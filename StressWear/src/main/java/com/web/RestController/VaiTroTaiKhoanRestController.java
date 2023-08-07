package com.web.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.web.DAO.VaiTroTaiKhoanDAO;
import com.web.Entity.TaiKhoan;
import com.web.Entity.VaiTroTaiKhoan;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/vaitrotaikhoan")
public class VaiTroTaiKhoanRestController {

    @Autowired
    VaiTroTaiKhoanDAO vaiTroTaiKhoanDAO;

    @GetMapping
    public ResponseEntity<List<VaiTroTaiKhoan>> findAll(){
        return ResponseEntity.ok(vaiTroTaiKhoanDAO.findAll());
    }
    
    @GetMapping("paging")
    public ResponseEntity<Page<VaiTroTaiKhoan>> findAllPage2(
            @RequestParam(name = "tenDangNhap", defaultValue = "") String tenDangNhap,
            @RequestParam("page") Optional<Integer> numberpage) {
        Pageable pageableVTTK = PageRequest.of(numberpage.orElse(0), 5);
        Page<VaiTroTaiKhoan> pageVaiTroTaiKhoan = vaiTroTaiKhoanDAO.findByVaiTroTenDangNhap(tenDangNhap, pageableVTTK);
        return ResponseEntity.ok(pageVaiTroTaiKhoan);
    }

    @GetMapping("{id}")
    public ResponseEntity<VaiTroTaiKhoan> findById(@PathVariable("id") Integer idVaiTro){
       Optional<VaiTroTaiKhoan> optional = vaiTroTaiKhoanDAO.findById(idVaiTro);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());  
    }

    @PostMapping()
    public ResponseEntity<VaiTroTaiKhoan> post(@RequestBody VaiTroTaiKhoan vaiTroTaiKhoan){
        if(vaiTroTaiKhoanDAO.existsById(vaiTroTaiKhoan.getIdVaiTroTaiKhoan())){
            return ResponseEntity.badRequest().build();
        }
        vaiTroTaiKhoanDAO.save(vaiTroTaiKhoan);
        return ResponseEntity.ok(vaiTroTaiKhoan);
    }

    @PutMapping("{id}")
    public ResponseEntity<VaiTroTaiKhoan> put(@PathVariable("id") Integer idVaiTro, @RequestBody VaiTroTaiKhoan vaiTro){
        if(!vaiTroTaiKhoanDAO.existsById(idVaiTro)){
            return ResponseEntity.notFound().build();
        }
        vaiTroTaiKhoanDAO.save(vaiTro);
         return ResponseEntity.ok(vaiTro);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idVaiTro){
       if(!vaiTroTaiKhoanDAO.existsById(idVaiTro)){
            return ResponseEntity.notFound().build();
        }
        vaiTroTaiKhoanDAO.deleteById(idVaiTro);
        return ResponseEntity.ok().build();
    }
}
