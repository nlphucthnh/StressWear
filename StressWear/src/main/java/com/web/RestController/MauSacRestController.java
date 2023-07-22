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

import com.web.DAO.MauSacDAO;
import com.web.Entity.MauSac;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/MauSac")
public class MauSacRestController {

    @Autowired
    MauSacDAO MauSacDAO;

    @GetMapping
    public ResponseEntity<List<MauSac>> findAll(){
        return ResponseEntity.ok(MauSacDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<MauSac> findById(@PathVariable("id") String idMauSac){
       Optional<MauSac> optional = MauSacDAO.findById(Integer.valueOf(idMauSac));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<MauSac> post(@RequestBody MauSac MauSac){
        if(MauSacDAO.existsById(MauSac.getIdMauSac())){
            return ResponseEntity.badRequest().build();
        }
        MauSacDAO.save(MauSac);
        return ResponseEntity.ok(MauSac);
    }

    @PutMapping("{id}")
    public ResponseEntity<MauSac> put(@PathVariable("id") Integer idMauSac, @RequestBody MauSac MauSac){
        if(!MauSacDAO.existsById(idMauSac)){
            return ResponseEntity.notFound().build();
        }
        MauSacDAO.save(MauSac);
         return ResponseEntity.ok(MauSac);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idMauSac){
       if(!MauSacDAO.existsById(idMauSac)){
            return ResponseEntity.notFound().build();
        }
        MauSacDAO.deleteById(idMauSac);
        return ResponseEntity.ok().build();
    }
}
