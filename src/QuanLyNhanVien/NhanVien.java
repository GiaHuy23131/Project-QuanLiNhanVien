/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package QuanLyNhanVien;

import java.text.ParseException;
import java.time.LocalDate;

/**
 *
 * @author Administrator
 */
public class NhanVien {
    private String maNV;
    private String hoTenNV;
    private LocalDate ngaySinh;
    private String maLoai;
    private String gioiTinh;
    private double luongCB;
    private int soGioLam;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTenNV, String ngaySinh, String maLoai, String gioiTinh, String luongCB,String soGioLam) throws ParseException {
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.ngaySinh = LocalDate.parse(ngaySinh);
        this.maLoai = maLoai;
        this.gioiTinh = gioiTinh;
        this.luongCB = Double.parseDouble(luongCB);
        this.soGioLam = Integer.parseInt(soGioLam);
    }
    
    
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }


    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public double getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(double luongCB) {
        this.luongCB = luongCB;
    }
    
    public int getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(int soGioLam) {
        this.soGioLam = soGioLam;
    }
    
    public double tinhLuong(){
        return luongCB * soGioLam;
    }
    public String toString() {
        return maNV + " - " + hoTenNV + " - " + ngaySinh + " - " + maLoai + " - " + gioiTinh + " - " + luongCB + " - " + soGioLam + "\n";
    }
    
}
