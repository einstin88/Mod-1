package http_practice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    /*
     * Spawns a multi-threaded server
     */

    // Instance variables
    Integer port;
    List<String> staticPath;

    // Constructor
    public HttpServer(Integer port, List<String> staticPath) {
        this.port = port;
        this.staticPath = staticPath;
    }

    // Main Method
    public void StartServer() throws IOException {
        /*
         * - Open TCP Connection
         * - Verify static path is valid
         */

        // Instance variables
        ServerSocket server;
        Socket serverSocket;

        // Verify if static paths are valid
        for (String dirPathRaw : staticPath) {
            Path path = Paths.get(dirPathRaw);
            if (Files.notExists(path) | !Files.isDirectory(path) | !Files.isReadable(path)) {
                System.out.printf("Error! Could not access \"%s\"%n%Exiting...%n", dirPathRaw);
                System.exit(1);
            }
        }

        // Starts the server
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        server = new ServerSocket(port);
        System.out.printf("-> Server started. Listening to incoming connections on port %d%n", port);

        while (true) {
            serverSocket = server.accept();
            System.out.printf("-> New client connected at port -> %d%n", serverSocket.getPort());

            // Pass the client to a child thread for handling http messaging
            HttpClientConnection client = new HttpClientConnection(serverSocket, staticPath);
            threadPool.submit(client);
        }
    }
}
