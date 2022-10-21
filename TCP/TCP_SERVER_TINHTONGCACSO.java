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
public class TCP_SERVER_TINHTONGCACSO {
    public static void main(String[] args) throws IOException {
        //Giai đoạn 1: Server tạo socket gắng công, lắng nghe kết nối
        ServerSocket server = new ServerSocket(9999);
        System.out.println("Server dang e, chua co nguoi yeu!!!!");
        while (true) {            
            //Giai đoạn 2: client tạo socket, yêu cầu kết nối và server chấp nhận kết nối
            Socket client = server.accept();
            System.out.println("Server thoat e, Server da co nguoi yeu!!!!");
            //Giao đoạn 3: Trao đổi thông tin giữa Client và Server
                //Giai đoạn 3.3: server tạo luồng nhập xuất và nhận dữ liệu từ client
            DataInputStream din = new DataInputStream(client.getInputStream());
            DataOutputStream dout = new DataOutputStream(client.getOutputStream());
            int n = din.readInt();
                //Giai đoạn 3.4: server xử lý dữ liệu
            int tong =0;
            while (n<0) {                
                n*=-1; // n = n * (-1);
            }
            while (n>0) {                
                tong +=n%10; //Tong = tong + n%10;
                n/=10; // n = n /10;
            }
                //Giai đoạn 3.5: Server trả dữ liệu về cho client
            dout.writeInt(tong);
        }
        
    }
}
