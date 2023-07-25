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
    KhuyenMaiDAO KhuyenMaiDAO;

    @GetMapping
    public ResponseEntity<List<KhuyenMai>> findAll(){
        return ResponseEntity.ok(KhuyenMaiDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<KhuyenMai> findById(@PathVariable("id") String idKhuyenMai){
       Optional<KhuyenMai> optional = KhuyenMaiDAO.findById(idKhuyenMai);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<KhuyenMai> post(@RequestBody KhuyenMai KhuyenMai){
        if(KhuyenMaiDAO.existsById(KhuyenMai.getIdKhuyenMai())){
            return ResponseEntity.badRequest().build();
        }
        KhuyenMaiDAO.save(KhuyenMai);
        return ResponseEntity.ok(KhuyenMai);
    }

    @PutMapping("{id}")
    public ResponseEntity<KhuyenMai> put(@PathVariable("id") String idKhuyenMai, @RequestBody KhuyenMai KhuyenMai){
        if(!KhuyenMaiDAO.existsById(idKhuyenMai)){
            return ResponseEntity.notFound().build();
        }
        KhuyenMaiDAO.save(KhuyenMai);
         return ResponseEntity.ok(KhuyenMai);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String idKhuyenMai){
       if(!KhuyenMaiDAO.existsById(idKhuyenMai)){
            return ResponseEntity.notFound().build();
        }
        KhuyenMaiDAO.deleteById(idKhuyenMai);
        return ResponseEntity.ok().build();
    }
}
