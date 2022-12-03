import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Bild {

    private int[][] bilddaten;
    private String format;
    private int breite;
    private int hoehe;
    private int maxHelligkeit;

    public void leseBild(String dateiName) throws IOException {

        Path path = Paths.get("/Users/timohaverich/IdeaProjects/VerdrehteBilder/src/"+dateiName);

        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            System.out.println(lines);


            String size = lines.get(1);
            String[] formatSplit = size.split(" ");
            this.breite = Integer.parseInt(formatSplit[0]);
            this.hoehe = Integer.parseInt(formatSplit[1]);
            this.format = this.breite + " x " +  this.hoehe;

            System.out.println(breite);
            System.out.println(hoehe);
            System.out.println(format);

        }
        else {
            System.out.println("nicht");
        }
    }
}
