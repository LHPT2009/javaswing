/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicast;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ADMIN
 */
public class TCPServer {
    static final int PORT = 7777;
    private ServerSocket server = null;
    public TCPServer(){
        try{
            server = new ServerSocket(PORT);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void action() throws IOException{
        Socket socket = null;
        int i = 0;
        System.out.println("Server listening");
        try {
            while((socket = server.accept()) != null){
            new ServerThread(socket, "Client#" + i);
            System.out.printf("Thread for Client#%d generating...%n",i++);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        new TCPServer().action();
    }
}
