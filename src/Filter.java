import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

import static java.nio.file.StandardOpenOption.CREATE;

public class Filter {
    public ArrayList<String> GetFilter(){
        File filterText = new File("src/englishStopWords.txt");
        String rec = "";
        ArrayList<String> filter = new ArrayList<>();

        try {
            Path file = filterText.toPath();
            InputStream in =
                new BufferedInputStream(Files.newInputStream(file, CREATE));
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(in));
            while (reader.ready()) {
                rec = reader.readLine();
                filter.add(rec);
            }
            reader.close();
            return filter;
        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ERROR: Noise word filter not set up properly");
        return null;
    }
}
