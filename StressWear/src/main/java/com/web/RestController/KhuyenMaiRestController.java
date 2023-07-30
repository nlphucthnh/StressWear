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

import com.web.DAO.KhuyenMaiDAO;
import com.web.Entity.KhuyenMai;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/khuyenmai")
public class KhuyenMaiRestController {

    @Autowired
    KhuyenMaiDAO khuyenMaiDAO;

    @GetMapping
    public ResponseEntity<List<KhuyenMai>> findAll(){
        return ResponseEntity.ok(khuyenMaiDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<KhuyenMai> findById(@PathVariable("id") String idKhuyenMai){
       Optional<KhuyenMai> optional = khuyenMaiDAO.findById(idKhuyenMai);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<KhuyenMai> post(@RequestBody KhuyenMai khuyenMai){
        if(khuyenMaiDAO.existsById(khuyenMai.getIdKhuyenMai())){
            return ResponseEntity.badRequest().build();
        }
        KhuyenMai khuyenMai2 = khuyenMaiDAO.save(khuyenMai);
        return ResponseEntity.ok(khuyenMai2);
    }

    @PutMapping("{id}")
    public ResponseEntity<KhuyenMai> put(@PathVariable("id") String idKhuyenMai, @RequestBody KhuyenMai khuyenMai){
        if(!khuyenMaiDAO.existsById(idKhuyenMai)){
            return ResponseEntity.notFound().build();
        }
        khuyenMaiDAO.save(khuyenMai);
         return ResponseEntity.ok(khuyenMai);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String idKhuyenMai){
       if(!khuyenMaiDAO.existsById(idKhuyenMai)){
            return ResponseEntity.notFound().build();
        }
        khuyenMaiDAO.deleteById(idKhuyenMai);
        return ResponseEntity.ok().build();
    }
}
