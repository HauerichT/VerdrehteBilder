import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Bild {

    // Instanzvariablen
    private int[][] bilddaten;
    private int[][] gedrehteBilddaten;
    private String format;
    private int breite;
    private int hoehe;
    private int maxHelligkeit;


    // Methode liest das übergebene PGM Bild ein
    public void leseBild(String dateiName) {

        // Variablendeklaration
        Path pfad;
        String daten = "";

        // versucht die übergebene Datei zu lesen
        try {
            pfad = Paths.get("src/"+dateiName);
            // speichert die Daten des Bildes
            daten = Files.readString(pfad);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Die übergebene Datei existiert nicht!");
        }

        // schreibt die Daten des Bildes, getrennt am Zeilenumbruch, in ein String Array
        String[] datenArray = daten.split("\r\n");

        // entfernt überflüssige Leerzeichen und speichert das Format
        this.format = datenArray[0].trim();

        // entfernt überflüssige Leerzeichen und speichert die maximale Helligkeit
        this.maxHelligkeit = Integer.parseInt(datenArray[2].trim());

        // schreibt die Größe des Bildes in ein weiteres Array und speichert Höhe und Breite
        String[] formatArray = datenArray[1].split(" ");
        this.breite = Integer.parseInt(formatArray[0]);
        this.hoehe = Integer.parseInt(formatArray[1].trim());

        // speichert die aktuelle Zeile
        String[] tempZeile;
        // neues temporäres Array um Daten in Instanzvariable schreiben zu können
        int[][] tempBilddaten = new int[this.hoehe][this.breite];

        for (int i = 0; i < this.hoehe; i++) {
            // trennt die Werte der Zeile an Leerzeichen
            tempZeile = datenArray[i+3].split("\\s+");
            for (int j = 0; j < this.breite; j++) {
                // prüft, ob Wert eine Zahl ist und schreibt diese in das temporäre Array
                if (tempZeile[j].matches("\\d*")) {
                    tempBilddaten[i][j] = Integer.parseInt(tempZeile[j]);
                }
            }
        }
        // schreibt das befüllte temporäre Array in bilddaten
        this.bilddaten = tempBilddaten;

    }


    // Methode dreht das verarbeitete Bild um 90 Grad gegen den Uhrzeigersinn
    public void rotateBild() {
        // speichert die gedrehten Bilddaten temporär
        int[][] tempGedrehteBilddaten = new int[breite][hoehe];
        // dreht die Bilddaten und schreibt diese in das temporäre Array
        for (int i = 0; i < this.hoehe; i++) {
            for (int j = 0; j < this.breite; j++) {
                tempGedrehteBilddaten[j][i] = bilddaten[i][breite-j-1];
            }
        }
        // schreibt die gedrehten temporären Bilddaten in gedrehteBilddaten
        gedrehteBilddaten = tempGedrehteBilddaten;
    }


    // Methode schreibt das gedrehte Bild Array in einen String und anschließend in eine neue PGM Datei
    public void schreibeBild(String verzeichnis){

        // gibt das aktuelle Datum zurück
        LocalDate date = LocalDate.now();

        // String zur speicherung der gedrehten Array-Daten
        String gedrehteBilddatenString = this.format + "\n" + this.hoehe + " " +this.breite + "\n" + this.maxHelligkeit + "\n";

        for (int i = 0; i < this.breite; i++) {
            for (int j = 0; j < this.hoehe; j++) {
                // prüft die Stellenanzahl der Zahlenwerte und setzt dementsprechend viele Leerzeichen
                if (this.gedrehteBilddaten[i][j] > 10) {
                    gedrehteBilddatenString = gedrehteBilddatenString.concat(" ");
                }
                else if (j != 0) {
                    gedrehteBilddatenString = gedrehteBilddatenString.concat("  ");
                }
                // fügt die Werte einer Zeile zum String hinzu
                gedrehteBilddatenString = gedrehteBilddatenString.concat(String.valueOf(this.gedrehteBilddaten[i][j]));
            }
            // fügt einen Zeilenumbruch an das Ende einer Zeile
            gedrehteBilddatenString = gedrehteBilddatenString.concat("\n");
        }

        // erstellt und speichert die neue Datei zwischen
        Path pfadGedrehtesBild = Paths.get(verzeichnis, date+".pgm");

        // versucht die neue Datei im übergebenen Verzeichnis mit gedrehteBilddatenString zu schreiben
        try {
            Files.writeString(pfadGedrehtesBild, gedrehteBilddatenString);
        } catch (IOException e) {
            System.out.println("Gedrehtes Bild konnte nicht in dem übergebenen Verzeichnis gespeichert werden!");
            e.printStackTrace();
        }
    }


    // Methode gibt die gelesenen und verarbeiteten Daten auf der Konsole aus
    public void printBilddaten() {

        System.out.println("Format: " + this.format);
        System.out.println("Breite: " + this.breite);
        System.out.println("Höhe: " + this.hoehe);
        System.out.println("Maximalwert für die Helligkeit: " + this.maxHelligkeit);

        System.out.println("Bilddaten in einem 2D Array: ");
        for (int i = 0; i < this.bilddaten.length; i++) {
            for (int j = 0; j < this.bilddaten[i].length; j++) {
                if (this.bilddaten[i][j] > 10) {
                    System.out.print(" ");
                }
                else if (j != 0) {
                    System.out.print("  ");
                }
                System.out.print(this.bilddaten[i][j]);
            }
            System.out.println();
        }
    }


    // Methode gibt die Daten des gedrehten Bildes auf der Konsole aus
    public void printGedrehteBilddaten() {

        System.out.println("Format: " + this.format);
        System.out.println("Breite: " + this.hoehe);
        System.out.println("Höhe: " + this.breite);
        System.out.println("Maximalwert für die Helligkeit: " + this.maxHelligkeit);

        System.out.println("Gedrehte Bilddaten in einem 2D Array: ");
        for (int i = 0; i < this.gedrehteBilddaten.length; i++) {
            for (int j = 0; j < this.gedrehteBilddaten[i].length; j++) {
                if (this.gedrehteBilddaten[i][j] > 10) {
                    System.out.print(" ");
                }
                else if (j != 0) {
                    System.out.print("  ");
                }
                System.out.print(this.gedrehteBilddaten[i][j]);
            }
            System.out.println();
        }
    }
}
