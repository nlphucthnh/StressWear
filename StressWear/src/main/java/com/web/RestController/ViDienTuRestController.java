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

import com.web.DAO.ViDienTuDAO;
import com.web.Entity.ViDienTu;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/vidientu")
public class ViDienTuRestController {

    @Autowired
    ViDienTuDAO viDienTuDAO;

    @GetMapping
    public ResponseEntity<List<ViDienTu>> findAll(){
        return ResponseEntity.ok(viDienTuDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ViDienTu> findById(@PathVariable("id") String idViDienTu){
       Optional<ViDienTu> optional = viDienTuDAO.findById(Integer.valueOf(idViDienTu));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<ViDienTu> post(@RequestBody ViDienTu viDienTu){
        if(viDienTuDAO.existsById(viDienTu.getIdViDienTu())){
            return ResponseEntity.badRequest().build();
        }
        viDienTuDAO.save(viDienTu);
        return ResponseEntity.ok(viDienTu);
    }

    @PutMapping("{id}")
    public ResponseEntity<ViDienTu> put(@PathVariable("id") Integer idViDienTu, @RequestBody ViDienTu viDienTu){
        if(!viDienTuDAO.existsById(idViDienTu)){
            return ResponseEntity.notFound().build();
        }
        viDienTuDAO.save(viDienTu);
         return ResponseEntity.ok(viDienTu);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idViDienTu){
       if(!viDienTuDAO.existsById(idViDienTu)){
            return ResponseEntity.notFound().build();
        }
        viDienTuDAO.deleteById(idViDienTu);
        return ResponseEntity.ok().build();
    }
}
