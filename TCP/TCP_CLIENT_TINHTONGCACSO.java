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
public class TCP_CLIENT_TINHTONGCACSO {
    public static void main(String[] args) throws IOException {
        //Giai đoạn client tạo socket, yêu cầu kết nối lên server và server chắp nhận kết nối
        Socket client = new Socket("localhost", 9999);
        //Giao đoạn 3: Trao đổi thông tin giữa Client và Server
            //Giai đoạn 3.1: client tạo luồng nhập xuất và nhập dữ liệu từ bàn phím
        DataInputStream din = new DataInputStream(client.getInputStream());
        DataOutputStream dout = new DataOutputStream(client.getOutputStream());
        Scanner sc = new Scanner(System.in);
        int n =0;
        boolean check  = false;
        while (!check) {            
            try
            {
                System.out.println("Nhap so n: ");
                n= sc.nextInt();
                check = true;
            }
            catch(Exception e)
            {
                sc.nextLine();
            }
        }
            //Giai đoạn 3.2: client gửi dữ liệu server
        dout.writeInt(n);
            //Giai đoạn 3.6: client nhận dữ liệu từ server và hiển thị lên màn hình
        int tong = din.readInt();
        System.out.println("Tong cac so cua n la: " + tong);
        //Giai đoạn 4: Kết thúc phiên làm việc
        client.close();
    }
}
