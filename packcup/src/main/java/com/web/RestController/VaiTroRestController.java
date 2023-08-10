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

import com.web.DAO.VaiTroDAO;
import com.web.Entity.VaiTro;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/vaitro")
public class VaiTroRestController {

    @Autowired
    VaiTroDAO vaiTroDAO;

    @GetMapping 
    public ResponseEntity<List<VaiTro>> findAll(){
        return ResponseEntity.ok(vaiTroDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<VaiTro> findById(@PathVariable("id") String idVaiTro){
       Optional<VaiTro> optional = vaiTroDAO.findById(idVaiTro);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<VaiTro> post(@RequestBody VaiTro vaiTro){
        if(vaiTroDAO.existsById(vaiTro.getIdVaiTro())){
            return ResponseEntity.badRequest().build();
        }
        vaiTroDAO.save(vaiTro);
        return ResponseEntity.ok(vaiTro);
    }

    @PutMapping("{id}")
    public ResponseEntity<VaiTro> put(@PathVariable("id") String idVaiTro, @RequestBody VaiTro vaiTro){
        if(!vaiTroDAO.existsById(idVaiTro)){
            return ResponseEntity.notFound().build();
        }
        vaiTroDAO.save(vaiTro);
         return ResponseEntity.ok(vaiTro);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String idVaiTro){
       if(!vaiTroDAO.existsById(idVaiTro)){
            return ResponseEntity.notFound().build();
        }
        vaiTroDAO.deleteById(idVaiTro);
        return ResponseEntity.ok().build();
    }
}
