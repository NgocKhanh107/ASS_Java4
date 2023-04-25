package viewmodel;

import entities.ChiTietSp;
import entities.HoaDon;

public class giohangViewModel {
    private HoaDon hoaDon;
    private ChiTietSp chiTietSp;
    private int soLuong;
    private double donGia;

    public giohangViewModel(HoaDon hoaDon, ChiTietSp chiTietSp, int soLuong, double donGia) {
        this.hoaDon = hoaDon;
        this.chiTietSp = chiTietSp;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public giohangViewModel() {
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietSp getChiTietSp() {
        return chiTietSp;
    }

    public void setChiTietSp(ChiTietSp chiTietSp) {
        this.chiTietSp = chiTietSp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
