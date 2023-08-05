package com.web.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.SanPham;
import java.util.List;

public interface SanPhamDAO extends JpaRepository<SanPham, Integer> {
    @Query("SELECT p FROM SanPham p WHERE p.nhomLoaiSP.idNhomLoai = ?1")
    List<SanPham> findByNhomLoai(Integer idNhomLoai);

    @Query(value = "SELECT p FROM SanPham p where p.nhomLoaiSP.idNhomLoai LIKE ?1")
    List<SanPham> findByLoaiSanpham(Integer idNhomLoai, Sort by);

    @Query("SELECT p FROM SanPham p WHERE p.nhomLoaiSP.idNhomLoai = ?1 AND p.giaSanPham BETWEEN ?2 AND ?3")
    List<SanPham> findByLoaiSanphamPrice(Integer idNhomLoai, Sort sort, double min, double max);

    @Query("SELECT s FROM SanPham s WHERE s.tenSanPham LIKE CONCAT('%', ?1, '%')")
    Page<SanPham> findByTenSanPham(String nameproduct, Pageable pageableSP);


}
