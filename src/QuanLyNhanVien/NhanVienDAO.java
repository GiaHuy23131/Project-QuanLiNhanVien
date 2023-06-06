/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyNhanVien;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author fff
 */
public class NhanVienDAO {
    //Thêm nhân viên
    public static boolean add(NhanVien nv) {
        int kq = -1;
        try {
            String sqlInsert = "INSERT INTO nhanvien(MaNV, TenNV, NgaySinh, Loai, GioiTinh, LuongCB, soGioLam) VALUES(?, ?, ?, ?, ?, ?, ?)"; 
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTenNV());
            ps.setDate(3,java.sql.Date.valueOf(nv.getNgaySinh()));
            ps.setString(4, nv.getMaLoai());
            ps.setString(5, nv.getGioiTinh());
            ps.setDouble(6, nv.getLuongCB());
            ps.setInt(7, nv.getSoGioLam());

            kq = ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return (kq != -1);
    }
    //Xóa nhân viên
    public static boolean delete(String maNV) {
        int kq = -1;
        try {
            String sqlDelete = "DELETE FROM nhanvien WHERE maNV = ?";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlDelete);

            ps.setString(1, maNV);

            kq = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (kq != -1);
    }
    //Sửa nhân viên
    public static boolean update(NhanVien nv) {
        int kq = -1;
        try {
            String sqlUpdate = "UPDATE nhanvien SET TenNV= ?, NgaySinh= ?, Loai= ?,GioiTinh= ?, LuongCB= ?,SoGioLam= ? WHERE MaNV= ?";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlUpdate);
            
            ps.setString(1, nv.getHoTenNV());
            ps.setDate(2,java.sql.Date.valueOf(nv.getNgaySinh()));
            ps.setString(3, nv.getMaLoai());
            ps.setString(4, nv.getGioiTinh());
            ps.setDouble(5, nv.getLuongCB());
            ps.setInt(6, nv.getSoGioLam());
            
            ps.setString(7,nv.getMaNV());
            
            kq = ps.executeUpdate();
            
        } catch (Exception e) {
            e.getStackTrace();
        }
        return (kq != -1);
    }
    //Tìm nhân viên
    public static ArrayList<NhanVien> find(String HoTenNV){
        ArrayList<NhanVien> array = new ArrayList<NhanVien>();
        try {
            String sqlFind = "SELECT * FROM nhanvien WHERE nhanvien.TenNV LIKE CONCAT ('%',?,'%')";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlFind);
            
            ps.setString(1,HoTenNV);
                       
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(new NhanVien(rs.getString(1),rs.getString(2), rs.getDate(3).toString(), rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7)));
            }
            
        } catch (Exception e) {
            e.getStackTrace();
        }
        return array;
    }
    //lấy tất cả danh sách nhân viên
    public static ArrayList<NhanVien> layAllNhanVien(){
        ArrayList<NhanVien> dskq = new ArrayList<NhanVien>();
        String sqlSelect = "SELECT * FROM nhanvien";
        
        try {
            Connection con = DatabaseUtil.getConnection(); 
            PreparedStatement ps =  con.prepareStatement(sqlSelect);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                NhanVien sv = new NhanVien(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7));
                dskq.add(sv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dskq;
    }
    //tìm khóa chính
    public static boolean ktEmpty(String maNV){
        ArrayList<NhanVien> array = new ArrayList<NhanVien>();
        try {
            String sqlFind = "SELECT * FROM nhanvien WHERE nhanvien.MaNV=?";
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlFind);
            
            ps.setString(1,maNV);
                       
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(new NhanVien(rs.getString(1),rs.getString(2), rs.getDate(3).toString(), rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7)));
            }
            
        } catch (Exception e) {
            e.getStackTrace();
        }
        if(array.isEmpty()){
            return true; // khóa chính chưa tồn tại
        }
        else{
            return false; // khóa chính tồn tại
        }
    }
}
