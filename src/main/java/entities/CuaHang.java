package entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CuaHang")
@NamedQuery(name="CuaHang.findAll", query="select c from CuaHang c")

public class CuaHang implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid",parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id")
    private String id;

    @Basic
    @Column(name = "Ma")
    private String ma;

    @Basic
    @Column(name = "Ten")
    private String ten;

    @Basic
    @Column(name = "DiaChi")
    private String diachi;

    @Basic
    @Column(name = "ThanhPho")
    private String thanhpho;

    @Basic
    @Column(name = "QuocGia")
    private String quocgia;

   @OneToMany(mappedBy = "cuaHang")
    private List<NhanVien> nhanViens;


    public CuaHang(String ma, String ten, String diaChi, String thanhPho, String quocGia) {
        this.ma = ma;
        this.ten = ten;
        this.diachi = diaChi;
        this.thanhpho = thanhPho;
        this.quocgia = quocGia;
    }

    public List<NhanVien> getNhanViens() {
        return nhanViens;
    }

    public void setNhanViens(List<NhanVien> nhanViens) {
        this.nhanViens = nhanViens;
    }
}
