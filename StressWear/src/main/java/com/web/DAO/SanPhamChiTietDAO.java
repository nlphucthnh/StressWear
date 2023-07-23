package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.SanPhamChiTiet;

public interface SanPhamChiTietDAO extends JpaRepository<SanPhamChiTiet,Integer> {
    
}
