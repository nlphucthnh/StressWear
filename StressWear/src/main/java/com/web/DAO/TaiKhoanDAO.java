package com.web.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.TaiKhoan;

import java.util.List;

public interface TaiKhoanDAO extends JpaRepository<TaiKhoan, String> {
    List<TaiKhoan> findByTenDangNhap(String tenDangNhap);

    @Query("SELECT s FROM TaiKhoan s WHERE s.tenDangNhap LIKE CONCAT('%', ?1, '%')")
    Page<TaiKhoan> findByTenDangNhap(String nameaccount, Pageable pageableTK);

    @Query("SELECT tk FROM TaiKhoan tk WHERE tk.tenDangNhap NOT IN (SELECT vttk.taiKhoan.tenDangNhap FROM VaiTroTaiKhoan vttk)")
    List<TaiKhoan> find_TK_NOT_VT();
    

}
