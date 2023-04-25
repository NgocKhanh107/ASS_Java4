package repositories.impl;

import Utils.JpaUtils;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.ArrayList;
import java.util.List;

public class chitietSpRepo implements CRUDRepo<ChiTietSp> {
    private EntityManager em;
    public chitietSpRepo(){
        this.em = JpaUtils.getEntityManager();
    }
    @Override
    public List<ChiTietSp> getAll() {
        String jpql = "SELECT obj FROM ChiTietSp obj";
        TypedQuery<ChiTietSp> query = this.em
                .createQuery(jpql, ChiTietSp.class);
        List<ChiTietSp> ds = query.getResultList();
        return ds;
    }

    @Override
    public ChiTietSp create(ChiTietSp chiTietSp) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(chiTietSp);
            this.em.flush();
            this.em.getTransaction().commit();

            return chiTietSp;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public ChiTietSp findById(String id)  {
        ChiTietSp ctsp = this.em.find(ChiTietSp.class, id);
        if (ctsp == null) {
            try {
                throw new Exception("NotFoundException");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ctsp;
    }

    @Override
    public void update(ChiTietSp chiTietSp) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(chiTietSp);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }

    }

    @Override
    public void delete(ChiTietSp chiTietSp) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(chiTietSp);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public Long getTotal(){
        Long count = em.createQuery("SELECT COUNT(e) FROM ChiTietSp e", Long.class)
                .getSingleResult();
        return count;
    }

    public List<ChiTietSp> listpaging(int index){

        String jpql = "SELECT e FROM ChiTietSp e ORDER BY e.id";
        TypedQuery<ChiTietSp> query = this.em
                .createQuery(jpql, ChiTietSp.class);
        query.setFirstResult((index-1)*8);
        query.setMaxResults(8);
        List<ChiTietSp> ds = query.getResultList();
        return ds;
    }

    public List<ChiTietSp> getByMauSac(MauSac mauSac) {
        String jpql = "SELECT o FROM ChiTietSp o WHERE o.mauSac = : mausac";
        TypedQuery<ChiTietSp> query = this.em
                .createQuery(jpql, ChiTietSp.class);
        query.setParameter("mausac", mauSac);
        List<ChiTietSp> ds = query.getResultList();
        return ds;
    }
    public List<ChiTietSp> getByDongSP(DongSp dsp) {
        String jpql = "SELECT o FROM ChiTietSp o WHERE o.dongSp = : dsp";
        TypedQuery<ChiTietSp> query = this.em
                .createQuery(jpql, ChiTietSp.class);
        query.setParameter("dsp", dsp);
        List<ChiTietSp> ds = query.getResultList();
        return ds;
    }
    public List<ChiTietSp> getBySanPham(SanPham sanPham) {
        String jpql = "SELECT o FROM ChiTietSp o WHERE o.sanPham = : sanpham";
        TypedQuery<ChiTietSp> query = this.em
                .createQuery(jpql, ChiTietSp.class);
        query.setParameter("sanpham", sanPham);
        List<ChiTietSp> ds = query.getResultList();
        return ds;
    }
    public List<ChiTietSp> getByNsx(Nsx nsx) {
        String jpql = "SELECT o FROM ChiTietSp o WHERE o.nsx = : nsx";
        TypedQuery<ChiTietSp> query = this.em
                .createQuery(jpql, ChiTietSp.class);
        query.setParameter("nsx", nsx);
        List<ChiTietSp> ds = query.getResultList();
        return ds;
    }
}
