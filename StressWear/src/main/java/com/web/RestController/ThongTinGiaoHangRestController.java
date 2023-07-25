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

import com.web.DAO.ThongTinGiaoHangDAO;
import com.web.Entity.ThongTinGiaoHang;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/thongtingiaohang")
public class ThongTinGiaoHangRestController {

    @Autowired
    ThongTinGiaoHangDAO ThongTinGiaoHangDAO;

    @GetMapping
    public ResponseEntity<List<ThongTinGiaoHang>> findAll(){
        return ResponseEntity.ok(ThongTinGiaoHangDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ThongTinGiaoHang> findById(@PathVariable("id") String idThongTinGiaoHang){
       Optional<ThongTinGiaoHang> optional = ThongTinGiaoHangDAO.findById(Integer.valueOf(idThongTinGiaoHang));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<ThongTinGiaoHang> post(@RequestBody ThongTinGiaoHang ThongTinGiaoHang){
        if(ThongTinGiaoHangDAO.existsById(ThongTinGiaoHang.getIdThongTinGiaoHang())){
            return ResponseEntity.badRequest().build();
        }
        ThongTinGiaoHangDAO.save(ThongTinGiaoHang);
        return ResponseEntity.ok(ThongTinGiaoHang);
    }

    @PutMapping("{id}")
    public ResponseEntity<ThongTinGiaoHang> put(@PathVariable("id") Integer idThongTinGiaoHang, @RequestBody ThongTinGiaoHang ThongTinGiaoHang){
        if(!ThongTinGiaoHangDAO.existsById(idThongTinGiaoHang)){
            return ResponseEntity.notFound().build();
        }
        ThongTinGiaoHangDAO.save(ThongTinGiaoHang);
         return ResponseEntity.ok(ThongTinGiaoHang);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idThongTinGiaoHang){
       if(!ThongTinGiaoHangDAO.existsById(idThongTinGiaoHang)){
            return ResponseEntity.notFound().build();
        }
        ThongTinGiaoHangDAO.deleteById(idThongTinGiaoHang);
        return ResponseEntity.ok().build();
    }
}
