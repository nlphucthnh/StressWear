package com.web.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.web.Entity.VaiTroTaiKhoan;

public interface VaiTroTaiKhoanDAO extends JpaRepository<VaiTroTaiKhoan, Integer> {
    @Query("SELECT p FROM VaiTroTaiKhoan p WHERE p.taiKhoan.tenDangNhap = ?1")
    List<VaiTroTaiKhoan> findByTenDangNhapVaiTro(String tenDangNhap);
}
