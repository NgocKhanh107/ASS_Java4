package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HoaDonChiTiet")
@IdClass(HoaDonChiTietPK.class)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class HoaDonChiTiet {

    @Id
    @ManyToOne
    @JoinColumn(name = "IdHoaDon", nullable = false)
    private HoaDon idHoaDon;


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


}
