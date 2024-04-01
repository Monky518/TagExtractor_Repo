import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> filter = new Filter().GetFilter(); // holds filter
        JFileChooser chooser = new JFileChooser();
        File selectedFile; // why do I need this?
        String rec = "";
        ArrayList<String> test = new ArrayList<>(); // holds user text in lines?
        Map<String, Integer> keyWords = new HashMap<String, Integer>(); // holds filtered text with int values

        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                // read the user file
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

                // filter test into keywords
                ArrayList<String> word = new ArrayList<>(); // up here for public / private reasons
                for (String line : test) {
                    String[] unfilteredLine = line.split(" "); // splits file into strings
                    for (String n : unfilteredLine) {
                        word.add(n.toLowerCase());
                    }
                }
                for (String w : word) {
                    boolean noiseWord = false;
                    for (String noise : filter) {
                        if (w.equals(noise)) {
                            noiseWord = true;
                            break;
                        }
                    }
                    if (!noiseWord) {
                        if (keyWords.get(w) == null) { // creates keyWords
                            keyWords.put(w, 1);
                        }
                        else { // update keyWords
                            Integer oldInt = keyWords.get(w);
                            keyWords.replace(w, (oldInt += 1));
                        }
                    }
                }
            }

            JFrame mainFrame = new Frame();
            JPanel mainPanel = new JPanel();
            JTextArea mainText = new JTextArea(15, 60);
            mainText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            mainText.setEditable(false);

            mainText.append("KEY WORDS FOUND AND FREQUENCY\n");
            mainText.append("=============================\n");
            mainText.append(keyWords.toString());
            mainPanel.add(mainText);
            mainFrame.add(mainPanel);

            mainFrame.show();

        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}