package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.ThanhToan;

public interface ThanhToanDAO extends JpaRepository<ThanhToan,Integer> {
    
}
