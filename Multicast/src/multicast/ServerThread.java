/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicast;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
    * @author CrisAn
 */
public class ServerThread implements Runnable {

    private Scanner in = null;
    private PrintWriter out = null;
    private Socket socket;
    private String name;
 
    public ServerThread(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.name = name;
        this.in = new Scanner(this.socket.getInputStream());
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            DBAccess acc = new DBAccess();
            while (true) {
                String chuoi = in.nextLine().trim();
                System.out.println("Chuoi nhan: " + chuoi);
                Scanner sc = new Scanner(chuoi);
                sc.useDelimiter("@");//1@2@5
                int flag = sc.nextInt();
                if (flag == 1) {
                    String hoTen = sc.next();
                    int namSinh = sc.nextInt();
                    String queQuan = sc.next().trim();
                    String sdt = sc.next().trim();
              
                    acc.Update("insert into sinhvien values('"+hoTen+"',"+namSinh+",'"+queQuan+"','"+sdt+"')");
                }
                else if(flag == 2)
                {
                    String hoTen = sc.next();
                    ResultSet rs = acc.Query("select quequan from sinhvien where '"+hoTen+"'=hoten");
                    while (rs.next()) {
                        String queQuan = rs.getString("quequan");
                        System.out.println("Search QueQuan: "+queQuan);
                        out.println(queQuan);
                    }
                    
                }
                else if (flag == 3) {
                    String path = sc.next();
                    String[] lines = (new String(Files.readAllBytes((new File(path))
                                   .toPath()))).replace("\r", "").split("\n");
                    
                    if (lines.length == 1 || lines.length==0) {
                        out.println("Invalid matrix!");
                        JOptionPane.showMessageDialog(null, "Ma trận không hợp lệ!");
                        return;
                    }
                    
                    // declare a 2-dimensional int array with the 1st dimension of lines.length
                    int a[][] = new int[lines.length][]; // create a 2 dimentinal array
                    int xLength = 0;
                    // fill the 2nd. dimensional elements
                    for (int i = 0; i < lines.length; ++i) {
                      String[] x = lines[i].split(" "); // string array of each line
                      a[i] = new int[x.length];  // create the 2nd dimension with the length of each line
                      xLength = x.length;
                      for (int j = 0; j < x.length; ++j) // convert to int
                        a[i][j] = x[j].length() == 0? -1:Integer.parseInt(x[j]);
                    }
                    
                    boolean kqkt = KiemTraDoiXung(a, lines.length, xLength);
                    if (kqkt) {
                        out.println("Ma trận đối xứng!");
                    }else {
                        out.println("Ma trận ko đối xứng!");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(name + " has departed");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
    
    public boolean KiemTraDoiXung(int a[][], int m, int n){
        if (m!=n) 
            return false;
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                if (a[i][j]!=a[j][i]) 
                    return false;
            }
        }
        return true;
    }
    
}
