/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ST3_LTM;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author ASUS
 */
public class UDP_SERVER_FIBO {
    public static void main(String[] args) throws SocketException, IOException {
        //Giai đoạn 1: Server tạo socket và gắng cổng
        DatagramSocket server = new DatagramSocket(9999);
        System.out.println("Server da chay, doi nhan tin nhan tu nguoi gui!!!");
        while (true) 
        {   
            //Giai đoạn 3: trao đổi dữ liệu giữa client và server
                //Giai đoạn 3.3: Server nhận dữ liệu từ client gửi lên
            byte nhann[] = new byte[256];
            DatagramPacket nhanson = new DatagramPacket(nhann, nhann.length);
            server.receive(nhanson);
                //Giai đoạn 3.4: Xử lý dữ liệu
            int n = Integer.parseInt(new String(nhanson.getData(),0,nhanson.getLength()));
            int fio = fibo(n);
                //Giai đoạn 3.5: Server gửi dữ liệu về client
            byte sofi[] = new byte[256];
            sofi = String.valueOf(fio).getBytes();
            DatagramPacket guisofi = new DatagramPacket(sofi, sofi.length, nhanson.getAddress(), nhanson.getPort());
            server.send(guisofi);
            
        }
    }
    public static int fibo(int n)
    {
        if(n==1 || n == 2)
            return 1;
        return fibo(n-1)+ fibo(n-2);
    }
}
