package com.web.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.DanhSachKT;
import com.web.Entity.DanhSachKTKey;

public interface DanhSachKTDAO extends JpaRepository<DanhSachKT,DanhSachKTKey>{
    // Optional<DanhSachKT>  findById(int idKichThuoc, int idSanPhamChiTiet);
}
