package http_practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HttpClientConnection implements Runnable {
    /*
     * Handles requests and responses from client
     */

    // Constants
    final String RESP_INVALID_METHOD = "405 Method Not Allowed";
    final String RESP_RESOURCE_ERROR = "404 Not Found";
    final String RESP_OK = "200 OK";

    // Instance variables
    Socket socket;
    List<String> staticPath;

    // Constructor
    public HttpClientConnection(Socket socket, List<String> staticPath) {
        this.socket = socket;
        this.staticPath = staticPath;
    }

    // Helper functions 
    private String responseHeader(String responseCode, String payLoad) {
        /*
         * Generates HTTP headers to be sent back
         */
        String responseTemplate = "HTTP/1.1 %s\r\n\r\n%s".formatted(responseCode, payLoad);
        System.out.println(">> " + responseTemplate.replaceAll("\n", ""));
        return responseTemplate;
    }
    
    // Overloaded function
    private String responseHeader(String responseCode) {
        String responseTemplate = "HTTP/1.1 %s\r\n".formatted(responseCode);
        System.out.println(">> " + responseTemplate.replaceAll("\n", ""));
        return responseTemplate;
    }

    // Main logic for handling requests
    @Override
    public void run() {
        System.out.println("Awaiting requests from client...");

        try {
            // IO Helper - to read incoming http requests
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            // IO Helper - to send http responses
            HttpWriter hr = new HttpWriter(socket.getOutputStream());

            String request = br.readLine();
            System.out.println("<< " + request);

            String[] parsedRequest = request.split(" ");
            String method = parsedRequest[0].trim();
            switch (method) {
                // If receive a GET request
                case ("GET") -> {
                    String resourceName = parsedRequest[1].trim();
                    resourceName = "/".equals(resourceName) ? "/index.html" : resourceName;

                    // Search for requested resource in all static paths
                    for (String dirPath : staticPath) {
                        // Joins directory path with file name into path object
                        Path resourcePath = Paths.get(dirPath, resourceName);

                        if (Files.exists(resourcePath)) {
                            // If found -> convert file to bytes to be sent as the http payload
                            byte[] payLoad = Files.readAllBytes(resourcePath);
                            
                            if (resourceName.endsWith(".png")) {
                                // Add meta data if resource is a .png file
                                hr.writeString(
                                    "HTTP/1.1 %s\r\nContent-Type: image/png\r\nContent-Length: %d\r\n".formatted(RESP_OK, payLoad.length)
                                );
                                System.out.printf(
                                    "HTTP/1.1 %s\r\nContent-Type: image/png\r\nContent-Length: %d%n", RESP_OK, payLoad.length
                                );
                            } else {
                                hr.writeString(responseHeader(RESP_OK));
                            }
                            // Write bytes out after the headers
                            hr.writeBytes(payLoad);

                        } else {
                            // Respond with error message if requested file can't be found
                            String message = "%s not found".formatted(resourceName);
                            hr.writeString(responseHeader(RESP_RESOURCE_ERROR, message));
                        }
                    }

                }
                // Respond with error message if request method is not GET
                default -> {
                    String message = "%s not supported".formatted(method);
                    hr.writeString(responseHeader(RESP_INVALID_METHOD, message));
                }
            }
            hr.close();

        } catch (Exception e) {
        }
    }
}
