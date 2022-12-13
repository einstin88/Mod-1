package workshop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Cookie {
    private LinkedList<String> cookies = new LinkedList<>();
    private String filePath;
    public boolean readFileFlag;

    public Cookie(String filePath) {
        this.filePath = filePath;
        readFile();
    }

    public LinkedList<String> getCookies() {
        return cookies;
    }

    private void readFile() {
        try {
            System.out.println("Opening file from " + filePath);
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            System.out.println("Reading cookie file...");

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals("")) {
                    this.cookies.add(line);
                }
            }
            br.close();
            readFileFlag = true;

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            readFileFlag = false;

        } catch (IOException e) {
            System.out.println("No cookie found!");
            this.readFileFlag = false;
        }
    }

    public String getCookie() {
        Random random = new Random();
        int randomInt = random.nextInt(cookies.size());
        return this.cookies.get(randomInt);
    }
}
