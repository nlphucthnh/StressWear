package com.web.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.Entity.DanhSachKT;
import com.web.Entity.DanhSachKTKey;
import com.web.Entity.SanPhamChiTiet;

public interface DanhSachKTDAO extends JpaRepository<DanhSachKT, Integer> {
    @Query("SELECT d FROM DanhSachKT d JOIN d.sanPhamChiTiet sp WHERE sp.sanPhamSPCT.idSanPham = ?1")
    List<DanhSachKT> findBySanPhamId(Integer idSanPham);
}

