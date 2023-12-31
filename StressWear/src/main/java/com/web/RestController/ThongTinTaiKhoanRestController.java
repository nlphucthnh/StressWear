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
@RequestMapping("/api/thongtintaikhoan")
public class ThongTinTaiKhoanRestController {

    @Autowired
    ThongTinTaiKhoanDAO thongTinTaiKhoanDAO;

    @GetMapping
    public ResponseEntity<List<ThongTinTaiKhoan>> findAll(){
        return ResponseEntity.ok(thongTinTaiKhoanDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ThongTinTaiKhoan> findById(@PathVariable("id") String idThongTinTaiKhoan){
       Optional<ThongTinTaiKhoan> optional = thongTinTaiKhoanDAO.findById(Integer.valueOf(idThongTinTaiKhoan));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @GetMapping("taikhoan/{tenDangNhap}")
    public ResponseEntity<ThongTinTaiKhoan> findByTenDangNhap(@PathVariable("tenDangNhap") String tenDangNhap){
       Optional<ThongTinTaiKhoan> optional = thongTinTaiKhoanDAO.findBytaiKhoanTTTK2(tenDangNhap);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<ThongTinTaiKhoan> post(@RequestBody ThongTinTaiKhoan thongTinTaiKhoan){
        if(thongTinTaiKhoanDAO.existsById(thongTinTaiKhoan.getIdThongTinTaiKhoan())){
            return ResponseEntity.badRequest().build();
        }
        thongTinTaiKhoanDAO.save(thongTinTaiKhoan);
        return ResponseEntity.ok(thongTinTaiKhoan);
    }

    @PutMapping("{id}")
    public ResponseEntity<ThongTinTaiKhoan> put(@PathVariable("id") Integer idThongTinTaiKhoan, @RequestBody ThongTinTaiKhoan thongTinTaiKhoan){
        if(!thongTinTaiKhoanDAO.existsById(idThongTinTaiKhoan)){
            return ResponseEntity.notFound().build();
        }
        thongTinTaiKhoanDAO.save(thongTinTaiKhoan);
        return ResponseEntity.ok(thongTinTaiKhoan);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idThongTinTaiKhoan){
       if(!thongTinTaiKhoanDAO.existsById(idThongTinTaiKhoan)){
            return ResponseEntity.notFound().build();
        }
        thongTinTaiKhoanDAO.deleteById(idThongTinTaiKhoan);
        return ResponseEntity.ok().build();
    }
}
