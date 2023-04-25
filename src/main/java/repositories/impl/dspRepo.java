package repositories.impl;

import Utils.JpaUtils;
import entities.ChiTietSp;
import entities.ChucVu;
import entities.DongSp;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;
import java.util.List;

public class dspRepo implements CRUDRepo<DongSp> {
    private EntityManager em ;
    private chitietSpRepo ctsprepo;
    public dspRepo(){
        this.em = JpaUtils.getEntityManager();
        this.ctsprepo = new chitietSpRepo();
    }

    @Override
    public List<DongSp> getAll() {
        String sql = "select c from DongSp c";
        TypedQuery<DongSp> query = em.createQuery(sql,DongSp.class);
        List<DongSp> dsp = query.getResultList();
        return dsp;
    }

    @Override
    public DongSp create(DongSp dongSp) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(dongSp);
            this.em.flush();
            this.em.getTransaction().commit();

            return dongSp;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public DongSp findById(String id) throws Exception {
        DongSp dsp = this.em.find(DongSp.class, id);
        if (dsp == null) {
            throw new Exception("NotFoundException");
        }
        return dsp;
    }

    @Override
    public void update(DongSp dongSp) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(dongSp);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(DongSp dongSp) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(dongSp);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public void remove( String iddsp){
        try {
            this.em.getTransaction().begin();
            DongSp dongSp = em.find(DongSp.class, iddsp);
            List<ChiTietSp> dsplist = dongSp.getListDongSPCTSP();
            for (ChiTietSp ctsp : dsplist) {
                em.remove(ctsp);
            }
            this.em.remove(dongSp);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
}
