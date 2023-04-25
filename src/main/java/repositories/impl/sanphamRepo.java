package repositories.impl;

import Utils.JpaUtils;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class sanphamRepo implements CRUDRepo<SanPham> {
    private EntityManager em;
    public sanphamRepo(){
        this.em = JpaUtils.getEntityManager();
    }
    @Override
    public List<SanPham> getAll() {
        String jpql = "SELECT obj FROM SanPham obj";
        TypedQuery<SanPham> query = this.em
                .createQuery(jpql, SanPham.class);
        List<SanPham> ds = query.getResultList();
        return ds;
    }

    @Override
    public SanPham create(SanPham sanPham) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(sanPham);
            this.em.flush();
            this.em.getTransaction().commit();

            return sanPham;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public SanPham findById(String id) throws Exception {
        SanPham sp = this.em.find(SanPham.class,id);
        if (sp == null) {
            throw new Exception("NotFoundException");
        }
        return sp;
    }

    @Override
    public void update(SanPham sanPham) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(sanPham);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(SanPham sanPham) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(sanPham);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }

    }
    public void remove( String idch) {
        try {
            this.em.getTransaction().begin();
            SanPham sp = em.find(SanPham.class, idch);
            List<ChiTietSp> splist = sp.getListCTSP();
            for (ChiTietSp ctsp : splist) {
                System.out.println(ctsp.getSanPham().getTen());
                em.remove(ctsp);
                System.out.println("Xoa dc nhan vien");
            }
            em.remove(sp);
            System.out.println("xoa thanh cong");
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
}
