/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserversimples;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Rafael
 */
public class WebServerSimples {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8080);
            while (true) {
                Socket s = ss.accept();
                Service servico = new Service(s);
                servico.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Service extends Thread {

        Socket socket = null;

        Service(Socket s) {
            socket = s;
        }

        public void run() {
            try {
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                String str = "";
                int recEndLine = 0;
                String status = "HTTP/1.1 200 OK\r\n";
                String at1 = "Content-Type: text/html\r\n";
                String at2 = "Server: java\r\n";
                String blank = "\r\n";
                String html = "<html><body>web server alive</body></html>";
                String response = status + at1 + at2 + blank + html + blank + blank; 
                        
                while (true) {
                    int ch = bis.read();
                    str += (char) ch;
                    if (ch == 10) {
                        System.out.println(str);
                        str = "";
                    }
                    if ((ch == 10) || (ch == 13)) {
                        recEndLine++;
                        if(recEndLine==3){
                            bos.write(response.getBytes());
                            bos.flush();
                            socket.close();
                            return;
                        }
                    } else{
                        recEndLine = 0;
                    }
                    

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
