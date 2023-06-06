/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyNhanVien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class NhanVienController {

    NhanVienModel model;
    NhanVienView view;
    NhanVien nv;
    MenuView menu = new MenuView();
    UserModel usermodel = new UserModel();

    public void showName(){
        ArrayList<LoaiNV> kq = LoaiNhanVienDAO.layAllLoaiNhanVien();
        
        for (LoaiNV loaiNV : kq) {
            view.getCboMaLoai().addItem(loaiNV.getTenLoai());
        }
    }
    //kiểm tra mã có trùng không
    public boolean checkEmpty(String MaNV) {
        if (NhanVienDAO.ktEmpty(MaNV)) {
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
    
    //lấy giữ liệu đã chọn trong combox
    public String layDtcbo(){
        String kq = view.getCboMaLoai().getSelectedItem().toString();
        ArrayList<LoaiNV> arrkq = new ArrayList<>();
        arrkq = LoaiNhanVienDAO.layAllLoaiNhanVien();
        
        for (LoaiNV loaiNV : arrkq) {
            if (kq.equals(loaiNV.getTenLoai())) {
                return loaiNV.getMaLoai();
            }
        }
        return null;
    }

    public NhanVienController(NhanVienModel model, NhanVienView view) {
        this.model = model;
        this.view = view;

        //thêm dữ liệu  database vào model
        model.addList(NhanVienDAO.layAllNhanVien());

        model.showDataTable(view, model);

        showName();
        init();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public void init() {

        //add
        view.getBtnAdd().addActionListener(add());
        //delete
        view.getBtnDelete().addActionListener(delete());

        //update
        view.getBtnUpdate().addActionListener(update());
        //reset
        view.getBtnReSet().addActionListener(reset());
        //find
        view.getBtnFind().addActionListener(find());
        //sort
        view.getCbosort().addActionListener(sort());
        //table
        view.getTblNhanVien().addMouseListener(tableListenenr());
        //trở lại
        view.getBtnTroLai().addActionListener(troLai());
    }

    public MouseAdapter tableListenenr() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = view.getTblNhanVien().getSelectedRow();
                String maNV = view.getTblNhanVien().getValueAt(index, 0).toString();
                String hoTenNV = view.getTblNhanVien().getValueAt(index, 1).toString();
                String ngaySinh = view.getTblNhanVien().getValueAt(index, 2).toString();
                String gioiTinh = view.getTblNhanVien().getValueAt(index, 4).toString();
                String luongCB = view.getTblNhanVien().getValueAt(index, 5).toString();
                String soGioLam = view.getTblNhanVien().getValueAt(index, 6).toString();

                view.getTxtMaNV().setText(maNV);
                view.getTxtHoTenNV().setText(hoTenNV);
                view.getTxtNgaySinh().setText(ngaySinh);
                view.getTxtGioiTinh().setText(gioiTinh);
                view.getTxtLuongCB().setText(luongCB);
                view.getTxtSoGioLam().setText(soGioLam);

            }
        };

    }
    //add
    public ActionListener add() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maNV = view.getTxtMaNV().getText();
                String hoTenNV = view.getTxtHoTenNV().getText();
                String ngaySinh = view.getTxtNgaySinh().getText();
                String maLoai = layDtcbo();
                String gioiTinh = view.getTxtGioiTinh().getText();
                String luongCB = view.getTxtLuongCB().getText();
                String soGioLam = view.getTxtSoGioLam().getText();
                //kiểm tra nhập dữ liệu vào chưa
                if (ktTrong(maNV) || ktTrong(hoTenNV) || ktTrong(ngaySinh) || ktTrong(gioiTinh) || ktTrong(luongCB) || ktTrong(soGioLam)) {
                    JOptionPane.showMessageDialog(view, "Không được để trống!!!");
                    return;//end
                }
                //kiểm tra khóa chính
                if (checkEmpty(maNV) == false) {
                    JOptionPane.showMessageDialog(view, "Trùng khóa chính!!!");
                    return;//end
                }
                //kiểm tra định dạng số
                if (ktDinhDang(luongCB) == false || ktDinhDang(soGioLam) == false) {
                    JOptionPane.showMessageDialog(view, "Nhập sai định dạng!!!");
                    return; //end
                }
                try {
                    nv = new NhanVien(maNV, hoTenNV, ngaySinh, maLoai, gioiTinh, luongCB, soGioLam);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
                model.add(nv);
                model.showDataTable(view, model);
            }
        };
    }
    //delete
    public ActionListener delete() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maNV = view.getTxtMaNV().getText();
                boolean kq = model.delete(maNV);
                if (kq == false) {
                    JOptionPane.showMessageDialog(view, "Không xóa duoc!!!");
                    return; //end
                } else {
                    JOptionPane.showMessageDialog(view, "xóa thanh cong!!!");
                    model.showDataTable(view, model);
                    view.getTxtMaNV().setText("");
                    view.getTxtHoTenNV().setText("");
                    view.getTxtNgaySinh().setText("");
                    view.getTxtGioiTinh().setText("");
                    view.getTxtLuongCB().setText("");
                    view.getTxtSoGioLam().setText("");

                }
            }
        };
    }
    //update
    public ActionListener update() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maNV = view.getTxtMaNV().getText();
                String hoTenNV = view.getTxtHoTenNV().getText();
                String ngaySinh = view.getTxtNgaySinh().getText();
                String maLoai = layDtcbo();
                String gioiTinh = view.getTxtGioiTinh().getText();
                String luongCB = view.getTxtLuongCB().getText();
                String soGioLam = view.getTxtSoGioLam().getText();
                //kiểm tra nhập dữ liệu vào chưa
                if (ktTrong(maNV) || ktTrong(hoTenNV) || ktTrong(ngaySinh) || ktTrong(gioiTinh) || ktTrong(luongCB) || ktTrong(soGioLam)) {
                    JOptionPane.showMessageDialog(view, "Không được để trống!!!");
                    return;//end
                }
                //kiểm tra định dạng số
                if (ktDinhDang(luongCB) == false || ktDinhDang(soGioLam) == false) {
                    JOptionPane.showMessageDialog(view, "Nhập sai định dạng!!!");
                    return; //end
                }
                try {
                    nv = new NhanVien(maNV, hoTenNV, ngaySinh, maLoai, gioiTinh, luongCB, soGioLam);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
                model.update(nv);
                model.showDataTable(view, model);
            }
        };
    }
    //reset
    public ActionListener reset() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTxtMaNV().setText("");
                view.getTxtHoTenNV().setText("");
                view.getTxtNgaySinh().setText("");
                view.getTxtGioiTinh().setText("");
                view.getTxtLuongCB().setText("");
                view.getTxtSoGioLam().setText("");
                NhanVienModel kq = new NhanVienModel();
                kq.addList(NhanVienDAO.layAllNhanVien());
                model.showDataTable(view, kq);
            }
        };
    }
    //find
    public ActionListener find() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<NhanVien> nv = new ArrayList<>();
                nv = model.find(view.getTxtFind().getText());
                
                if (ktTrong(view.getTxtFind().getText())) {
                    JOptionPane.showMessageDialog(view, "Không được để trống!!!");
                    return;//end
                } else if (nv.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Không tim thấy!!!");
                } else {
                    NhanVienModel kq1 = new NhanVienModel();
                    kq1.addList(nv);
                    model.showDataTable(view, kq1);
                    
                }
            }
        };
    }
    //sắp xếp theo tên
    public ActionListener sort(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVienModel kqmodel = new NhanVienModel();
                if(view.getCbosort().getSelectedIndex()== 0){
                    kqmodel.addList(model.sapXepTheoTen());
                    model.showDataTable(view, kqmodel);
                }
                if(view.getCbosort().getSelectedIndex()== 1){
                    kqmodel.addList(model.sapXepTheoLuongVaTen());
                    model.showDataTable(view, kqmodel);
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
               MenuController controll = new MenuController(usermodel,menu);
            }
        };
    }
}
