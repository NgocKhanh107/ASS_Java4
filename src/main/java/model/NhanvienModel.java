package model;

import entities.ChucVu;
import entities.CuaHang;

import java.sql.Date;

public class NhanvienModel {
    private String ma;
    private String ten;
    private String tendem;
    private String ho;
    private String gioitinh;
    private String ngaysinh;
    private String diachi;
    private String sdt;
    private String matkhau;
    private String cuaHangID;
    private String chucVuID;

    public NhanvienModel() {
    }

    public NhanvienModel(String ma, String ten, String tendem, String ho, String gioitinh, String ngaysinh, String diachi, String sdt, String matkhau,String cuaHangID, String chucVuID) {
        this.ma = ma;
        this.ten = ten;
        this.tendem = tendem;
        this.ho = ho;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.matkhau = matkhau;
        this.cuaHangID = cuaHangID;
        this.chucVuID = chucVuID;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTendem() {
        return tendem;
    }

    public void setTendem(String tendem) {
        this.tendem = tendem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCuaHangID() {
        return cuaHangID;
    }

    public void setCuaHangID(String cuaHangID) {
        this.cuaHangID = cuaHangID;
    }

    public String getChucVuID() {
        return chucVuID;
    }

    public void setChucVuID(String chucVuID) {
        this.chucVuID = chucVuID;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
