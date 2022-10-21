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
public class UDP_CLIENT_FIBO {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        //Giai đoạn 2: client tạo socket
        DatagramSocket client = new DatagramSocket();
        //Giai đoạn 3: Trao đổi dữ liệu giữa client và server
            //Giai đoạn 3.1: Cliet nhập dữ liệu
        Scanner sc = new Scanner(System.in);
        int n=0;
        boolean check = false;
        while (!check) {            
            try
            {
                System.out.println("nhap n: ");
                n = sc.nextInt();
                check = true;
            }
            catch(Exception e)
            {
                sc.nextLine();
            }
        }
            //Giai đoạn 3.2: Cliet tạo packet để gửi lên server
        byte guin[] = new byte[256];
        guin = String.valueOf(n).getBytes();
        InetAddress ip = InetAddress.getByName("localhost");
        int port = 9999;
        DatagramPacket guison = new DatagramPacket(guin, guin.length, ip, port);
        client.send(guison);
            //Giai đoạn 3.6: client nhận dữ liệu và hiển thị
        byte nhanfi[] = new byte[256];
        DatagramPacket nhansofi = new DatagramPacket(nhanfi, nhanfi.length);
        client.receive(nhansofi);
        
        String ketqua = new String(nhansofi.getData(),0,nhansofi.getLength());
        System.out.println("So fibonacia thu n la: " + ketqua);
        client.close();
    }
}
