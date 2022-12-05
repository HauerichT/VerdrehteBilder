import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Bild {

    private int[][] bilddaten;
    private String format;
    private int breite;
    private int hoehe;
    private int maxHelligkeit;

    public void leseBild(String dateiName) throws IOException {

        Path path = Paths.get("src/"+dateiName);

        if (Files.exists(path)) {
            String dataOutFile = Files.readString(path);
            String[] dataInArray = dataOutFile.split("\r\n");

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
            System.out.println("Bilddaten in einem 2D Array: "+Arrays.deepToString(this.bilddaten));



        }
        else {
            System.out.println("nicht");
        }
    }
}
