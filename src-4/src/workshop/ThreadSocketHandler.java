package workshop;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadSocketHandler extends Thread {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Cookie cookie;

    public ThreadSocketHandler(Socket socket, DataInputStream dis, DataOutputStream dos, Cookie cookie) {
        this.socket = socket;
        this.dis = dis;
        this.dos = dos;
        this.cookie = cookie;
    }

    @Override
    public void run() {
        System.out.println("This client is using thread: " + Thread.currentThread().getName());
        while (true) {
            try {
                String request = dis.readUTF();
                System.out.println("Received request : " + request);

                if (request.equalsIgnoreCase("exit")) {
                    break;
                } else if (request.equalsIgnoreCase("get-cookie")) {
                    dos.writeUTF("cookie-text --> " + cookie.getCookie());
                    dos.flush();
                }
            } catch (IOException e) {

            }

        }
        try {
            socket.close();
        } catch (IOException e) {
        }
    }
}
