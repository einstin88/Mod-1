package demo;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("CLIENT SERVER :");
        int PORT = 8000;

        try {
            Socket cs = new Socket("localhost", PORT);

            OutputStream os = cs.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            // Test sending message
            // dos.writeUTF("Client says Hello!");
            // dos.flush();
            // System.out.println("Message sent to Server");

            // cs.close();
            //
            Scanner inputSc = new Scanner(System.in);
            String line;

            while ((line = inputSc.nextLine()) != null) {
                if (line.equalsIgnoreCase("close")) {
                    System.out.println("Exiting");
                    break;
                }
                dos.writeUTF(line);
                dos.flush();
                System.out.println("Message sent!");
            }
            cs.close();
            inputSc.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
