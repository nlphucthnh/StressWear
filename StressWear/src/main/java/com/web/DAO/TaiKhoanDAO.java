package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.TaiKhoan;

import java.util.List;
import java.util.Optional;


public interface TaiKhoanDAO extends JpaRepository<TaiKhoan, String> {
    List<TaiKhoan> findByTenDangNhap(String tenDangNhap);
    

    
}
