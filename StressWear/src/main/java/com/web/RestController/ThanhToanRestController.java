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

import com.web.DAO.ThanhToanDAO;
import com.web.Entity.ThanhToan;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/ThanhToan")
public class ThanhToanRestController {

    @Autowired
    ThanhToanDAO ThanhToanDAO;

    @GetMapping
    public ResponseEntity<List<ThanhToan>> findAll(){
        return ResponseEntity.ok(ThanhToanDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ThanhToan> findById(@PathVariable("id") String idThanhToan){
       Optional<ThanhToan> optional = ThanhToanDAO.findById(Integer.valueOf(idThanhToan));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<ThanhToan> post(@RequestBody ThanhToan ThanhToan){
        if(ThanhToanDAO.existsById(ThanhToan.getIdThanhToan())){
            return ResponseEntity.badRequest().build();
        }
        ThanhToanDAO.save(ThanhToan);
        return ResponseEntity.ok(ThanhToan);
    }

    @PutMapping("{id}")
    public ResponseEntity<ThanhToan> put(@PathVariable("id") Integer idThanhToan, @RequestBody ThanhToan ThanhToan){
        if(!ThanhToanDAO.existsById(idThanhToan)){
            return ResponseEntity.notFound().build();
        }
        ThanhToanDAO.save(ThanhToan);
         return ResponseEntity.ok(ThanhToan);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idThanhToan){
       if(!ThanhToanDAO.existsById(idThanhToan)){
            return ResponseEntity.notFound().build();
        }
        ThanhToanDAO.deleteById(idThanhToan);
        return ResponseEntity.ok().build();
    }
}
