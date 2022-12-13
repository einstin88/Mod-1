package workshop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Server {
    static String DIR_PATH = "src/workshop/";

    public static void main(String[] args) {

        String usage = """

                Usage: Server
                <program> <server> <port> <file_name>

                Usage: Client
                <program> <client> <host> <port>
                    """;

        if (args.length != 3) {
            System.out.println("Incorrect Inputs.");
            System.out.println(usage);
            return;
        }

        String mode = args[0];

        if (mode.equalsIgnoreCase("server")) {
            System.out.println("Starting server...");
            StartServer(Integer.parseInt(args[1]), args[2]);

        } else if (mode.equalsIgnoreCase("threaded.server")) {
            System.out.println("Starting multi-threaded server...");
            StartMultiThreadedServer(Integer.parseInt(args[1]), args[2]);

        } else if (mode.equalsIgnoreCase("client")) {
            System.out.println("Starting client...");
            StartClient(args[1], Integer.parseInt(args[2]));

        } else {
            System.out.println("Incorrect arguments !!");
        }
    }

    public static void StartMultiThreadedServer(int port, String fileName) {
        // Read the cookie file with the given file name
        String filePath = DIR_PATH + fileName;
        Cookie cookie = new Cookie(filePath);
        // Do not continue if no valid file or no valid input is found
        if (!cookie.readFileFlag) {
            return;
        }

        // Declare outside of try-catch block for accesibility purpose
        ServerSocket server;

        try {
            server = new ServerSocket(port);

            while (true) {
                System.out.println("Listening to incoming connection on port " + port);
                Socket socket = server.accept();
                System.out.println("Client connected.");

                DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

                Thread thr = new ThreadSocketHandler(socket, dis, dos, cookie);
                thr.start();
            }
        } catch (IOException e) {

        }
    }

    public static void StartServer(int port, String fileName) {
        String filePath = DIR_PATH + fileName;
        Cookie cookie = new Cookie(filePath);
        if (!cookie.readFileFlag) {
            return;
        }

        ServerSocket server;

        try {
            server = new ServerSocket(port);
            System.out.println("Listening to incoming connection on port " + port);
            Socket socket = server.accept();
            System.out.println("Client connected.");

            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            while (true) {
                String request = dis.readUTF();
                System.out.println("Received request : " + request);

                if (request.equalsIgnoreCase("exit")) {
                    break;
                } else if (request.equalsIgnoreCase("get-cookie")) {
                    dos.writeUTF("cookie-text --> " + cookie.getCookie());
                    dos.flush();
                }
            }
            socket.close();

        } catch (IOException e) {

        }

    }

    public static void StartClient(String hostName, int port) {
        Socket socket;
        try {
            socket = new Socket(hostName, port);

            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            System.out.println("Connected to server...");

            Scanner sc = new Scanner(System.in);
            boolean stop = false;

            while (!stop) {
                System.out.printf("Command to send to server >> ");
                String line = sc.nextLine();

                if (line.equalsIgnoreCase("exit")) {
                    dos.writeUTF("exit");
                    dos.flush();
                    stop = true;
                    sc.close();
                    break;
                } else if (line.equalsIgnoreCase("get-cookie")) {
                    dos.writeUTF("get-cookie");
                    dos.flush();

                    String response = dis.readUTF();
                    System.out.println(">> Response: " + response);

                } else {
                    System.out.println("Invalid command.");
                }

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
