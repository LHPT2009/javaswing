package udpserver2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPServer2 {
    static final int PORT =1234;
    private DatagramSocket socket = null;
    public UDPServer2(){
        try {
            socket = new DatagramSocket(PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void action(){
        InetAddress host = null;
        int port;
        String chuoi="";
        try {
            System.out.println("Server is listening");
            while (true) {
                DatagramPacket packet= receive();
                host=packet.getAddress();
                port=packet.getPort();
                chuoi=new String(packet.getData()).trim();
                if(!chuoi.equals("")){
                    Scanner sc =new Scanner(chuoi);
                    sc.useDelimiter("@");
                    int so1 = sc.nextInt();
                    String pheptoan = sc.next();
                    int so2= sc.nextInt();
                    if(pheptoan.equals("+"))
                        chuoi=(so1+so2)+"";
                    else if(pheptoan.equals("-"))
                        chuoi=(so1-so2)+"";
                    else if(pheptoan.equals("*"))
                        chuoi=(so1*so2)+"";
                    else if(pheptoan.equals("/"))
                        chuoi=((float)so1/so2)+"";
                    send(chuoi,host,port);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            socket.close();
        }
    }
    
    private void send (String chuoi, InetAddress host,int port) throws IOException{
        byte[] buffer =chuoi.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length,host,port);
        socket.send(packet);
    }
    
    private DatagramPacket receive() throws IOException{
        byte[] buffer = new byte[65507];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return packet;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new UDPServer2().action();
    }
    
}
