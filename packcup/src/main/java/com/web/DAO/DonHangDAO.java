package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.web.Entity.DonHang;

public interface DonHangDAO extends JpaRepository<DonHang,Integer>{

    
}
