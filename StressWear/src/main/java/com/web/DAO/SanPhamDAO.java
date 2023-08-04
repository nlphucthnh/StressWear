package com.web.DAO;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham,Integer> {
    @Query("SELECT s FROM SanPham s WHERE s.tenSanPham LIKE CONCAT('%', ?1, '%')")
	Page<SanPham> findByTenSanPham(String nameproduct,Pageable pageable);
}
