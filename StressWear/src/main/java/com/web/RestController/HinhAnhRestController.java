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

import com.web.DAO.HinhAnhDAO;
import com.web.Entity.HinhAnh;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/hinhanh")
public class HinhAnhRestController {

    @Autowired
    HinhAnhDAO hinhAnhDAO;

    @GetMapping
    public ResponseEntity<List<HinhAnh>> findAll(){
        return ResponseEntity.ok(hinhAnhDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<HinhAnh> findById(@PathVariable("id") String idHinhAnh){
       Optional<HinhAnh> optional = hinhAnhDAO.findById(idHinhAnh);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<HinhAnh> post(@RequestBody HinhAnh hinhAnh){
        if(hinhAnhDAO.existsById(hinhAnh.getIdHinhAnh())){
            return ResponseEntity.badRequest().build();
        }
        HinhAnh hinhanh2 =  hinhAnhDAO.save(hinhAnh);
        return ResponseEntity.ok(hinhanh2);
    }

    @PutMapping("{id}")
    public ResponseEntity<HinhAnh> put(@PathVariable("id") String idHinhAnh, @RequestBody HinhAnh hinhAnh){
        if(!hinhAnhDAO.existsById(idHinhAnh)){
            return ResponseEntity.notFound().build();
        }
        hinhAnhDAO.save(hinhAnh);
         return ResponseEntity.ok(hinhAnh);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String idHinhAnh){
       if(!hinhAnhDAO.existsById(idHinhAnh)){
            return ResponseEntity.notFound().build();
        }
        hinhAnhDAO.deleteById(idHinhAnh);
        return ResponseEntity.ok().build();
    }
}
