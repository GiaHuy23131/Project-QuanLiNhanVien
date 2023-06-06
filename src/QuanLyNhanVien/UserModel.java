/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyNhanVien;

import java.util.ArrayList;

/**
 *
 * @author fff
 */
public class UserModel {

    private ArrayList<User> user;

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }

    public UserModel(ArrayList<User> user) {
        this.user = user;
    }

    public UserModel() {
        this.user = new ArrayList<User>();
    }

    public boolean ktDangNhap(String username, String password) {
        return UserDAO.ktDangNhap(username, password);
    }

    public boolean dangKy(User use) {
        boolean kq = UserDAO.dangKy(use);
        boolean kq1 = user.add(use);
        return (kq = kq1 = true);

    }

    public boolean doiMatKhau(String username, String oldpass, String newpass) {
        return UserDAO.doiMatKhau(username, oldpass, newpass);
    }

}
