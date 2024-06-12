/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author VAISAL
 */
public class koneksi {
 private static Connection koneksi;
public static Connection koneksiDb(){
    if(koneksi == null){
            try{
                String url = "jdbc:mysql://localhost:3306/siska";
                String user = "root";
                String password = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println(("berhasil"));
            }catch(Exception e){
                System.out.println("Error");
            }
           
        }
    return koneksi;
}   
public static void main(String args[]){
    koneksiDb();
}

    static PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
