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
public class LoaiNhanVienDAO {

    //thêm loại nhân viên
    public static boolean add(LoaiNV loaiNV) {
        int kq = -1;
        try {
            String sqlInsert = "INSERT INTO loainv (MaLoai, TenLoai, SoLuong) VALUES (?, ?, ?)";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setString(1, loaiNV.getMaLoai());
            ps.setString(2, loaiNV.getTenLoai());
            ps.setInt(3, loaiNV.getSoLuong());

            kq = ps.executeUpdate();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return (kq != -1);
    }

    //Xóa loai nhân viên
    public static boolean delete(String maLoai) {
        int kq = -1;
        try {
            String sqlDelete = "DELETE FROM loainv WHERE maLoai = ?";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlDelete);

            ps.setString(1, maLoai);

            kq = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (kq != -1);
    }

    //Sửa nhân viên
    public static boolean update(LoaiNV loaiNV) {
        int kq = -1;
        try {
            String sqlUpdate = "UPDATE loainv SET TenLoai= ?, SoLuong= ? WHERE MaLoai= ?";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlUpdate);

            ps.setString(1, loaiNV.getTenLoai());
            ps.setInt(2, loaiNV.getSoLuong());
            ps.setString(3, loaiNV.getMaLoai());

            kq = ps.executeUpdate();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return (kq != -1);
    }

    //Tìm nhân viên
    public static ArrayList<LoaiNV> find(String TenLoai) {
        ArrayList<LoaiNV> array = new ArrayList<LoaiNV>();
        try {
            String sqlFind = "SELECT * FROM loainv WHERE loainv.TenLoai LIKE CONCAT ('%',?,'%')";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlFind);
            ps.setString(1,TenLoai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LoaiNV Loainv = new LoaiNV(rs.getString(1),
                        rs.getString(2), rs.getInt(3));
                array.add(Loainv);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return array;
    }

    //lấy tất cả danh sách nhân viên
    public static ArrayList<LoaiNV> layAllLoaiNhanVien(){
        ArrayList<LoaiNV> dskq = new ArrayList<LoaiNV>();
        String sqlSelect = "SELECT * FROM loainv";
        
        try {
            Connection con = DatabaseUtil.getConnection(); 
            PreparedStatement ps =  con.prepareStatement(sqlSelect);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                LoaiNV loainv = new LoaiNV(rs.getString(1),rs.getString(2), rs.getInt(3));
                dskq.add(loainv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dskq;
    }

    //tìm khóa chính
    public static boolean ktEmpty(String HoTenNV) {
        ArrayList<LoaiNV> array = new ArrayList<LoaiNV>();
        try {
            String sqlFind = "SELECT * FROM loainv WHERE loainv.MaLoai=?";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlFind);

            ps.setString(1, HoTenNV);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(new LoaiNV(rs.getString(1), rs.getString(2), rs.getInt(3)));
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

}
