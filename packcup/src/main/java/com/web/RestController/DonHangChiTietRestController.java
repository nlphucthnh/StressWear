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

import com.web.DAO.DonHangChiTietDAO;
import com.web.Entity.DonHangChiTiet;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/donhangchitiet")
public class DonHangChiTietRestController {

    @Autowired
    DonHangChiTietDAO donHangChiTietDAO;

    @GetMapping
    public ResponseEntity<List<DonHangChiTiet>> findAll(){
        return ResponseEntity.ok(donHangChiTietDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<DonHangChiTiet> findById(@PathVariable("id") String idDonHangChiTiet){
       Optional<DonHangChiTiet> optional = donHangChiTietDAO.findById(Integer.valueOf(idDonHangChiTiet));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<DonHangChiTiet> post(@RequestBody DonHangChiTiet donHangChiTiet){
        if(donHangChiTietDAO.existsById(donHangChiTiet.getIdDonHangChiTiet())){
            return ResponseEntity.badRequest().build();
        }
        donHangChiTietDAO.save(donHangChiTiet);
        return ResponseEntity.ok(donHangChiTiet);
    }

    @PutMapping("{id}")
    public ResponseEntity<DonHangChiTiet> put(@PathVariable("id") Integer idDonHangChiTiet, @RequestBody DonHangChiTiet donHangChiTiet){
        if(!donHangChiTietDAO.existsById(idDonHangChiTiet)){
            return ResponseEntity.notFound().build();
        }
        donHangChiTietDAO.save(donHangChiTiet);
         return ResponseEntity.ok(donHangChiTiet);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idDonHangChiTiet){
       if(!donHangChiTietDAO.existsById(idDonHangChiTiet)){
            return ResponseEntity.notFound().build();
        }
        donHangChiTietDAO.deleteById(idDonHangChiTiet);
        return ResponseEntity.ok().build();
    }
}
