package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.DonHangChiTiet;

public interface DonHangChiTietDAO extends JpaRepository<DonHangChiTiet,Integer>{
    
}
