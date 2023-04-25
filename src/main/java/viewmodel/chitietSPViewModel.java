package viewmodel;

public class chitietSPViewModel {
    private String id;
    private int namBh;
    private String moTa;
    private int soLuongTon;
    private Integer giaNhap;
    private Integer giaBan;
    private String mauSacID;
    private String dspID;
    private String nsxID;
    private String sanPhamID;

    public chitietSPViewModel() {
    }

    public chitietSPViewModel(String id, int namBh, String moTa, int soLuongTon, Integer giaNhap, Integer giaBan, String mauSacID, String dspID, String nsxID, String sanPhamID) {
        this.id = id;
        this.namBh = namBh;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.mauSacID = mauSacID;
        this.dspID = dspID;
        this.nsxID = nsxID;
        this.sanPhamID = sanPhamID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNamBh() {
        return namBh;
    }

    public void setNamBh(int namBh) {
        this.namBh = namBh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Integer getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Integer giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Integer getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Integer giaBan) {
        this.giaBan = giaBan;
    }

    public String getMauSacID() {
        return mauSacID;
    }

    public void setMauSacID(String mauSacID) {
        this.mauSacID = mauSacID;
    }

    public String getDspID() {
        return dspID;
    }

    public void setDspID(String dspID) {
        this.dspID = dspID;
    }

    public String getNsxID() {
        return nsxID;
    }

    public void setNsxID(String nsxID) {
        this.nsxID = nsxID;
    }

    public String getSanPhamID() {
        return sanPhamID;
    }

    public void setSanPhamID(String sanPhamID) {
        this.sanPhamID = sanPhamID;
    }
}
