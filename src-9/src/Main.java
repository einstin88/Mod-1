import java.io.IOException;
import java.util.List;

import http_practice.HttpServer;

public class Main {
    // static final List<String> ARGS = List.of("--port", "--docRoot");
    static final String ARG_PORT = "--port";
    static final String ARG_STATIC_DIR = "--docRoot";

    static Integer port = 3000;
    static List<String> staticPath = List.of("./static");

    public static void main(String[] args) {
        /*
         * Main entry point for HttpServer application
         */

        // + Parse the input arguments, if available.
        // + Handle invalid arguments
        if (args.length == 2 | args.length == 4) {
            for (int i = 0; i < args.length; i += 2) {
                // Check the reference argument to be assigned
                if (args[i].trim().equals(ARG_PORT)) {
                    port = Integer.parseInt(args[i + 1]);

                } else if (args[i].trim().equals(ARG_STATIC_DIR)) {
                    String[] paths = args[i + 1].trim().split(":");
                    staticPath = List.of(paths);

                } else {
                    System.out.println("Error! Incorrect argument specified");
                    return;
                }
            }
        } else if (args.length == 0) {
            // Do nothing
        } else {
            System.out.println("Error! Incorrect argument length");
            return;
        }

        // Display starting up message
        System.out.printf("%nPort \t\t\t--> %d%n", port);
        System.out.printf("Static directory \t--> %s%n%n", staticPath);
        System.out.println("Starting server application...");

        // Spawns the web server
        HttpServer webServer = new HttpServer(port, staticPath);
        try {
            webServer.StartServer();
        } catch (IOException e) {
            System.out.printf("Error! Could not start server at port -> %s%nExiting...%n", port);
            System.exit(1);
        }

    }
}
