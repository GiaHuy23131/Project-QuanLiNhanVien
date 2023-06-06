/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyNhanVien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author fff
 */
public class LoaiNhanVienController {

    LoaiNhanVienModel model;
    LoaiNhanVienView view;
    LoaiNV loainv;
    MenuView menuview = new MenuView();
    UserModel usermodel = new UserModel();

    //kiểm tra mã có trùng không
    public boolean checkEmpty(String maLoai) {
        if (LoaiNhanVienDAO.ktEmpty(maLoai)) {
            return true;
        }
        return false;
    }

    //kiểm tra khoảng trống
    public boolean ktTrong(String s) {
        if (s.isEmpty()) {
            return true;
        }
        return false;
    }

    //kiểm tra định dạng
    public boolean ktDinhDang(String s) {
        if (s.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (Character.isLetter(s.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public LoaiNhanVienController(LoaiNhanVienModel model, LoaiNhanVienView view) {
        this.model = model;
        this.view = view;
        //thêm dữ liệu database vào model
        model.addList(LoaiNhanVienDAO.layAllLoaiNhanVien());
        
        model.showDataTable(view, model);
        init();
        view.setLocationRelativeTo(null);
        view.setVisible(true);

    }

    public void init() {
        view.getTblLoaiNV().addMouseListener(tableListenenr());
        //add
        view.getBtnAdd().addActionListener(add());
        //delete
        view.getBtnDelete().addActionListener(delele());
        //update
        view.getBtnUpdate().addActionListener(update());
        //find
        view.getBtnfind().addActionListener(find());
        //reset
        view.getBtnReset().addActionListener(reset());
        //sort
        view.getCboSort().addActionListener(sort());
        //trở lại
        view.getBtnTroLai().addActionListener(troLai());

    }

    public MouseAdapter tableListenenr() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = view.getTblLoaiNV().getSelectedRow();
                String maloai = view.getTblLoaiNV().getValueAt(index, 0).toString();
                String tenLoai = view.getTblLoaiNV().getValueAt(index, 1).toString();
                String soLuong = view.getTblLoaiNV().getValueAt(index, 2).toString();

                view.getTxtMaLoai().setText(maloai);
                view.getTxtTenLoai().setText(tenLoai);
                view.getTxtSoLuong().setText(soLuong);
            }
        };
    }
    //add
    public ActionListener add(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLoai = view.getTxtMaLoai().getText();
                String tenLoai = view.getTxtTenLoai().getText();
                String soLuong = view.getTxtSoLuong().getText();
                
                if(ktTrong(maLoai) || ktTrong(tenLoai) || ktTrong(soLuong)){
                    JOptionPane.showMessageDialog(view, "Không được để trống!!!");
                    return;//end
                }
                if(checkEmpty(maLoai) == false){
                    JOptionPane.showMessageDialog(view, "Trùng khóa chính!!!");
                }
                if(ktDinhDang(soLuong) == false){
                    JOptionPane.showMessageDialog(view, "Nhập sai định dạng!!!");
                }
                loainv = new LoaiNV(maLoai,tenLoai,Integer.parseInt(soLuong));
                model.add(loainv);
                model.showDataTable(view, model);
            }
        };
    }
    //delete
    public ActionListener delele(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLoai = view.getTxtMaLoai().getText();
                boolean kq = model.delete(maLoai);
                if(kq == false){
                    JOptionPane.showMessageDialog(view, "Xóa không thành công!!!");
                    return;//end
                }
                else{
                    JOptionPane.showMessageDialog(view, "Xóa thành công!!!");
                    model.showDataTable(view, model);
                    view.getTxtMaLoai().setText("");
                    view.getTxtTenLoai().setText("");
                    view.getTxtSoLuong().setText("");
                }
            }
        };
    }
    //update
    public ActionListener update(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLoai = view.getTxtMaLoai().getText();
                String tenLoai = view.getTxtTenLoai().getText();
                String soLuong = view.getTxtSoLuong().getText();
                
                if(ktTrong(maLoai) || ktTrong(tenLoai) || ktTrong(soLuong)){
                    JOptionPane.showMessageDialog(view, "Không được để trống!!!");
                    return;//end
                }
                if(ktDinhDang(soLuong) == false){
                    JOptionPane.showMessageDialog(view, "Nhập sai định dạng!!!");
                }
                loainv = new LoaiNV(maLoai,tenLoai,Integer.parseInt(soLuong));
                model.update(loainv);
                model.showDataTable(view, model);
            }
        };
    }
    //find
    public ActionListener find(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<LoaiNV> lnv = new ArrayList<LoaiNV>();
                String ifind = view.getTxtFind().getText();
                lnv = model.find(ifind);
                if(ktTrong(ifind)){
                    JOptionPane.showMessageDialog(view, "Không được để trống!!!");
                    return; //end
                }
                else if(lnv.isEmpty()){
                    JOptionPane.showMessageDialog(view, "Không tìm thấy!!!");
                }
                else{
                    LoaiNhanVienModel kq = new LoaiNhanVienModel();
                    kq.addList(lnv);
                    model.showDataTable(view, kq);         
                }
            }
        };
    }
    //reset 
    public ActionListener reset(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTxtMaLoai().setText("");
                view.getTxtMaLoai().setText("");
                view.getTxtMaLoai().setText("");
                
                LoaiNhanVienModel lnv = new LoaiNhanVienModel();
                lnv.addList(LoaiNhanVienDAO.layAllLoaiNhanVien());
                model.showDataTable(view, lnv);
            }
        };
    }
    //sort
    public ActionListener sort(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoaiNhanVienModel lnv = new LoaiNhanVienModel();
               if(view.getCboSort().getSelectedIndex() == 0){
                   lnv.addList(model.sapXepLoaiNVTheoTen());
                   model.showDataTable(view, lnv);
               }
               if(view.getCboSort().getSelectedIndex() == 1){
                   lnv.addList(model.sapXepLoaiNVTheoSoLuongVaTen());
                   model.showDataTable(view, lnv);
               } 
            }
        };
    }
    public ActionListener troLai(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //đóng bảng Userview
               view.setVisible(false);
               view.dispose();
               //hiển thị bảng changePasswordView
               MenuController controll = new MenuController(usermodel,menuview);
            }
        };
    }
}
