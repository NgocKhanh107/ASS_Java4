package repositories.impl;

import Utils.JpaUtils;
import entities.ChiTietSp;
import entities.ChucVu;
import entities.MauSac;
import entities.Nsx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class mausacRepo implements CRUDRepo<MauSac> {
    private EntityManager em;
    public mausacRepo(){
        this.em = JpaUtils.getEntityManager();
    }
    @Override
    public List<MauSac> getAll() {
        String sql = "SELECT c FROM MauSac c ";
        TypedQuery<MauSac> query = this.em.createQuery(sql, MauSac.class);
        List<MauSac> ms = query.getResultList();
        return ms;
    }

    @Override
    public MauSac create(MauSac mauSac) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(mauSac);
            this.em.flush();
            this.em.getTransaction().commit();

            return mauSac;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public MauSac findById(String id) throws Exception {
        MauSac ms = this.em.find(MauSac.class, id);
        if (ms == null) {
            throw new Exception("NotFoundException");
        }
        return ms;
    }

    @Override
    public void update(MauSac mauSac) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(mauSac);
            this.em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(MauSac mauSac) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(mauSac);
            this.em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public void remove( String idms) {
        try {
            this.em.getTransaction().begin();
            MauSac ms = em.find(MauSac.class, idms);
            List<ChiTietSp> splist = ms.getListCTSP();
            for (ChiTietSp ctsp : splist) {
                em.remove(ctsp);
            }
            em.remove(ms);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
}
