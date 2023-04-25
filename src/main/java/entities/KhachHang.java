package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="KhachHang")
@NamedQuery(name="KhachHang.findAll", query="SELECT c FROM KhachHang c")
public class KhachHang {

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
    @Column(name = "TenDem")
    private String tenDem;

    @Basic
    @Column(name = "Ho")
    private String ho;

    @Basic
    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Basic
    @Column(name = "Sdt")
    private String sdt;

    @Basic
    @Column(name = "DiaChi")
    private String diaChi;

    @Basic
    @Column(name = "ThanhPho")
    private String thanhPho;

    @Basic
    @Column(name = "QuocGia")
    private String quocGia;

    @Basic
    @Column(name = "MatKhau")
    private String matKhau;

}
