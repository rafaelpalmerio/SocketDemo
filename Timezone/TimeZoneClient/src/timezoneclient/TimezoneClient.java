/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timezoneclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Rafael
 */
public class TimezoneClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 8081);
            BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);
            String resposta = "";
            while (true) {
                System.out.println("Digite o nome da cidade: ");
                String city = sc.nextLine() + '\n';

                bos.write(city.getBytes());
                bos.flush();
                char ch = (char) bis.read();
                
                while( (byte)ch != 10) {
                    resposta += ch;
                    ch = (char) bis.read();
                }
                System.out.println(resposta);
                resposta = "";
                
                //System.out.println("recebido no client" + ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
