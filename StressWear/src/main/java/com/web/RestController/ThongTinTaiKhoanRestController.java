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

import com.web.DAO.ThongTinTaiKhoanDAO;
import com.web.Entity.ThongTinTaiKhoan;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/ThongTinTaiKhoan")
public class ThongTinTaiKhoanRestController {

    @Autowired
    ThongTinTaiKhoanDAO ThongTinTaiKhoanDAO;

    @GetMapping
    public ResponseEntity<List<ThongTinTaiKhoan>> findAll(){
        return ResponseEntity.ok(ThongTinTaiKhoanDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ThongTinTaiKhoan> findById(@PathVariable("id") String idThongTinTaiKhoan){
       Optional<ThongTinTaiKhoan> optional = ThongTinTaiKhoanDAO.findById(Integer.valueOf(idThongTinTaiKhoan));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<ThongTinTaiKhoan> post(@RequestBody ThongTinTaiKhoan ThongTinTaiKhoan){
        if(ThongTinTaiKhoanDAO.existsById(ThongTinTaiKhoan.getIdThongTinTaiKhoan())){
            return ResponseEntity.badRequest().build();
        }
        ThongTinTaiKhoanDAO.save(ThongTinTaiKhoan);
        return ResponseEntity.ok(ThongTinTaiKhoan);
    }

    @PutMapping("{id}")
    public ResponseEntity<ThongTinTaiKhoan> put(@PathVariable("id") Integer idThongTinTaiKhoan, @RequestBody ThongTinTaiKhoan ThongTinTaiKhoan){
        if(!ThongTinTaiKhoanDAO.existsById(idThongTinTaiKhoan)){
            return ResponseEntity.notFound().build();
        }
        ThongTinTaiKhoanDAO.save(ThongTinTaiKhoan);
         return ResponseEntity.ok(ThongTinTaiKhoan);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idThongTinTaiKhoan){
       if(!ThongTinTaiKhoanDAO.existsById(idThongTinTaiKhoan)){
            return ResponseEntity.notFound().build();
        }
        ThongTinTaiKhoanDAO.deleteById(idThongTinTaiKhoan);
        return ResponseEntity.ok().build();
    }
}
