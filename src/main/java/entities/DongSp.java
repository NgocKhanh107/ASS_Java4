package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "DongSp")
@NamedQuery(name="DongSp.findAll", query="select c from DongSp c")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DongSp {

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

    @OneToMany(mappedBy = "dongSp",fetch = FetchType.LAZY)
    private List<ChiTietSp> listDongSPCTSP;

    public DongSp(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public List<ChiTietSp> getListDongSPCTSP() {
        return listDongSPCTSP;
    }

    public void setListDongSPCTSP(List<ChiTietSp> listDongSPCTSP) {
        this.listDongSPCTSP = listDongSPCTSP;
    }
}
