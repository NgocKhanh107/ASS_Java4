package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ChiTietSP")
@NamedQuery(name="ChiTietSp.findAll", query="SELECT c FROM ChiTietSp c")
public class ChiTietSp {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid",parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id")
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdSP")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdNsx")
    private Nsx nsx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdDongSP")
    private DongSp dongSp;

    @Basic
    @Column(name = "NamBH")
    private Integer namBh;
    @Basic
    @Column(name = "MoTa")
    private String moTa;

    @Basic
    @Column(name = "SoLuongTon")
    private Integer soLuongTon;

    @Basic
    @Column(name = "GiaNhap")
    private Integer giaNhap;

    @Basic
    @Column(name = "GiaBan")
    private Integer giaBan;

    public ChiTietSp() {
    }

    public ChiTietSp(SanPham sanPham, Nsx nsx, MauSac mauSac, DongSp dongSp, Integer namBh, String moTa, Integer soLuongTon, Integer giaNhap, Integer giaBan) {
        this.sanPham = sanPham;
        this.nsx = nsx;
        this.mauSac = mauSac;
        this.dongSp = dongSp;
        this.namBh = namBh;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Nsx getNsx() {
        return nsx;
    }

    public void setNsx(Nsx nsx) {
        this.nsx = nsx;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public DongSp getDongSp() {
        return dongSp;
    }

    public void setDongSp(DongSp dongSp) {
        this.dongSp = dongSp;
    }

    public Integer getNamBh() {
        return namBh;
    }

    public void setNamBh(Integer namBh) {
        this.namBh = namBh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
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
}
