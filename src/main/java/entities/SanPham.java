package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "SanPham")
@NamedQuery(name="SanPham.findAll", query="select c from SanPham c")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPham {

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
    @Column(name = "Images")
    private String images;

    @OneToMany(mappedBy = "sanPham")
    private List<ChiTietSp> listCTSP;

    public SanPham(String ma, String ten, String images) {
        this.ma = ma;
        this.ten = ten;
        this.images = images;
    }

    public List<ChiTietSp> getListCTSP() {
        return listCTSP;
    }

    public void setListCTSP(List<ChiTietSp> listCTSP) {
        this.listCTSP = listCTSP;
    }
}