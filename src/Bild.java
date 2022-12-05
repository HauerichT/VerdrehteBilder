import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Bild {

    private int[][] bilddaten;
    private int[][] gedrehteBilddaten;
    private String format;
    private int breite;
    private int hoehe;
    private int maxHelligkeit;

    public void leseBild(String dateiName) throws IOException {

        Path path;
        String data = "";

        try {
            path = Paths.get("src/"+dateiName);
            data = Files.readString(path);
        } catch (IOException e) {
            System.out.println("Die übergebene Datei existiert nicht!");
        }

        String[] dataInArray = data.split("\r\n");

        this.format = dataInArray[0].trim();
        this.maxHelligkeit = Integer.parseInt(dataInArray[2].trim());
        String[] formatArr = dataInArray[1].split(" ");
        this.breite = Integer.parseInt(formatArr[0]);
        this.hoehe = Integer.parseInt(formatArr[1].trim());

        String[] temp;
        int[][] bilddatenTemp = new int[hoehe][breite];
        for (int i = 0; i < this.hoehe; i++) {
            temp = dataInArray[i+3].split("\\s+");
            for (int j = 0; j < this.breite; j++) {
                bilddatenTemp[i][j] = Integer.parseInt(temp[j]);
            }
        }
        bilddaten = bilddatenTemp;

        System.out.println("Format: " + this.format);
        System.out.println("Breite: " + this.breite);
        System.out.println("Höhe: " + this.hoehe);
        System.out.println("Maximalwert für die Helligkeit: " + this.maxHelligkeit);
        System.out.println("Bilddaten in einem 2D Array: " + Arrays.deepToString(this.bilddaten));

    }

    public void rotateBild() {
        int[][] gedrehteBilddatenTemp = new int[breite][hoehe];
        for (int i = 0; i < this.hoehe; i++) {
            for (int j = 0; j < this.breite; j++) {
                gedrehteBilddatenTemp[j][i] = bilddaten[i][breite-j-1];
            }
        }
        gedrehteBilddaten = gedrehteBilddatenTemp;
    }


    public void schreibeBild(String verzeichnis) throws IOException {

        LocalDate date = LocalDate.now();

        String gedrehteBilddatenString = this.format + "\n" + this.hoehe + " " +this.breite + "\n" + this.maxHelligkeit + "\n";
        for (int i = 0; i < this.breite; i++) {
            for (int j = 0; j < this.hoehe; j++) {
                if (this.gedrehteBilddaten[i][j] > 10) {
                    gedrehteBilddatenString = gedrehteBilddatenString.concat(" ");
                }
                else if (j != 0) {
                    gedrehteBilddatenString = gedrehteBilddatenString.concat("  ");
                }
                gedrehteBilddatenString = gedrehteBilddatenString.concat(String.valueOf(this.gedrehteBilddaten[i][j]));
            }
            gedrehteBilddatenString = gedrehteBilddatenString.concat("\n");
        }

        Path path = Paths.get(verzeichnis, date+".pgm");

        try {
            Files.writeString(path, gedrehteBilddatenString);
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
        }

    }
}
