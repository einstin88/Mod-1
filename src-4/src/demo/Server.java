package demo;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("SOCKET SERVER :");
        int PORT = 8000;

        // Create ServerSocket
        try {
            ServerSocket server = new ServerSocket(PORT);

            // Get socket object
            Socket socket = server.accept();
            //
            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            // Test receiving message
            // String message = dis.readUTF();
            // System.out.println("Received -> " + message);
            String clientMsg = dis.readUTF();
            while (!clientMsg.equalsIgnoreCase("close") && clientMsg != null) {
                // Process message
                System.out.println("Received --> " + clientMsg);
                // Read next line from input stream
                clientMsg = dis.readUTF();
            }
            server.close();

        } catch (IOException e) {
            System.out.println("IO Error: " + e);
            
        }
    }
}
