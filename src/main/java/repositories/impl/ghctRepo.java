package repositories.impl;

import Utils.JpaUtils;
import entities.CuaHang;
import entities.GioHangChiTiet;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class ghctRepo implements CRUDRepo<GioHangChiTiet> {
    private EntityManager em;
    public ghctRepo(){
        this.em = JpaUtils.getEntityManager();
    }
    @Override
    public List<GioHangChiTiet> getAll() {
        String jpql = "SELECT obj FROM GioHangChiTiet obj";
        TypedQuery<GioHangChiTiet> query = this.em
                .createQuery(jpql, GioHangChiTiet.class);
        List<GioHangChiTiet> ds = query.getResultList();
        return ds;
    }

    @Override
    public GioHangChiTiet create(GioHangChiTiet gioHangChiTiet) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(gioHangChiTiet);

            this.em.getTransaction().commit();

            return gioHangChiTiet;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public GioHangChiTiet findById(String id) throws Exception {
        GioHangChiTiet u = this.em.find(GioHangChiTiet.class, id);
        if (u == null) {
            throw new Exception("NotFoundException");
        }
        return u;
    }

    @Override
    public void update(GioHangChiTiet gioHangChiTiet) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(gioHangChiTiet);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(GioHangChiTiet gioHangChiTiet) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(gioHangChiTiet);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public GioHangChiTiet findByIDGHCT(String idgh, String idctsp)
    {
        String jpql = "SELECT a FROM GioHangChiTiet a "
                + "WHERE a.idChiTietSp.id = :idctsp "
                + "AND a.idGioHang.id = :idgh";
        try {
            TypedQuery<GioHangChiTiet> query =
                    this.em.createQuery(jpql, GioHangChiTiet.class);
            query.setParameter("idctsp", idctsp);
            query.setParameter("idgh", idgh);
            GioHangChiTiet ghct = query.getSingleResult();
            return ghct;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<GioHangChiTiet> getListByKH(String idgh) {
        String jpql = "SELECT obj FROM GioHangChiTiet obj where obj.idGioHang.id = : idgh";
        TypedQuery<GioHangChiTiet> query = this.em
                .createQuery(jpql, GioHangChiTiet.class);
        query.setParameter("idgh", idgh);
        List<GioHangChiTiet> ds = query.getResultList();
        return ds;
    }
    public void remove( String idgh, String idctsp){
        try { this.em.getTransaction().begin();
//            GioHangChiTiet ghct = em.fin(GioHangChiTiet.class, idgh, idctsp);
            GioHangChiTiet gioHangChiTiet = findByIDGHCT(idgh,idctsp);

            em.remove(gioHangChiTiet);
            System.out.println("xoa thanh cong");
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
}
