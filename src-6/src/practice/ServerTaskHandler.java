package practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ServerTaskHandler implements Runnable {
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;

    public ServerTaskHandler(DataInputStream dis, DataOutputStream dos, Socket socket) {
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String[] parsedRequest = dis.readUTF().split(" ");
            Integer req1 = Integer.parseInt(parsedRequest[0]);
            Integer req2 = Integer.parseInt(parsedRequest[1]);

            String payload = "";
            for (Integer n : GetRandomNumber(req1, req2)) {
                payload += String.valueOf(n).concat(",");
            }

            dos.writeUTF(payload);
            dos.flush();

            // socket.close();
        } catch (IOException e) {
            System.out.printf("Client at %d is disconnected%n", socket.getPort());
        }
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
