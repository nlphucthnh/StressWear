package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.VaiTroTaiKhoan;

public interface VaiTroTaiKhoanDAO extends JpaRepository<VaiTroTaiKhoan, Integer> {
    
}
