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
 * @author jungp
 */
public class ChangePassWordController {

    UserModel model;
    ChangePassWordView view;
    UserView userview = new UserView();

    //kiểm tra mã có trùng không
    public boolean checkEmpty(String username) {
        if (UserDAO.ktEmpty(username)) {
            return true; // kiểm tra khóa chính chưa tồn tài
        } else {
            return false; // khóa chính tồn tại
        }
    }
    //kiểm tra pass word
    public boolean checkPassword(String password) {
        if (UserDAO.ktPassWord(password)) {
            return true; // kiểm tra password chưa tồn tài
        } else {
            return false; // khóa password tồn tại
        }
    }
    //kiểm tra chuỗi nhập trống
    public boolean ktTrong(String s) {
        if (s.isEmpty()) {
            return true;
        }
        return false;
    }

    public ChangePassWordController(UserModel model, ChangePassWordView view) {
        this.model = model;
        this.view = view;

        init();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public void init() {
        view.getBtnOk().addActionListener(changepassword());
        view.getBtnTroLai().addActionListener(troLai());
    }

    public ActionListener changepassword() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getTxtUserName().getText();
                String oldpass = view.getTxtOldPass().getText();
                String newPass = view.getTxtNewPass().getText();
                if (ktTrong(username) || ktTrong(oldpass) || ktTrong(newPass)) {
                    JOptionPane.showMessageDialog(view, "Không được để trống");
                    return; // end
                } else if (checkEmpty(username) == true || checkPassword(oldpass) == true) {
                    JOptionPane.showMessageDialog(view, "Username password không đúng");
                    return; //end

                } else {
                    model.doiMatKhau(username, oldpass, newPass);
                    JOptionPane.showMessageDialog(view, "Đổi password thành công");
                }
            }
        };
    }

    public ActionListener troLai() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserController controll = new UserController(model, userview);
                view.setVisible(true);
                view.dispose();
            }
        };
    }

}
