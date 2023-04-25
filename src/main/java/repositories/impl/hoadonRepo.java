package repositories.impl;

import Utils.JpaUtils;
import entities.ChiTietSp;
import entities.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class hoadonRepo implements CRUDRepo<HoaDon> {
    private EntityManager em;
    public hoadonRepo(){
        this.em = JpaUtils.getEntityManager();
    }
    @Override
    public List<HoaDon> getAll() {
        String jpql = "SELECT obj FROM HoaDon obj";
        TypedQuery<HoaDon> query = this.em
                .createQuery(jpql, HoaDon.class);
        List<HoaDon> ds = query.getResultList();
        return ds;
    }

    @Override
    public HoaDon create(HoaDon hoaDon) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(hoaDon);
            this.em.flush();
            this.em.getTransaction().commit();

            return hoaDon;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public HoaDon findById(String id) throws Exception {
        return null;
    }

    @Override
    public void update(HoaDon hoaDon) {

    }

    @Override
    public void delete(HoaDon hoaDon) {

    }
}
