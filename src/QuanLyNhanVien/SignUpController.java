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
public class SignUpController {

    UserModel model;
    User user;
    SignUpView signup;
    UserView view = new UserView();

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

    public SignUpController(UserModel model, SignUpView signup) {
        this.model = model;
        this.signup = signup;

        init();
        view.setLocationRelativeTo(null);
        signup.setVisible(true);

    }

    public void init() {
        signup.getBtnSignUp().addActionListener(signup1());
        signup.getBtnTroVe().addActionListener(troVe());

    }
    public ActionListener signup1() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String hoTen = signup.getTxtHoTen().getText();
                String username = signup.getTxtUserName().getText();
                String password = signup.getTxtPassWord().getText();
                if (ktTrong(hoTen) || ktTrong(username) || ktTrong(password)) {
                    JOptionPane.showMessageDialog(signup, "Không được để trống!!!");
                    return; //end
                } else if (checkEmpty(username) == false) {
                    JOptionPane.showMessageDialog(signup, "Trùng khóa chính!!!");
                    return; //end
                } else {
                    user = new User(username, password, hoTen);
                    model.dangKy(user);
                    JOptionPane.showMessageDialog(signup, "Đăng ký thành công!!!");
                }
            }
        };
    }

    public ActionListener troVe() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup.setVisible(false);
                signup.dispose();
                UserController controll = new UserController(model,view);
            }
        };
    }
}
