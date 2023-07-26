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

import com.web.DAO.NhomLoaiDAO;
import com.web.Entity.NhomLoai;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/nhomloai")
public class NhomLoaiRestController {

    @Autowired
    NhomLoaiDAO nhomLoaiDAO;

    @GetMapping
    public ResponseEntity<List<NhomLoai>> findAll(){
        return ResponseEntity.ok(nhomLoaiDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<NhomLoai> findById(@PathVariable("id") String idNhomLoai){
       Optional<NhomLoai> optional = nhomLoaiDAO.findById(Integer.valueOf(idNhomLoai));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<NhomLoai> post(@RequestBody NhomLoai nhomLoai){
        if(nhomLoaiDAO.existsById(nhomLoai.getIdNhomLoai())){
            return ResponseEntity.badRequest().build();
        }
        nhomLoaiDAO.save(nhomLoai);
        return ResponseEntity.ok(nhomLoai);
    }

    @PutMapping("{id}")
    public ResponseEntity<NhomLoai> put(@PathVariable("id") Integer idNhomLoai, @RequestBody NhomLoai nhomLoai){
        if(!nhomLoaiDAO.existsById(idNhomLoai)){
            return ResponseEntity.notFound().build();
        }
        nhomLoaiDAO.save(nhomLoai);
         return ResponseEntity.ok(nhomLoai);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idNhomLoai){
       if(!nhomLoaiDAO.existsById(idNhomLoai)){
            return ResponseEntity.notFound().build();
        }
        nhomLoaiDAO.deleteById(idNhomLoai);
        return ResponseEntity.ok().build();
    }
}
