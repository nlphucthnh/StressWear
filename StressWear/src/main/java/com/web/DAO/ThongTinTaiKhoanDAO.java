package com.web.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.TaiKhoan;
import com.web.Entity.ThongTinTaiKhoan;

public interface ThongTinTaiKhoanDAO extends JpaRepository<ThongTinTaiKhoan,Integer> {
    
    @Query("SELECT s FROM ThongTinTaiKhoan s WHERE s.taiKhoanTTTK.tenDangNhap = ?1")
    ThongTinTaiKhoan findBytaiKhoanTTTK(String tenDangNhap);

     @Query("SELECT s FROM ThongTinTaiKhoan s WHERE s.taiKhoanTTTK.tenDangNhap = ?1")
    Optional<ThongTinTaiKhoan> findBytaiKhoanTTTK2(String tenDangNhap);
}
