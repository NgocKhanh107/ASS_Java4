package repositories.impl;

import Utils.JpaUtils;
import entities.CuaHang;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class cuahangRepo implements CRUDRepo<CuaHang> {
    private EntityManager em;
    private nhanvienRepo nvrepo;
    public cuahangRepo(){
        this.em = JpaUtils.getEntityManager();
        this.nvrepo = new nhanvienRepo();
    }
    @Override
    public List<CuaHang> getAll() {
        String jpql = "SELECT obj FROM CuaHang obj";
        TypedQuery<CuaHang> query = this.em
                .createQuery(jpql, CuaHang.class);
        List<CuaHang> ds = query.getResultList();
        return ds;
    }

    @Override
    public CuaHang create(CuaHang cuaHang) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(cuaHang);
            this.em.flush();
            this.em.getTransaction().commit();

            return cuaHang;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public CuaHang findById(String id) throws Exception {
        CuaHang u = this.em.find(CuaHang.class, id);
        if (u == null) {
            throw new Exception("NotFoundException");
        }
        return u;
    }

    @Override
    public void update(CuaHang cuaHang) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(cuaHang);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(CuaHang cuaHang) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(cuaHang);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public void remove( String idch){
        try { this.em.getTransaction().begin();
        CuaHang ch = em.find(CuaHang.class, idch);
        List<NhanVien> nvlist = ch.getNhanViens();
        for (NhanVien nv : nvlist) {
            System.out.println(nv.getTen());
            em.remove(nv);
            System.out.println("Xoa dc nhan vien");
        }
        em.remove(ch);
        System.out.println("xoa thanh cong");
        this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
}
