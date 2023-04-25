package repositories;

import Utils.JpaUtils;
import entities.*;
import jakarta.persistence.EntityManager;
import repositories.impl.*;

import java.util.List;

public class test {

    public static void main(String[] args) throws Exception {

//        HoaDonChiTiet hoadonCT = em.find(HoaDonChiTiet.class,new HoaDonChiTietPK("2670B3E2-B936-4A9D-BFE4-B8DCD5ECE803","EC1E7C5E-C74A-4535-9009-214B725C3AEA"));
//       hdctRepo hdct = new hdctRepo();
//        hdct.updateSL("2670B3E2-B936-4A9D-BFE4-B8DCD5ECE803","EC1E7C5E-C74A-4535-9009-214B725C3AEA",7);
//        chitietSpRepo ctsp = new chitietSpRepo();
//        System.out.println(ctsp.getTotal());
//        List<ChiTietSp> list = ctsp.listpaging(1);
//        for(ChiTietSp i : list){
//            System.out.println(i);
//        }
//        hdctRepo hdct = new hdctRepo();
//       HoaDonChiTiet hd = hdct.findByID("EC1E7C5E-C74A-4535-9009-214B725C3AEA","4701FDF4-26D8-4164-A665-3884EA7CBC84");
//        System.out.println(hd.getSoLuong());
//        cuahangRepo chrepo = new cuahangRepo();
//        CuaHang entity = chrepo.findById("5BEBB85E-56EA-4CD7-938C-2452BCCA6A36");
//        chrepo.remove(entity,"5BEBB85E-56EA-4CD7-938C-2452BCCA6A36");
//        nhanvienRepo nv = new nhanvienRepo();
        chucvuRepo cv = new chucvuRepo();
//        ChucVu chucvu = cv.findById("469C722C-6CA5-4F6E-B3C2-2A492C89A215");
//
//        List<NhanVien> list = nv.getByChucVu(chucvu);

//        khachhangRepo kh = new khachhangRepo();
//        KhachHang khachHang = kh.findIDByMa("KH6");
//        System.out.println(khachHang.getId());

            ChucVu chucVu = cv.findById("5BA9AA5C-D095-4E13-8154-FAD383DCD068");
        List<NhanVien> listcv = chucVu.getNhanViens();
        System.out.println(listcv.size());
    }

}
