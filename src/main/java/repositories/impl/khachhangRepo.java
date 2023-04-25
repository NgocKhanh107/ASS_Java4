package repositories.impl;

import Utils.JpaUtils;
import entities.KhachHang;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class khachhangRepo implements CRUDRepo<KhachHang> {
    private EntityManager em;
    public khachhangRepo(){
        this.em = JpaUtils.getEntityManager();
    }
    @Override
    public List<KhachHang> getAll() {
        String jpql = "SELECT obj FROM KhachHang obj";
        TypedQuery<KhachHang> query = this.em
                .createQuery(jpql, KhachHang.class);
        List<KhachHang> ds = query.getResultList();
        return ds;
    }

    @Override
    public KhachHang create(KhachHang khachHang) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(khachHang);
            this.em.flush();
            this.em.getTransaction().commit();

            return khachHang;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public KhachHang findById(String id)  {
        KhachHang kh = this.em.find(KhachHang.class, id);
        if (kh == null) {
            try {
                throw new Exception("NotFoundException");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return kh;
    }

    @Override
    public void update(KhachHang khachHang) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(khachHang);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(KhachHang khachHang) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(khachHang);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public KhachHang login(String ma, String password) {
        String jpql = "SELECT a FROM KhachHang a "
                + "WHERE a.ma = :ma "
                + "AND a.matKhau = :mk";
        try {
            TypedQuery<KhachHang> query =
                    this.em.createQuery(jpql, KhachHang.class);
            query.setParameter("ma", ma);
            query.setParameter("mk", password);
            KhachHang kh = query.getSingleResult();
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public  void updatePass(String id,String mk) {
        try {
                KhachHang kh = em.find(KhachHang.class,id);
                kh.setMatKhau(mk);
                this.em.getTransaction().begin();
                this.em.merge(kh);
                this.em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public KhachHang findIDByMa(String ma) {
        String jpql = "SELECT a FROM KhachHang a "
                + "WHERE a.ma = :ma ";

        try {
            TypedQuery<KhachHang> query =
                    this.em.createQuery(jpql, KhachHang.class);
            query.setParameter("ma", ma);
            KhachHang kh = query.getSingleResult();
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
