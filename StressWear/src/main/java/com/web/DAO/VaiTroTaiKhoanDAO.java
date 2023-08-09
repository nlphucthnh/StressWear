package com.web.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.TaiKhoan;
import com.web.Entity.VaiTro;
import com.web.Entity.VaiTroTaiKhoan;

public interface VaiTroTaiKhoanDAO extends JpaRepository<VaiTroTaiKhoan, Integer> {
    @Query("SELECT p FROM VaiTroTaiKhoan p WHERE p.taiKhoan.tenDangNhap = ?1")
    List<VaiTroTaiKhoan> findByTenDangNhapVaiTro(String tenDangNhap);

    @Query("SELECT s FROM VaiTroTaiKhoan s WHERE s.taiKhoan.tenDangNhap LIKE CONCAT('%', ?1, '%')")
    Page<VaiTroTaiKhoan> findByVaiTroTenDangNhap(String tenDangNhap, Pageable pageableVTTK);

    List<VaiTroTaiKhoan> findByTaiKhoan(TaiKhoan taiKhoan);

    @Query("SELECT s FROM VaiTroTaiKhoan s WHERE s.taiKhoan = ?1 AND s.vaiTro = ?2")
    VaiTroTaiKhoan findVaiTroTaiKhoan(TaiKhoan taiKhoan, VaiTro vaiTro);

    Page<VaiTroTaiKhoan> findByVaiTroIdVaiTroContaining(String idVaiTro, Pageable pageable);

}
