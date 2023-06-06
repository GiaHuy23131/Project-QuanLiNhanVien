/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyNhanVien;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author fff
 */
public class DatabaseUtil {
    public static Connection c;
    private static String db_url = "jdbc:mysql://localhost:3306/quanlynhanvien";         
    private static String username = "root";
    private static String password = null;
    
    public static Connection getConnection() throws Exception{
        if(c == null){
            c = DriverManager.getConnection(db_url,username,password);
        }
        return c;
    }
    public static void main(String[] args) throws Exception{
        System.out.println(DatabaseUtil.getConnection());
    }
}
