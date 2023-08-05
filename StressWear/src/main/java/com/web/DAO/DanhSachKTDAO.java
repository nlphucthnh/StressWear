package com.web.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.DanhSachKT;

public interface DanhSachKTDAO extends JpaRepository<DanhSachKT, Integer> {
    @Query("SELECT d FROM DanhSachKT d JOIN d.sanPhamChiTiet sp WHERE sp.sanPhamSPCT.idSanPham = ?1")
    List<DanhSachKT> findBySanPhamId(Integer idSanPham);

    @Query("SELECT d FROM DanhSachKT d WHERE d.sanPhamChiTiet.idSanPhamChiTiet = ?1")
    List<DanhSachKT> findByIdSanPhamChiTiet(int idSanPhamChiTiet);
}

