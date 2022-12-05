public class BildTest {
    public static void main(String[] args) {

        Bild bild = new Bild();
        bild.leseBild("java.pgm");
        bild.rotateBild();
        bild.schreibeBild("src/");

    }
}