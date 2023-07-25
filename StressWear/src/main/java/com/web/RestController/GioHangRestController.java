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

import com.web.DAO.GioHangDAO;
import com.web.Entity.GioHang;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/giohang")
public class GioHangRestController {

    @Autowired
    GioHangDAO GioHangDAO;

    @GetMapping
    public ResponseEntity<List<GioHang>> findAll(){
        return ResponseEntity.ok(GioHangDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<GioHang> findById(@PathVariable("id") String idGioHang){
       Optional<GioHang> optional = GioHangDAO.findById(Integer.valueOf(idGioHang));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<GioHang> post(@RequestBody GioHang GioHang){
        if(GioHangDAO.existsById(GioHang.getIdGioHang())){
            return ResponseEntity.badRequest().build();
        }
        GioHangDAO.save(GioHang);
        return ResponseEntity.ok(GioHang);
    }

    @PutMapping("{id}")
    public ResponseEntity<GioHang> put(@PathVariable("id") Integer idGioHang, @RequestBody GioHang GioHang){
        if(!GioHangDAO.existsById(idGioHang)){
            return ResponseEntity.notFound().build();
        }
        GioHangDAO.save(GioHang);
         return ResponseEntity.ok(GioHang);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idGioHang){
       if(!GioHangDAO.existsById(idGioHang)){
            return ResponseEntity.notFound().build();
        }
        GioHangDAO.deleteById(idGioHang);
        return ResponseEntity.ok().build();
    }
}
