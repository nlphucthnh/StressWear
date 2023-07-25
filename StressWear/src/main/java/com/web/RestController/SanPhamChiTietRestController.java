package com.web.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.DAO.SanPhamChiTietDAO;
import com.web.Entity.SanPhamChiTiet;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sanphamchitiet")
public class SanPhamChiTietRestController {

    @Autowired
    SanPhamChiTietDAO SanPhamChiTietDAO;

    @GetMapping
    public ResponseEntity<List<SanPhamChiTiet>> findAll(){
        return ResponseEntity.ok(SanPhamChiTietDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<SanPhamChiTiet> findById(@PathVariable("id") String idSanPhamChiTiet){
       Optional<SanPhamChiTiet> optional = SanPhamChiTietDAO.findById(Integer.valueOf(idSanPhamChiTiet));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<SanPhamChiTiet> post(@RequestBody SanPhamChiTiet SanPhamChiTiet){
        if(SanPhamChiTietDAO.existsById(SanPhamChiTiet.getIdSanPhamChiTiet())){
            return ResponseEntity.badRequest().build();
        }
        SanPhamChiTietDAO.save(SanPhamChiTiet);
        return ResponseEntity.ok(SanPhamChiTiet);
    }

    @PutMapping("{id}")
    public ResponseEntity<SanPhamChiTiet> put(@PathVariable("id") Integer idSanPhamChiTiet, @RequestBody SanPhamChiTiet SanPhamChiTiet){
        if(!SanPhamChiTietDAO.existsById(idSanPhamChiTiet)){
            return ResponseEntity.notFound().build();
        }
        SanPhamChiTietDAO.save(SanPhamChiTiet);
         return ResponseEntity.ok(SanPhamChiTiet);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idSanPhamChiTiet){
       if(!SanPhamChiTietDAO.existsById(idSanPhamChiTiet)){
            return ResponseEntity.notFound().build();
        }
        SanPhamChiTietDAO.deleteById(idSanPhamChiTiet);
        return ResponseEntity.ok().build();
    }
}
