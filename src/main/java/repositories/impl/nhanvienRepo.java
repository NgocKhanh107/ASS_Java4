package repositories.impl;

import Utils.JpaUtils;
import entities.ChucVu;
import entities.CuaHang;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class nhanvienRepo implements CRUDRepo<NhanVien> {
    private EntityManager em;
    public nhanvienRepo(){
        this.em = JpaUtils.getEntityManager();
    }

    @Override
    public List<NhanVien> getAll() {
        String jpql = "SELECT obj FROM NhanVien obj";
        TypedQuery<NhanVien> query = this.em
                .createQuery(jpql, NhanVien.class);
        List<NhanVien> ds = query.getResultList();
        return ds;
    }

    @Override
    public NhanVien create(NhanVien nhanVien) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(nhanVien);
            this.em.flush();
            this.em.getTransaction().commit();

            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public NhanVien findById(String id) throws Exception {
        NhanVien nv = this.em.find(NhanVien.class, id);
        if (nv == null) {
            throw new Exception("NotFoundException");
        }
        return nv;
    }

    @Override
    public void update(NhanVien nhanVien) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(nhanVien);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(NhanVien nhanVien) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(nhanVien);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public NhanVien login(String ma, String password) {
        String jpql = "SELECT a FROM NhanVien a "
                + "WHERE a.ma = :ma "
                + "AND a.matkhau = :mk";
        try {
            TypedQuery<NhanVien> query =
                    this.em.createQuery(jpql, NhanVien.class);
            query.setParameter("ma", ma);
            query.setParameter("mk", password);
            NhanVien nv = query.getSingleResult();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<NhanVien> getByChucVu(ChucVu chucVu) {
        String jpql = "SELECT o FROM NhanVien o WHERE o.chucVu = :cv";
        TypedQuery<NhanVien> query = this.em
                .createQuery(jpql, NhanVien.class);
        query.setParameter("cv", chucVu);
        List<NhanVien> ds = query.getResultList();
        return ds;
    }
    public List<NhanVien> getByCuaHang(CuaHang cuaHang) {
        String jpql = "SELECT o FROM NhanVien o WHERE o.cuaHang = :ch";
        TypedQuery<NhanVien> query = this.em
                .createQuery(jpql, NhanVien.class);
        query.setParameter("ch", cuaHang);
        List<NhanVien> ds = query.getResultList();
        return ds;
    }

}
