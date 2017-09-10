package socketdemoserver;

/**
 *
 * @author Rafael
 */

import java.net.ServerSocket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

public class SocketDemoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);
            while (true) {
                Socket s = ss.accept();
                BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
                BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
                while (true) {
                    int ch = bis.read();
                    System.out.println("recebido no servidor " + (char) ch);
                    bos.write((byte) ch);
                    bos.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
