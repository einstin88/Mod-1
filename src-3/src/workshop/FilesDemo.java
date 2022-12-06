package workshop;

import java.io.BufferedReader;
// import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesDemo {
    public static void main(String[] args) {
        String pathName = "src-3/src/workshop/README.txt";
        ReadFile(pathName);
        WriteFile("src-3/src/workshop/OUTPUT.txt");
    }

    public static void ReadFile(String pathName) {
        // Path Object
        Path path = Paths.get(pathName);

        // File Object
        File fileObj = path.toFile();
        if (!fileObj.exists()) {
            System.out.println("File not found.");
            return;
        }

        // Reader Object
        try {
            FileReader fr = new FileReader(fileObj);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            System.out.println(line);

            br.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void WriteFile(String pathName) {
        try {
            FileWriter fw = new FileWriter(pathName, false);
            // BufferedWriter bfw = new BufferedWriter(fw);

            fw.write("apple\n");
            fw.write("orange\n");


            fw.flush();
            fw.close();

        } catch (IOException e) {
            System.err.println("An error has occured while writing file");
        }
    }
}
