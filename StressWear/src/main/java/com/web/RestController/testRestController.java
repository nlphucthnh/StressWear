package com.web.RestController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.DAO.KhuyenMaiDAO;
import com.web.DAO.NhomLoaiDAO;
import com.web.DAO.SanPhamDAO;
import com.web.Entity.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class testRestController {
    
    @Autowired
    NhomLoaiDAO nhomLoaiDAO;

    @Autowired
    KhuyenMaiDAO khuyenMaiDAO;

    @Autowired
    SanPhamDAO sanPhamDAO;
    
    @GetMapping("nhomloai/paging")
    public ResponseEntity<Page<NhomLoai>> findAllPage(@RequestParam("page") Optional<Integer> numberpage){
        Pageable pageableNL = PageRequest.of(numberpage.orElse(0), 5);
        Page<NhomLoai> pageNhomLoai = (Page) nhomLoaiDAO.findAll(pageableNL);
        return ResponseEntity.ok(pageNhomLoai);
    }

    @GetMapping("khuyenmai/paging")
    public ResponseEntity<Page<KhuyenMai>> findAllPage1(@RequestParam("page") Optional<Integer> numberpage){
        Pageable pageableKM = PageRequest.of(numberpage.orElse(0), 5);
        Page<KhuyenMai> pageKhuyenMai = (Page) khuyenMaiDAO.findAll(pageableKM);
        return ResponseEntity.ok(pageKhuyenMai);
    }

    @GetMapping("sanpham/paging")
    public ResponseEntity<Page<SanPham>> findAllPage2(@RequestParam(name = "name", defaultValue = "") String nameproduct,@RequestParam("page") Optional<Integer> numberpage){
        Pageable pageableSP = PageRequest.of(numberpage.orElse(0), 5);
        Page<SanPham> pageSanPham = sanPhamDAO.findByTenSanPham(nameproduct, pageableSP);
        return ResponseEntity.ok(pageSanPham);
    }
}
