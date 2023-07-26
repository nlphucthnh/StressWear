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

import com.web.DAO.SanPhamDAO;
import com.web.Entity.SanPham;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sanpham")
public class SanPhamRestController {

    @Autowired
    SanPhamDAO sanPhamDAO;

    @GetMapping
    public ResponseEntity<List<SanPham>> findAll(){
        return ResponseEntity.ok(sanPhamDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<SanPham> findById(@PathVariable("id") String idSanPham){
       Optional<SanPham> optional = sanPhamDAO.findById(Integer.valueOf(idSanPham));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<SanPham> post(@RequestBody SanPham sanPham){
        if(sanPhamDAO.existsById(sanPham.getIdSanPham())){
            return ResponseEntity.badRequest().build();
        }
        sanPhamDAO.save(sanPham);
        return ResponseEntity.ok(sanPham);
    }

    @PutMapping("{id}")
    public ResponseEntity<SanPham> put(@PathVariable("id") Integer idSanPham, @RequestBody SanPham sanPham){
        if(!sanPhamDAO.existsById(idSanPham)){
            return ResponseEntity.notFound().build();
        }
        sanPhamDAO.save(sanPham);
        return ResponseEntity.ok(sanPham);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idSanPham){
       if(!sanPhamDAO.existsById(idSanPham)){
            return ResponseEntity.notFound().build();
        }
        sanPhamDAO.deleteById(idSanPham);
        return ResponseEntity.ok().build();
    }
}
