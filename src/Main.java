import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    public static void main(String[] args) {
        // JFrame mainFrame = new Frame();
        JPanel mainPanel = new JPanel();

        JFileChooser chooser = new JFileChooser();
        File selectedFile; // why do I need this?
        String rec = "";
        ArrayList<String> test = new ArrayList<>();

        try {
            // File workingDirectory = new File(System.getProperty("user.dir"));
            // chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                while (reader.ready()) {
                    rec = reader.readLine();
                    test.add(rec);
                }
                reader.close();
                System.out.println("Test Read\n---------\n");
                String[] keyWords;
                for(String l : test) {
                    keyWords = l.split(" ");
                    for (String cut : keyWords)
                    {
                        // cuts noise words
                        // cuts non-letter (regex)
                    }
                    System.out.println(l);
                }
                System.out.println("\n---------");
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}