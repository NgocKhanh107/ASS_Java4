package repositories.impl;

import Utils.JpaUtils;
import entities.HoaDonChiTiet;
import entities.HoaDonChiTietPK;
import entities.KhachHang;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repositories.CRUDRepo;

import java.util.List;

public class hdctRepo implements CRUDRepo<HoaDonChiTiet> {
    private EntityManager em;
    public hdctRepo(){
        this.em = JpaUtils.getEntityManager();
    }
    @Override
    public List<HoaDonChiTiet> getAll() {
        String jpql = "SELECT obj FROM HoaDonChiTiet obj";
        TypedQuery<HoaDonChiTiet> query = this.em
                .createQuery(jpql, HoaDonChiTiet.class);
        List<HoaDonChiTiet> ds = query.getResultList();
        return ds;
    }

    @Override
    public HoaDonChiTiet create(HoaDonChiTiet hoaDonChiTiet) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(hoaDonChiTiet);
            this.em.flush();
            this.em.getTransaction().commit();
            return hoaDonChiTiet;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public HoaDonChiTiet findById(String id) throws Exception {
        return null;
    }

    @Override
    public void update(HoaDonChiTiet hoaDonChiTiet) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(hoaDonChiTiet);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(HoaDonChiTiet hoaDonChiTiet) {

    }
    public void updateSL(String idhd, String idctsp, Integer sl){
        HoaDonChiTietPK hd = new HoaDonChiTietPK(idhd,idctsp);

        HoaDonChiTiet hoadonCT = this.em.find(HoaDonChiTiet.class,hd);
        hoadonCT.setSoLuong(sl);
        try {
            this.em.getTransaction().begin();
            this.em.merge(hoadonCT);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public HoaDonChiTiet findByID(String idctsp, String idhd){
        String jpql = "SELECT a FROM HoaDonChiTiet a "
                + "WHERE a.idChiTietSp.id = :idctsp "
                + "AND a.idHoaDon.id = :idhd";
        try {
            TypedQuery<HoaDonChiTiet> query =
                    this.em.createQuery(jpql, HoaDonChiTiet.class);
            query.setParameter("idctsp", idctsp);
            query.setParameter("idhd", idhd);
            HoaDonChiTiet hdct = query.getSingleResult();
            return hdct;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
