package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedListServer {
    public static void main(String[] args) {
        Integer threadCount = Integer.parseInt(args[1]);
        ExecutorService threadpool = Executors.newFixedThreadPool(
                                                    threadCount);

        Integer port = Integer.parseInt(args[0]);
        ServerSocket server;
        System.out.printf("Started server with %d threads%n", threadCount);

        try {
            server = new ServerSocket(port);
            System.out.printf("Listening to incoming connection on port -> %d%n", port);

            while (true) {
                Socket serverSocket = server.accept();
                System.out.printf("New client connected on port: %d%n", serverSocket.getPort());

                DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(serverSocket.getInputStream()));
                DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(serverSocket.getOutputStream()));

                ServerTaskHandler newClient = new ServerTaskHandler(
                    dis, dos, serverSocket);
                threadpool.submit(newClient);
            }

            
        } catch (IOException e) {
            System.out.println("Error on starting server at port: " + port);
        }
        threadpool.shutdown();
    }
}
