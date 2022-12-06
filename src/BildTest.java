public class BildTest {
    public static void main(String[] args) {

        // Test von Bild.java
        Bild bild = new Bild();

        // einlesen und verarbeiten des Bildes
        bild.leseBild("java.pgm");
        System.out.println("Ursprüngliches Bild:");
        bild.printBilddaten();

        System.out.println();

        // rotieren des Bildes
        bild.rotateBild();
        System.out.println("Gedrehtes Bild:");
        bild.printGedrehteBilddaten();
        bild.schreibeBild("src/");

    }
}