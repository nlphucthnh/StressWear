package com.web.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.Entity.DanhSachHA;
import com.web.Entity.DanhSachHAKey;
import com.web.Entity.DanhSachKT;


public interface DanhSachHADAO extends JpaRepository<DanhSachHA,DanhSachHAKey>{

    void deleteById(DanhSachHAKey idDanhSachHA);

    @Query("SELECT d FROM DanhSachHA d WHERE d.idSanPhamChiTiet = ?1")
    List<DanhSachHA> findByIdSanPhamChiTiet(int idSanPhamChiTiet);
  
}
