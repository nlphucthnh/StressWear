package com.web.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.DonHang;
import com.web.Entity.DonHangChiTiet;

public interface DonHangChiTietDAO extends JpaRepository<DonHangChiTiet,Integer>{

     List<DonHangChiTiet> findByDonHangDHCT(Optional<DonHang> donHang);
}
