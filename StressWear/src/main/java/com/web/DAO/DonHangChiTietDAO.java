package com.web.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.DonHang;
import com.web.Entity.DonHangChiTiet;
import com.web.Entity.ThongKe;

public interface DonHangChiTietDAO extends JpaRepository<DonHangChiTiet, Integer> {

     List<DonHangChiTiet> findByDonHangDHCT(Optional<DonHang> donHang);

     @Query("SELECT new ThongKe(dhct.sanPhamDHCT,SUM(dhct.soLuong),dh.ngayTao) FROM DonHangChiTiet dhct JOIN dhct.donHangDHCT dh WHERE dh.trangThaiDonHang =  'done' GROUP BY dhct.sanPhamDHCT, dhct.soLuong, dh.ngayTao")
     List<ThongKe> findListThongKeSP();

     @Query("SELECT new ThongKe(dhct.sanPhamDHCT,SUM(dhct.soLuong),dh.ngayTao) FROM DonHangChiTiet dhct JOIN dhct.donHangDHCT dh WHERE dh.trangThaiDonHang =  'done' GROUP BY dhct.sanPhamDHCT, dhct.soLuong, dh.ngayTao")
     Page<ThongKe> findListThongKeSP_NAMESP(Pageable pageableTK);
}
