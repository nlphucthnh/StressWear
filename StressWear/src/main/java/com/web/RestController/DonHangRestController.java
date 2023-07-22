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

import com.web.DAO.DonHangDAO;
import com.web.Entity.DonHang;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/DonHang")
public class DonHangRestController {

    @Autowired
    DonHangDAO DonHangDAO;

    @GetMapping
    public ResponseEntity<List<DonHang>> findAll(){
        return ResponseEntity.ok(DonHangDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<DonHang> findById(@PathVariable("id") String idDonHang){
       Optional<DonHang> optional = DonHangDAO.findById(Integer.valueOf(idDonHang));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<DonHang> post(@RequestBody DonHang DonHang){
        if(DonHangDAO.existsById(DonHang.getIdDonHang())){
            return ResponseEntity.badRequest().build();
        }
        DonHangDAO.save(DonHang);
        return ResponseEntity.ok(DonHang);
    }

    @PutMapping("{id}")
    public ResponseEntity<DonHang> put(@PathVariable("id") Integer idDonHang, @RequestBody DonHang DonHang){
        if(!DonHangDAO.existsById(idDonHang)){
            return ResponseEntity.notFound().build();
        }
        DonHangDAO.save(DonHang);
         return ResponseEntity.ok(DonHang);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idDonHang){
       if(!DonHangDAO.existsById(idDonHang)){
            return ResponseEntity.notFound().build();
        }
        DonHangDAO.deleteById(idDonHang);
        return ResponseEntity.ok().build();
    }
}
