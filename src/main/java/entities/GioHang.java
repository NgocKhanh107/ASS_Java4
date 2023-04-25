package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="GioHang")
@NamedQuery(name="GioHang.findAll", query="SELECT c FROM GioHang c")
public class GioHang {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid",parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id")
    private String id;

    @Basic
    @Column(name = "Ma")
    private String ma;

    @Basic
    @Column(name = "NgayTao")
    private Date ngayTao;

    @Basic
    @Column(name = "NgayThanhToan")
    private Date ngayThanhToan;

    @Basic
    @Column(name = "TenNguoiNhan")
    private String tenNguoiNhan;

    @Basic
    @Column(name = "DiaChi")
    private String diaChi;

    @Basic
    @Column(name = "Sdt")
    private String sdt;

    @Basic
    @Column(name = "TinhTrang")
    private Integer tinhTrang;

    @ManyToOne
    @JoinColumn(name = "IdKH")
    private KhachHang khachHang;

}
