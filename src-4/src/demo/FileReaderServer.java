package demo;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReaderServer {
    static int PORT = 8000;
    public static void main(String[] args) {
        System.out.println("Server started. Listening for connection...");
        try {
            ServerSocket server = new ServerSocket(PORT);
            Socket socket = server.accept();
            System.out.println("Connected.");
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String line = dis.readUTF();
            while (!line.equalsIgnoreCase("EOF") && line != null ) {
                System.out.println("Received --> " + line);
                try {
                    line = dis.readUTF();
                } catch (EOFException e) {
                    System.out.println("Ended.. Closing connection...");
                    server.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
