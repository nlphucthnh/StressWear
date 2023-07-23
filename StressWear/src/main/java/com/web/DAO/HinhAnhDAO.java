package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.HinhAnh;
import java.util.Optional;


public interface HinhAnhDAO extends JpaRepository<HinhAnh,String> {
    HinhAnh findByIdHinhAnh(String idHinhAnh);
    Optional<HinhAnh> findById(String idHinhAnh);

}
