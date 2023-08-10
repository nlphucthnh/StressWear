package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.ThongTinTaiKhoan;

public interface ThongTinTaiKhoanDAO extends JpaRepository<ThongTinTaiKhoan,Integer> {
    
}
