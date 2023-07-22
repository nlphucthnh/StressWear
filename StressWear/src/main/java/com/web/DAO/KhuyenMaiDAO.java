package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.KhuyenMai;
import java.util.Optional;

public interface KhuyenMaiDAO extends JpaRepository<KhuyenMai, String> {
    KhuyenMai findByIdKhuyenMai(String idKhuyenMai);
    Optional<KhuyenMai> findById(String idKhuyenMai);
}
