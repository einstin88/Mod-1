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
    final String RESP_INVALID_METHOD = "405 Method Not Allowed";
    final String RESP_RESOURCE_ERROR = "404 Not Found";
    final String RESP_OK = "200 OK";

    Socket socket;
    List<String> staticPath;

    public HttpClientConnection(Socket socket, List<String> staticPath) {
        this.socket = socket;
        this.staticPath = staticPath;
    }

    private String responseHeader(String responseCode, String payLoad) {
        String responseTemplate = "HTTP/1.1 %s\r\n\r\n%s".formatted(responseCode, payLoad);
        System.out.println(">> " + responseTemplate.replaceAll("\n", ""));
        return responseTemplate;
    }
    
    private String responseHeader(String responseCode) {
        String responseTemplate = "HTTP/1.1 %s\r\n".formatted(responseCode);
        System.out.println(">> " + responseTemplate.replaceAll("\n", ""));
        return responseTemplate;
    }

    @Override
    public void run() {
        System.out.println("Awaiting requests from client...");

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            HttpWriter hr = new HttpWriter(socket.getOutputStream());

            String request = br.readLine();
            System.out.println(request);

            String[] parsedRequest = request.split(" ");
            String method = parsedRequest[0].trim();
            switch (method) {
                case ("GET") -> {
                    String resourceName = parsedRequest[1].trim();
                    resourceName = "/".equals(resourceName) ? "/index.html" : resourceName;

                    for (String dirPath : staticPath) {
                        Path resourcePath = Paths.get(dirPath, resourceName);
                        if (Files.exists(resourcePath)) {
                            byte[] payLoad = Files.readAllBytes(resourcePath);
                            
                            if (resourceName.endsWith(".png")) {
                                System.out.println(resourcePath.toString());
                                hr.writeString(
                                    "HTTP/1.1 %s\r\nContent-Type: image/png\r\nContent-Length: %d\r\n".formatted(RESP_OK, payLoad.length)
                                );
                                System.out.printf(
                                    "HTTP/1.1 %s\r\nContent-Type: image/png\r\n%n\r\n".replaceAll("\n", ""), RESP_OK
                                );
                            } else {
                                hr.writeString(responseHeader(RESP_OK));
                            }
                            hr.writeBytes(payLoad);

                        } else {
                            String message = "%s not found".formatted(resourceName);
                            hr.writeString(responseHeader(RESP_RESOURCE_ERROR, message));
                        }
                    }

                }
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
