package com.web.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.SanPham;
import com.web.Entity.SanPhamChiTiet;

public interface SanPhamChiTietDAO extends JpaRepository<SanPhamChiTiet, String> {

    @Query(value = "SELECT p FROM SanPhamChiTiet p where p.sanPhamSPCT.idSanPham LIKE %?1%")
    List<SanPhamChiTiet> findByidsanpham(Integer p);
}
