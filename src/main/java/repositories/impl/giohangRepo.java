package repositories.impl;

import Utils.JpaUtils;
import entities.GioHang;
import entities.GioHangChiTiet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class giohangRepo implements CRUDRepo<GioHang> {
    private EntityManager em;
    public giohangRepo(){
        this.em = JpaUtils.getEntityManager();
    }
    @Override
    public List<GioHang> getAll() {
        String jpql = "SELECT obj FROM GioHang obj";
        TypedQuery<GioHang> query = this.em
                .createQuery(jpql, GioHang.class);
        List<GioHang> ds = query.getResultList();
        return ds;
    }

    @Override
    public GioHang create(GioHang gioHang) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(gioHang);
            this.em.flush();
            this.em.getTransaction().commit();

            return gioHang;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public GioHang findById(String id) throws Exception {
        GioHang u = this.em.find(GioHang.class, id);
        if (u == null) {
            throw new Exception("NotFoundException");
        }
        return u;
    }

    @Override
    public void update(GioHang gioHang) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(gioHang);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }

    }

    @Override
    public void delete(GioHang gioHang) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(gioHang);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public List<GioHang> findByIdKH(String id)  {

        String jpql = "SELECT svlop FROM GioHang svlop " + "WHERE svlop.khachHang.id = :lopid ";
        try {
            TypedQuery<GioHang> query = this.em.createQuery(jpql, GioHang.class);
            query.setParameter("lopid", id);
            List<GioHang> sv = query.getResultList();
            return sv;
        } catch (Exception e) {
            System.out.println("erro SinhVienLopRepository");
            e.printStackTrace();
            return null;
        }
    }


}
