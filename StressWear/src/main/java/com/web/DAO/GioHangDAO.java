package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.GioHang;

public interface GioHangDAO extends JpaRepository<GioHang,Integer>{
    
}
