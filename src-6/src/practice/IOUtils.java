package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IOUtils {
    public static void write(Socket socket, String payLoad) throws IOException {
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        dos.writeUTF(payLoad);
        dos.flush();
        
        dos.close();
        bos.close();
        os.close();
    }
    
    public static String read(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);
        
        String response = dis.readUTF();
        
        dis.close();
        bis.close();
        is.close();

        return response;
    }

}
