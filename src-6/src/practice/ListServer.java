package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListServer {
    public static void main(String[] args) throws Exception {
        Integer port = Integer.parseInt(args[0]);

        ServerSocket server = new ServerSocket(port);
        System.out.println("Listening to incoming connections on port " + port);

        while(true) {
            Socket serverSocket = server.accept();
            System.out.println("New client connected at port: " + serverSocket.getPort());

            DataInputStream dis = new DataInputStream(new BufferedInputStream(serverSocket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(serverSocket.getOutputStream()));
    
            String[] request = dis.readUTF().split(" ");
            Integer req1 = Integer.parseInt(request[0]);
            Integer req2 = Integer.parseInt(request[1]);

            // dos.writeUTF(GetRandomNumber(req1, req2).toString());
            String response = "";
            for ( int num : GetRandomNumber(req1, req2)) {
                response += String.valueOf(num).concat(",");
            }
            dos.writeUTF(response);
            dos.flush();
            break;
        }
        server.close();
    }


    protected static List<Integer> GetRandomNumber(Integer reps, Integer limit) {
        List<Integer> numList = new LinkedList<>();

        Random rand = new SecureRandom();
        for (int i = 0; i < reps; i++) {
            numList.add(rand.nextInt(limit));
        }

        return numList;
    }
}
