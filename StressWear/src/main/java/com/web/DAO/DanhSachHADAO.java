package com.web.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.DanhSachHA;


public interface DanhSachHADAO extends JpaRepository<DanhSachHA,Integer>{

    void deleteById(Integer idDanhSachHA);

    @Query("SELECT d FROM DanhSachHA d WHERE d.sanPhamChiTiet.idSanPhamChiTiet = ?1")
    List<DanhSachHA> findByIdSanPhamChiTiet(int idSanPhamChiTiet);
  
}
