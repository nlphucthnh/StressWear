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

import com.web.DAO.KichThuocDAO;
import com.web.Entity.KichThuoc;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/kichthuoc")
public class KichThuocRestController {

    @Autowired
    KichThuocDAO kichThuocDAO;

    @GetMapping
    public ResponseEntity<List<KichThuoc>> findAll(){
        return ResponseEntity.ok(kichThuocDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<KichThuoc> findById(@PathVariable("id") String idKichThuoc){
       Optional<KichThuoc> optional = kichThuocDAO.findById(idKichThuoc);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<KichThuoc> post(@RequestBody KichThuoc kichThuoc){
        if(kichThuocDAO.existsById(kichThuoc.getIdKichThuoc())){
            return ResponseEntity.badRequest().build();
        }
        kichThuocDAO.save(kichThuoc);
        return ResponseEntity.ok(kichThuoc);
    }

    @PutMapping("{id}")
    public ResponseEntity<KichThuoc> put(@PathVariable("id") String idKichThuoc, @RequestBody KichThuoc kichThuoc){
        if(!kichThuocDAO.existsById(idKichThuoc)){
            return ResponseEntity.notFound().build();
        }
        kichThuocDAO.save(kichThuoc);
         return ResponseEntity.ok(kichThuoc);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String idKichThuoc){
       if(!kichThuocDAO.existsById(idKichThuoc)){
            return ResponseEntity.notFound().build();
        }
        kichThuocDAO.deleteById(idKichThuoc);
        return ResponseEntity.ok().build();
    }
}
