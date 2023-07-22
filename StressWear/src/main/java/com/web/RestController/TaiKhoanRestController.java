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

import com.web.DAO.TaiKhoanDAO;
import com.web.Entity.TaiKhoan;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TaiKhoan")
public class TaiKhoanRestController {

    @Autowired
    TaiKhoanDAO TaiKhoanDAO;

    @GetMapping
    public ResponseEntity<List<TaiKhoan>> findAll(){
        return ResponseEntity.ok(TaiKhoanDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TaiKhoan> findById(@PathVariable("id") String idTaiKhoan){
       Optional<TaiKhoan> optional = TaiKhoanDAO.findById(idTaiKhoan);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<TaiKhoan> post(@RequestBody TaiKhoan TaiKhoan){
        if(TaiKhoanDAO.existsById(TaiKhoan.getTenDangNhap())){
            return ResponseEntity.badRequest().build();
        }
        TaiKhoanDAO.save(TaiKhoan);
        return ResponseEntity.ok(TaiKhoan);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaiKhoan> put(@PathVariable("id") String tenDangNhap, @RequestBody TaiKhoan TaiKhoan){
        if(!TaiKhoanDAO.existsById(tenDangNhap)){
            return ResponseEntity.notFound().build();
        }
        TaiKhoanDAO.save(TaiKhoan);
         return ResponseEntity.ok(TaiKhoan);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String tenDangNhap){
       if(!TaiKhoanDAO.existsById(tenDangNhap)){
            return ResponseEntity.notFound().build();
        }
        TaiKhoanDAO.deleteById(tenDangNhap);
        return ResponseEntity.ok().build();
    }
}
