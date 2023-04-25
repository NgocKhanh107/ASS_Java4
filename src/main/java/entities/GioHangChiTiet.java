package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GioHangChiTiet")
@IdClass(GioHangChiTietPK.class)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class GioHangChiTiet {

    @Id
    @ManyToOne
    @JoinColumn(name = "IdGioHang", nullable = false)
    private GioHang idGioHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdChiTietSP", nullable = false)
    private ChiTietSp idChiTietSp;


    @Basic
    @Column(name = "SoLuong")
    private Integer soLuong;

    @Basic
    @Column(name = "DonGia")
    private Integer donGia;

    @Basic
    @Column(name = "DonGiaKhiGiam")
    private Integer donGiaKhiGiam;


}
