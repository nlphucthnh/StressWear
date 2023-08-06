package com.web.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.TaiKhoan;

import java.util.List;

public interface TaiKhoanDAO extends JpaRepository<TaiKhoan, String> {
    List<TaiKhoan> findByTenDangNhap(String tenDangNhap);



    @Query("SELECT s FROM TaiKhoan s WHERE s.tenDangNhap LIKE CONCAT('%', ?1, '%')")
    Page<TaiKhoan> findByTenDangNhap(String nameaccount, Pageable pageableTK);

}
