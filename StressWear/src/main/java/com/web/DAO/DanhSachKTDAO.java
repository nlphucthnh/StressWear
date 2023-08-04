package com.web.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.DanhSachKT;
import com.web.Entity.DanhSachKTKey;

public interface DanhSachKTDAO extends JpaRepository<DanhSachKT, DanhSachKTKey> {
    @Query("SELECT d FROM DanhSachKT d JOIN d.sanPhamChiTiet sp WHERE sp.sanPhamSPCT.idSanPham = ?1")
    List<DanhSachKT> findBySanPhamId(Integer idSanPham);

    @Query("SELECT d FROM DanhSachKT d WHERE d.idSanPhamChiTiet = ?1")
    List<DanhSachKT> findByIdSanPhamChiTiet(int idSanPhamChiTiet);
}

