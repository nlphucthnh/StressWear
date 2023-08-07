package com.web.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.VaiTroTaiKhoan;

public interface VaiTroTaiKhoanDAO extends JpaRepository<VaiTroTaiKhoan, Integer> {
    @Query("SELECT p FROM VaiTroTaiKhoan p WHERE p.taiKhoan.tenDangNhap = ?1")
    List<VaiTroTaiKhoan> findByTenDangNhapVaiTro(String tenDangNhap);

    @Query("SELECT s FROM VaiTroTaiKhoan s WHERE s.taiKhoan.tenDangNhap LIKE CONCAT('%', ?1, '%')")
    Page<VaiTroTaiKhoan> findByVaiTroTenDangNhap(String nameaccount, Pageable pageableVTTK);

    Page<VaiTroTaiKhoan> findByVaiTroIdVaiTroContaining(String idVaiTro, Pageable pageable);

}
