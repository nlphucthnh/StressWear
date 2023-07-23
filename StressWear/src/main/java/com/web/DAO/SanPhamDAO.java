package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham,Integer> {
    
}
