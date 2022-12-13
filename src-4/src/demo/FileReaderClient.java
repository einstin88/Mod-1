package demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileReaderClient {
    static int PORT = 8000;
    public static void main(String[] args) {

        try {
            Socket cs = new Socket("localhost", PORT);
            DataOutputStream dos = new DataOutputStream(cs.getOutputStream());

            FileReader fr = new FileReader("src-4/src/demo/test.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }
                dos.writeUTF(line);
                dos.flush();
                System.out.println("Message sent!");
            }

            dos.writeUTF("EOF");
            dos.flush();
            br.close();
            cs.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
