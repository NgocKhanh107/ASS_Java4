package repositories.impl;

import Utils.JpaUtils;
import entities.ChucVu;
import entities.CuaHang;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class chucvuRepo implements CRUDRepo<ChucVu> {
    private EntityManager em;
    private nhanvienRepo nvrepo;
    public chucvuRepo(){
        this.em = JpaUtils.getEntityManager();
        this.nvrepo = new nhanvienRepo();
    }
    @Override
    public List<ChucVu> getAll() {
        String jpql = "SELECT obj FROM ChucVu obj";
        TypedQuery<ChucVu> query = this.em
                .createQuery(jpql, ChucVu.class);
        List<ChucVu> ds = query.getResultList();
        return ds;
    }

    @Override
    public ChucVu create(ChucVu chucVu) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(chucVu);
            this.em.flush();
            this.em.getTransaction().commit();

            return chucVu;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public ChucVu findById(String id) throws Exception {
        ChucVu cv = this.em.find(ChucVu.class, id);
        if (cv == null) {
            throw new Exception("NotFoundException");
        }
        return cv;
    }

    @Override
    public void update(ChucVu chucVu) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(chucVu);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(ChucVu chucVu) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(chucVu);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public void remove(ChucVu chucVu, String idcv){
        try {
            this.em.getTransaction().begin();
            ChucVu cv = em.find(ChucVu.class, idcv);
            List<NhanVien> nvlist = cv.getNhanViens();
            for (NhanVien nv : nvlist) {
                em.remove(nv);
            }
            this.em.remove(cv);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
}
