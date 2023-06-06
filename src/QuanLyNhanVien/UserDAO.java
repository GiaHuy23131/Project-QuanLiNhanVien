/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyNhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author fff
 */
public class UserDAO {

    public static boolean ktDangNhap(String username, String password) {
        try {
            String sqlInsert = "SELECT * FROM user WHERE username =? AND password =?";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean dangKy(User user) {
        int kq = -1;
        try {
            String sqlInsert = "INSERT INTO user VALUES(?,?,?)";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getHoTen());

            kq = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (kq != -1);
    }

    public static boolean doiMatKhau(String username, String oldpass, String newpass) {
        int kq = -1;
        try {
            String sqlInsert = "UPDATE user SET password=? WHERE username=? AND password=?";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setString(1, newpass);
            ps.setString(2, username);
            ps.setString(3, oldpass);
            
            kq = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (kq != -1);
    }

    //check username
    public static boolean ktEmpty(String username) {
        ArrayList<User> array = new ArrayList<User>();
        try {
            String sqlFind = "SELECT * FROM user WHERE user.username=?";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlFind);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        if (array.isEmpty()) {
            return true; // khóa chính chưa tồn tại
        } else {
            return false; // khóa chính tồn tại
        }
    }
    //check password
    public static boolean ktPassWord(String password){
        ArrayList<User> array = new ArrayList<User>();
        try{
            String sqlktpassword = "SELECT * FROM user WHERE user.password=?";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlktpassword);

            ps.setString(1, password);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        if (array.isEmpty()) {
            return true; // khóa chính chưa tồn tại
        } else {
            return false; // khóa chính tồn tại
        }
    }
}
