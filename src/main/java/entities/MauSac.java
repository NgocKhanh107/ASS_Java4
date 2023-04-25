package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "MauSac")
@NamedQuery(name="MauSac.findAll", query="select c from MauSac c")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MauSac {

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

    @OneToMany(mappedBy = "mauSac")
    private List<ChiTietSp> listCTSP;

    public MauSac(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public List<ChiTietSp> getListCTSP() {
        return listCTSP;
    }

    public void setListCTSP(List<ChiTietSp> listCTSP) {
        this.listCTSP = listCTSP;
    }
}