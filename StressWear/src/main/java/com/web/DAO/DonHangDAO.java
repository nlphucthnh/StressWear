package com.web.DAO;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.DonHang;
public interface DonHangDAO extends JpaRepository<DonHang,Integer>{
    @Query("SELECT d FROM DonHang d WHERE d.idDonHang LIKE CONCAT('%', ?1, '%')")
    Page<DonHang> findById(String idDonHang, Pageable pageableSP);

   
}
