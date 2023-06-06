/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyNhanVien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fff
 */
public class LoaiNhanVienModel {

    private ArrayList<LoaiNV> dsLoaiNV;

    public ArrayList<LoaiNV> getDsLoaiNV() {
        return dsLoaiNV;
    }

    public void setDsLoaiNV(ArrayList<LoaiNV> dsLoaiNV) {
        this.dsLoaiNV = dsLoaiNV;
    }

    public LoaiNhanVienModel() {
        this.dsLoaiNV = new ArrayList<LoaiNV>();
    }

    public LoaiNhanVienModel(ArrayList<LoaiNV> dsLoaiNV) {
        this.dsLoaiNV = dsLoaiNV;
    }

    public void show() {
        System.out.println(LoaiNhanVienDAO.layAllLoaiNhanVien().toString());
    }

    //add vào mảng
    public boolean addList(ArrayList<LoaiNV> loaiNV) {
        boolean kq = dsLoaiNV.addAll(loaiNV);
        return (kq == true);
    }

    //add
    public boolean add(LoaiNV loainv) {
        boolean kq = LoaiNhanVienDAO.add(loainv);
        boolean kq1 = dsLoaiNV.add(loainv);
        return (kq1 == kq == true);
    }

    //delete
    public boolean delete(String maLoai) {
        for (LoaiNV loaiNV : dsLoaiNV) {
            if (loaiNV.getMaLoai().equals(maLoai)) {
                dsLoaiNV.remove(loaiNV);
                return LoaiNhanVienDAO.delete(maLoai);
            }
        }
        return false;
    }

    //update
    public boolean update(LoaiNV loaiNV) {
        for (int i = 0; i < dsLoaiNV.size(); i++) {
            if (dsLoaiNV.get(i).getMaLoai().equals(loaiNV.getMaLoai())) {
                dsLoaiNV.get(i).setTenLoai(loaiNV.getTenLoai());
                dsLoaiNV.get(i).setSoLuong(loaiNV.getSoLuong());
                LoaiNhanVienDAO.update(loaiNV);
                return true;
            }
        }
        return false;
    }

    public ArrayList<LoaiNV> find(String tenLoai) {
        return LoaiNhanVienDAO.find(tenLoai);
    }

    //sắp xếp loại nhân viên theo tên
    public ArrayList<LoaiNV> sapXepLoaiNVTheoTen() {
        ArrayList<LoaiNV> loainv = dsLoaiNV;
        Collections.sort(loainv, new Comparator<LoaiNV>() {
            @Override
            public int compare(LoaiNV l1, LoaiNV l2) {
                return l1.getTenLoai().compareTo(l2.getTenLoai());
            }

        });
        return loainv;
    }

    //sắp xếp loại nhân viên theo số lượng và tên
    public ArrayList<LoaiNV> sapXepLoaiNVTheoSoLuongVaTen() {
        ArrayList<LoaiNV> loainv = dsLoaiNV;
        Collections.sort(loainv, new Comparator<LoaiNV>() {
            @Override
            public int compare(LoaiNV l1, LoaiNV l2) {
                if (l1.getSoLuong() == l2.getSoLuong()) {
                    return l1.getTenLoai().compareTo(l2.getTenLoai());
                } else if (l1.getSoLuong() > l2.getSoLuong()) {
                    return 1;
                } else {
                    return -1;
                }
            }

        });
        return loainv;
    }
    
    //show
    public void showDataTable(LoaiNhanVienView view, LoaiNhanVienModel model) {
        //thêm dữ liệu vào bảng
        DefaultTableModel tb = (DefaultTableModel) view.getTblLoaiNV().getModel();
        ArrayList<LoaiNV> kq = model.dsLoaiNV;
        tb.setColumnCount(0);
        tb.setRowCount(0);
        tb.addColumn("Mã Loại");
        tb.addColumn("Tên Loại");
        tb.addColumn("Số Lượng");
        for (LoaiNV loaiNV : kq) {
            tb.addRow(new Object[]{loaiNV.getMaLoai(), loaiNV.getTenLoai(), loaiNV.getSoLuong()});
        }

    }

}
