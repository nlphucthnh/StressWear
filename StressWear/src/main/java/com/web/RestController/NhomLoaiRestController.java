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
@RequestMapping("/api/NhomLoai")
public class NhomLoaiRestController {

    @Autowired
    NhomLoaiDAO NhomLoaiDAO;

    @GetMapping
    public ResponseEntity<List<NhomLoai>> findAll(){
        return ResponseEntity.ok(NhomLoaiDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<NhomLoai> findById(@PathVariable("id") String idNhomLoai){
       Optional<NhomLoai> optional = NhomLoaiDAO.findById(Integer.valueOf(idNhomLoai));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<NhomLoai> post(@RequestBody NhomLoai NhomLoai){
        if(NhomLoaiDAO.existsById(NhomLoai.getIdNhomLoai())){
            return ResponseEntity.badRequest().build();
        }
        NhomLoaiDAO.save(NhomLoai);
        return ResponseEntity.ok(NhomLoai);
    }

    @PutMapping("{id}")
    public ResponseEntity<NhomLoai> put(@PathVariable("id") Integer idNhomLoai, @RequestBody NhomLoai NhomLoai){
        if(!NhomLoaiDAO.existsById(idNhomLoai)){
            return ResponseEntity.notFound().build();
        }
        NhomLoaiDAO.save(NhomLoai);
         return ResponseEntity.ok(NhomLoai);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idNhomLoai){
       if(!NhomLoaiDAO.existsById(idNhomLoai)){
            return ResponseEntity.notFound().build();
        }
        NhomLoaiDAO.deleteById(idNhomLoai);
        return ResponseEntity.ok().build();
    }
}
