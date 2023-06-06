/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyNhanVien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author fff
 */
public class UserController {

    UserModel model;
    UserView view;
    User user;
    SignUpView signup = new SignUpView();
    ChangePassWordView changpassword = new ChangePassWordView();
    MenuView menuView = new MenuView();
    

    //kiểm tra mã có trùng không
    public boolean checkEmpty(String MaNV) {
        if (UserDAO.ktEmpty(MaNV)) {
            return true; // kiểm tra khóa chính chưa tồn tài
        } else {
            return false; // khóa chính tồn tại
        }
    }

    //kiểm tra chuỗi nhập trống
    public boolean ktTrong(String s) {
        if (s.isEmpty()) {
            return true;
        }
        return false;
    }

    public UserController(UserModel model, UserView view) {
        this.model = model;
        this.view = view;

        init();
        view.setLocationRelativeTo(null);
        view.setVisible(true);

    }

    public void init() {
        //login
        view.getBtnLogin().addActionListener(login());
        //signup
        view.getBtnSignUp().addActionListener(signup());
        //changepassword
        view.getBtnForgot().addActionListener(changepassword());

    }

    public ActionListener login() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getTxtUserName().getText();
                String password = view.getTxtPassWord().getText();
                if (ktTrong(username) || ktTrong(password)) {
                    JOptionPane.showMessageDialog(view, "Không được để trống!!!");
                } else if (model.ktDangNhap(username, password) == true) {
                    JOptionPane.showMessageDialog(view, "Đăng nhập thành công");
                    MenuController controller = new MenuController(model,menuView);
                    view.setVisible(true);
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(view, "Username Password Sai!!!");
                }

            }
        };
    }

    public ActionListener signup() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                //đóng bản Userview
                view.setVisible(false);
                view.dispose();
                //hiển thị bảng SignUpView
                SignUpController control = new SignUpController(model,signup);
            }
        };
    }
    public ActionListener changepassword(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //đóng bảng Userview
               view.setVisible(false);
               view.dispose();
               //hiển thị bảng changePasswordView
               ChangePassWordController controll = new ChangePassWordController(model,changpassword);
            }
        };
    }

    public static void main(String[] args) {
        new UserController(new UserModel(), new UserView());
    }

}
