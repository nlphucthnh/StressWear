package com.web.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.DonHang;
import com.web.Entity.DonHangChiTiet;
public interface DonHangDAO extends JpaRepository<DonHang,Integer>{
    @Query("SELECT d FROM DonHang d WHERE d.idDonHang LIKE CONCAT('%', ?1, '%')")
    Page<DonHang> findById(String idDonHang, Pageable pageableSP);

   
}
