import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileAway {


    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        int numOfLines = 0;
        int numOfWords = 0;
        int numOfCharacters = 0;


        try {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);


            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));


                int line = 0;

                while (reader.ready()) {
                    rec = reader.readLine();
                    numOfLines++;
                    numOfCharacters += rec.length();
                    numOfWords += new StringTokenizer(rec, " ,").countTokens();

                }
                reader.close();
                System.out.println("Selected File Name : " + selectedFile.getName());
                System.out.println("\n**DATA FILE READ!**\n");
                System.out.printf("Number Of Lines: %d\nNumber Of Words: %d\nNumber Of Characters: %d\n", numOfLines, numOfWords, numOfCharacters);
            } else {
                System.out.println("You must select a file!.. Program Existing!!!");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }
}