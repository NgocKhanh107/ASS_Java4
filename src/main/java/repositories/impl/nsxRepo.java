package repositories.impl;

import Utils.JpaUtils;
import entities.ChiTietSp;
import entities.ChucVu;
import entities.Nsx;
import entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class nsxRepo implements CRUDRepo<Nsx> {
    private EntityManager em;
    public nsxRepo(){
        this.em = JpaUtils.getEntityManager();
    }
    @Override
    public List<Nsx> getAll() {
        String jpql = "SELECT obj FROM Nsx obj";
        TypedQuery<Nsx> query = this.em
                .createQuery(jpql, Nsx.class);
        List<Nsx> ds = query.getResultList();
        return ds;
    }

    @Override
    public Nsx create(Nsx nsx) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(nsx);
            this.em.flush();
            this.em.getTransaction().commit();

            return nsx;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Nsx findById(String id) throws Exception {
        Nsx nsx = this.em.find(Nsx.class, id);
        if (nsx == null) {
            throw new Exception("NotFoundException");
        }
        return nsx;
    }

    @Override
    public void update(Nsx nsx) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(nsx);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(Nsx nsx) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(nsx);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public void remove( String idnsx) {
        try {
            this.em.getTransaction().begin();
            Nsx nsx = em.find(Nsx.class, idnsx);
            List<ChiTietSp> splist = nsx.getListCTSP();
            for (ChiTietSp ctsp : splist) {
                em.remove(ctsp);
            }
            em.remove(nsx);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
}
