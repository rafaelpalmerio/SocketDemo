package socketdemoservermultithreaded;

/**
 *
 * @author Rafael
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class SocketDemoServerMultiThreaded {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(8080);
            while(true){
                Socket s = ss.accept();
                Service servico = new Service(s);
                servico.start();
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static class Service extends Thread{
        Socket socket = null;
        
        Service(Socket s){
            socket = s;
        }
        public void run() {
            try{
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                while(true){
                    int ch = bis.read();
                    bos.write((byte)ch);
                    bos.flush();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
