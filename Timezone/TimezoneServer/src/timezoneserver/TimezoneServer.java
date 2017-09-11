/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timezoneserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.TimeZone;
/**
 *
 * @author Rafael
 */
public class TimezoneServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8081);
            while (true) {
                Socket s = ss.accept();
                BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
                BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
                String city = "";
                int ch = 1;
                while (true) {
                    ch = bis.read();
                    
                    //System.out.println(city);
                    if (ch == 10) {
                        //System.out.println(city);
                        String time = "teste";
                        String response = pega_horario(city) + "\n";
                        System.out.println(response);
                        bos.write(response.getBytes());
                        bos.flush();
                        city = "";
                    }
                    else{
                        city += (char) ch;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String pega_horario(String cidade){
        if ("Brasilia".equals(cidade)){
            TimeZone zone = TimeZone.getTimeZone("America/Sao_Paulo");
            Calendar cal = new java.util.GregorianCalendar(zone);
            String resposta = "São " + cal.get(Calendar.HOUR_OF_DAY) + ":" + String.format("%02d", cal.get(Calendar.MINUTE)) + " em Brasilia";
            return resposta;
        }
        else{
            try{
                TimeZone zone = TimeZone.getTimeZone(cidade);
                Calendar cal = new java.util.GregorianCalendar(zone);
                String resposta = "São " + cal.get(Calendar.HOUR_OF_DAY) + ":" + String.format("%02d", cal.get(Calendar.MINUTE)) + " em "+ cidade + ".";
                return resposta;
            }catch(Exception e){
                return "Cidade não encontrada";
            }
        }
    }
    
}
