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
@Table(name="HoaDon")
@NamedQuery(name="HoaDon.findAll", query="SELECT c FROM HoaDon c")
public class HoaDon {


    @Id
    @GenericGenerator(name = "generator", strategy = "guid",parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdKH")
    private KhachHang khachHang;

    @Basic
    @Column(name = "Ma")
    private String ma;

    @Basic
    @Column(name = "NgayTao")
    private Date ngayTao;


}
