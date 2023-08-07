package com.web.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.DAO.DonHangChiTietDAO;
import com.web.DAO.DonHangDAO;
import com.web.Entity.DonHang;
import com.web.Entity.DonHangChiTiet;
import com.web.service.DonHangService;

@Service
public class DonHangServiceImpl implements DonHangService{

    @Autowired
    DonHangDAO donhangDao;

    @Autowired 
    DonHangChiTietDAO donHangChiTietDAO;
    
    @Override
    public DonHang create(JsonNode donhangData) {
        ObjectMapper  mapper = new ObjectMapper();

        DonHang donhang = mapper.convertValue(donhangData, DonHang.class);
        donhangDao.save(donhang);

        TypeReference<List<DonHangChiTiet>> type = new TypeReference<List<DonHangChiTiet>>(){};
        List<DonHangChiTiet> details = mapper.convertValue(donhangData.get("ListDHCT"), type)
        .stream().peek(d -> d.setDonHangDHCT(donhang)).collect(Collectors.toList());
        donHangChiTietDAO.saveAll(details);
        
        return donhang;
    }

    @Override
    public DonHang findById(int id) {
        return donhangDao.findById(id).get();
    }
    

}
