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

import com.web.DAO.DanhSachHADAO;
import com.web.Entity.DanhSachHA;
import com.web.Entity.DanhSachHAKey;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/DanhSachHA")
public class DanhSachHARestController {

    @Autowired
    DanhSachHADAO DanhSachHADAO;

    @GetMapping
    public ResponseEntity<List<DanhSachHA>> findAll(){
        return ResponseEntity.ok(DanhSachHADAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<DanhSachHA> findById(@PathVariable("id") DanhSachHAKey idDanhSachHA){
       Optional<DanhSachHA> optional = DanhSachHADAO.findById(idDanhSachHA.getIdHinhAnh(),idDanhSachHA.getIdSanPhamChiTiet());
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<DanhSachHA> post(@RequestBody DanhSachHA DanhSachHA){
        DanhSachHAKey danhSachHAKey = new DanhSachHAKey(DanhSachHA.getIdHinhAnh(), DanhSachHA.getIdSanPhamChiTiet());
        if(DanhSachHADAO.existsById(danhSachHAKey)){
            return ResponseEntity.badRequest().build();
        }
        DanhSachHADAO.save(DanhSachHA);
        return ResponseEntity.ok(DanhSachHA);
    }

    @PutMapping("{id}")
    public ResponseEntity<DanhSachHA> put(@PathVariable("id") DanhSachHAKey idDanhSachHA, @RequestBody DanhSachHA DanhSachHA){
        if(!DanhSachHADAO.existsById(idDanhSachHA)){
            return ResponseEntity.notFound().build();
        }
        DanhSachHADAO.save(DanhSachHA);
         return ResponseEntity.ok(DanhSachHA);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") DanhSachHAKey idDanhSachHA){
       if(!DanhSachHADAO.existsById(idDanhSachHA)){
            return ResponseEntity.notFound().build();
        }
        DanhSachHADAO.deleteById(idDanhSachHA);
        return ResponseEntity.ok().build();
    }
}
