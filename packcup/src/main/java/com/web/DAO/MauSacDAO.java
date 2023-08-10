package com.web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.Entity.MauSac;

public interface MauSacDAO extends JpaRepository<MauSac,Integer> {
    
}
