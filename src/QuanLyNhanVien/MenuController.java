/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyNhanVien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jungp
 */
public class MenuController {

    UserModel model;
    MenuView view;

    NhanVienView nv = new NhanVienView();
    NhanVienModel nvmodel = new NhanVienModel();

    LoaiNhanVienView loainv = new LoaiNhanVienView();
    LoaiNhanVienModel loainvmodel = new LoaiNhanVienModel();
    
    UserView userview = new UserView();

    public MenuController(UserModel model, MenuView view) {
        this.model = model;
        this.view = view;

        init();
        view.setLocationRelativeTo(null);
        view.setVisible(true);

    }

    public void init() {
        //nhân viên
        view.getBtnNhanVien().addActionListener(nhanvien());
        //loại nhân viên
        view.getBtnLoaiNV().addActionListener(loainhanvien());
        //log out
        view.getBtnLogOut().addActionListener(logout());

    }

    public ActionListener nhanvien() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVienController controll = new NhanVienController(nvmodel, nv);
                view.setVisible(true);
                view.dispose();
            }
        };
    }

    public ActionListener loainhanvien() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoaiNhanVienController controll = new LoaiNhanVienController(loainvmodel, loainv);
                view.setVisible(true);
                view.dispose();
            }
        };
    }
    public ActionListener logout() {
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
