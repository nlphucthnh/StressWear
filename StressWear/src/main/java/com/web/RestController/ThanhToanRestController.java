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
@RequestMapping("/api/thanhtoan")
public class ThanhToanRestController {

    @Autowired
    ThanhToanDAO thanhToanDAO;

    @GetMapping
    public ResponseEntity<List<ThanhToan>> findAll(){
        return ResponseEntity.ok(thanhToanDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ThanhToan> findById(@PathVariable("id") String idThanhToan){
       Optional<ThanhToan> optional = thanhToanDAO.findById(Integer.valueOf(idThanhToan));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<ThanhToan> post(@RequestBody ThanhToan thanhToan){
        if(thanhToanDAO.existsById(thanhToan.getIdThanhToan())){
            return ResponseEntity.badRequest().build();
        }
        thanhToanDAO.save(thanhToan);
        return ResponseEntity.ok(thanhToan);
    }

    @PutMapping("{id}")
    public ResponseEntity<ThanhToan> put(@PathVariable("id") Integer idThanhToan, @RequestBody ThanhToan thanhToan){
        if(!thanhToanDAO.existsById(idThanhToan)){
            return ResponseEntity.notFound().build();
        }
        thanhToanDAO.save(thanhToan);
         return ResponseEntity.ok(thanhToan);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idThanhToan){
       if(!thanhToanDAO.existsById(idThanhToan)){
            return ResponseEntity.notFound().build();
        }
        thanhToanDAO.deleteById(idThanhToan);
        return ResponseEntity.ok().build();
    }
}
