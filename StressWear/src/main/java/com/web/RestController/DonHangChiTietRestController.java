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
    DonHangChiTietDAO DonHangChiTietDAO;

    @GetMapping
    public ResponseEntity<List<DonHangChiTiet>> findAll(){
        return ResponseEntity.ok(DonHangChiTietDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<DonHangChiTiet> findById(@PathVariable("id") String idDonHangChiTiet){
       Optional<DonHangChiTiet> optional = DonHangChiTietDAO.findById(Integer.valueOf(idDonHangChiTiet));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<DonHangChiTiet> post(@RequestBody DonHangChiTiet DonHangChiTiet){
        if(DonHangChiTietDAO.existsById(DonHangChiTiet.getIdDonHangChiTiet())){
            return ResponseEntity.badRequest().build();
        }
        DonHangChiTietDAO.save(DonHangChiTiet);
        return ResponseEntity.ok(DonHangChiTiet);
    }

    @PutMapping("{id}")
    public ResponseEntity<DonHangChiTiet> put(@PathVariable("id") Integer idDonHangChiTiet, @RequestBody DonHangChiTiet DonHangChiTiet){
        if(!DonHangChiTietDAO.existsById(idDonHangChiTiet)){
            return ResponseEntity.notFound().build();
        }
        DonHangChiTietDAO.save(DonHangChiTiet);
         return ResponseEntity.ok(DonHangChiTiet);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idDonHangChiTiet){
       if(!DonHangChiTietDAO.existsById(idDonHangChiTiet)){
            return ResponseEntity.notFound().build();
        }
        DonHangChiTietDAO.deleteById(idDonHangChiTiet);
        return ResponseEntity.ok().build();
    }
}
