/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyNhanVien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jungp
 */
public class NhanVienModel {

    private ArrayList<NhanVien> nhanvien;

    public ArrayList<NhanVien> getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(ArrayList<NhanVien> nhanvien) {
        this.nhanvien = nhanvien;
    }

    public NhanVienModel() {
        this.nhanvien = new ArrayList<NhanVien>();
    }

    public NhanVienModel(ArrayList<NhanVien> nhanvien) {
        this.nhanvien = nhanvien;
    }

    //show nhân viên
    public void show() {
        System.out.println(NhanVienDAO.layAllNhanVien().toString());
    }
    //thêm vào mảng
    public boolean addList(ArrayList<NhanVien> nv) {
        boolean kq1 = nhanvien.addAll(nv);
        return (kq1 == true);
    }
    //add nhân viên
    public boolean add(NhanVien nv) {
        boolean kq = NhanVienDAO.add(nv);
        boolean kq1 = nhanvien.add(nv);
        return (kq1 == kq == true);
    }

    //xóa nhân viên
    public boolean delete(String maNV) {
        for (NhanVien nhanVien : nhanvien) {
            if (nhanVien.getMaNV().equals(maNV)) {
                nhanvien.remove(nhanVien);
                return NhanVienDAO.delete(maNV);
            }
        }
        return false;
    }

    //update nhân viên
    public boolean update(NhanVien nv) {
        for (int i = 0; i < nhanvien.size(); i++) {
            if (nhanvien.get(i).getMaNV().equals(nv.getMaNV())) {
                nhanvien.get(i).setHoTenNV(nv.getHoTenNV());
                nhanvien.get(i).setNgaySinh(nv.getNgaySinh());
                nhanvien.get(i).setGioiTinh(nv.getGioiTinh());
                nhanvien.get(i).setLuongCB(nv.getLuongCB());   
                nhanvien.get(i).setSoGioLam(nv.getSoGioLam());
                NhanVienDAO.update(nv);
                return true;
            }
        }
        return false;
    }

    //Find nhân viên
    public ArrayList<NhanVien> find(String maNV) {
        return NhanVienDAO.find(maNV);
    }

    //Sắp xếp theo Tên
    public ArrayList<NhanVien> sapXepTheoTen() {
        ArrayList<NhanVien> nv = nhanvien;
        Collections.sort(nv, new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien nv1, NhanVien nv2) {
                return nv1.getHoTenNV().compareTo(nv2.getHoTenNV());
            }
        });
        return nv;
    }

    //Sắp xếp theo Luong và theo ten
    public ArrayList<NhanVien> sapXepTheoLuongVaTen() {
        ArrayList<NhanVien> nv = (ArrayList<NhanVien>) nhanvien.clone();

        Collections.sort(nv, new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien nv1, NhanVien nv2) {
                if (nv1.getLuongCB() == nv2.getLuongCB()) {
                    return nv1.getHoTenNV().compareTo(nv2.getHoTenNV());
                } else if (nv1.getLuongCB() > nv2.getLuongCB()) {
                    return 1;
                } else {
                    return -1;
                }

            }
        });
        return nv;
    }

    

    public void showDataTable(NhanVienView view, NhanVienModel model) {
        //thêm dữ liệu vào bảng
        DefaultTableModel tb = (DefaultTableModel) view.getTblNhanVien().getModel();
        ArrayList<NhanVien> kq = model.getNhanvien();
        tb.setColumnCount(0);
        tb.setRowCount(0);
        tb.addColumn("Mã NV");
        tb.addColumn("Họ Tên NV");
        tb.addColumn("Ngày Sinh");
        tb.addColumn("Mã Loại");
        tb.addColumn("Giới Tính");
        tb.addColumn("Lương CB");
        tb.addColumn("Số Giờ Làm");
        for (NhanVien nv : kq) {
            tb.addRow(new Object[]{nv.getMaNV(), nv.getHoTenNV(), nv.getNgaySinh(), nv.getMaLoai(), nv.getGioiTinh(), nv.getLuongCB(), nv.getSoGioLam()});
        }

    }

    
}
