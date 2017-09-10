package socketdemoclient;

/**
 *
 * @author Rafael
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

public class SocketDemoClient {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 8080);
            BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
            String str = "Hello World!";

            while (true) {
                for (int i = 0; i < str.length(); i++) {
                    byte b = (byte) str.charAt(i);
                    bos.write(b);
                    bos.flush();
                    char ch = (char) bis.read();
                    System.out.println("recebido no client" + ch);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
