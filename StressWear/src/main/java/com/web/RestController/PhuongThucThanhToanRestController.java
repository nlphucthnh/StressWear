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

import com.web.DAO.PhuongThucThanhToanDAO;
import com.web.Entity.PhuongThucThanhToan;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/PhuongThucThanhToan")
public class PhuongThucThanhToanRestController {

    @Autowired
    PhuongThucThanhToanDAO PhuongThucThanhToanDAO;

    @GetMapping
    public ResponseEntity<List<PhuongThucThanhToan>> findAll(){
        return ResponseEntity.ok(PhuongThucThanhToanDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<PhuongThucThanhToan> findById(@PathVariable("id") String idPhuongThucThanhToan){
       Optional<PhuongThucThanhToan> optional = PhuongThucThanhToanDAO.findById(Integer.valueOf(idPhuongThucThanhToan));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<PhuongThucThanhToan> post(@RequestBody PhuongThucThanhToan PhuongThucThanhToan){
        if(PhuongThucThanhToanDAO.existsById(PhuongThucThanhToan.getIdPhuongThuc())){
            return ResponseEntity.badRequest().build();
        }
        PhuongThucThanhToanDAO.save(PhuongThucThanhToan);
        return ResponseEntity.ok(PhuongThucThanhToan);
    }

    @PutMapping("{id}")
    public ResponseEntity<PhuongThucThanhToan> put(@PathVariable("id") Integer idPhuongThucThanhToan, @RequestBody PhuongThucThanhToan PhuongThucThanhToan){
        if(!PhuongThucThanhToanDAO.existsById(idPhuongThucThanhToan)){
            return ResponseEntity.notFound().build();
        }
        PhuongThucThanhToanDAO.save(PhuongThucThanhToan);
         return ResponseEntity.ok(PhuongThucThanhToan);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idPhuongThucThanhToan){
       if(!PhuongThucThanhToanDAO.existsById(idPhuongThucThanhToan)){
            return ResponseEntity.notFound().build();
        }
        PhuongThucThanhToanDAO.deleteById(idPhuongThucThanhToan);
        return ResponseEntity.ok().build();
    }
}
