package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ListClient {
    public static void main(String[] args) throws Exception {
        String server = args[2];
        Integer port = Integer.parseInt(args[3]);
        Integer reps = Integer.parseInt(args[0]);
        Integer limit = Integer.parseInt(args[1]);

        Socket clientSocket;
        Console cons = System.console();

        System.out.printf("Attempting to connect to server at %s:%s%n", server, port);
        clientSocket = new Socket(server, port);
        System.out.printf("Connected to %s:%s -> %d%n", server, port, clientSocket.getPort());

        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        DataInputStream dis = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));

        while (true) {
            dos.writeUTF(String.format("%d %d", reps, limit));
            dos.flush();

            String response = dis.readUTF();
            System.out.printf("Response -> %s%n", response);

            Integer sum = 0;
            String[] responses = response.trim().split(",");
            for (String res : responses) {
                sum += Integer.parseInt(res);
            }
            System.out.println(responses.length);
            System.out.printf("The average of the random numbers are %d%n%n", sum / responses.length);

            String option = cons.readLine("Repeat? (Y/N)");
            if ("Yy".equals(option.trim())) {
                continue;
            } else {
                break;
            }
        }
        clientSocket.close();
    }
}
