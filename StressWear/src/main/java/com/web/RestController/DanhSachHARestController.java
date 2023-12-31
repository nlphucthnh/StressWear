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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/danhsachha")
public class DanhSachHARestController {

    @Autowired
    DanhSachHADAO danhSachHADAO;

    @GetMapping
    public ResponseEntity<List<DanhSachHA>> findAll(){
        return ResponseEntity.ok(danhSachHADAO.findAll());
    }

    @GetMapping("sanphamchitiet/{id}")
    public ResponseEntity<List<DanhSachHA>> findListSPCTById(@PathVariable("id") Integer idSanPhamChiTiet){
        return ResponseEntity.ok(danhSachHADAO.findByIdSanPhamChiTiet(idSanPhamChiTiet));        
    }


    @GetMapping("{id}")
    public ResponseEntity<DanhSachHA> findById(@PathVariable("id") Integer idDanhSachHA){
       Optional<DanhSachHA> optional = danhSachHADAO.findById(idDanhSachHA);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }
    
    @PostMapping()
    public ResponseEntity<DanhSachHA> post(@RequestBody DanhSachHA danhSachHA){
        if(danhSachHADAO.existsById(danhSachHA.getIdDanhSachHA())){
            return ResponseEntity.badRequest().build();
        }
        danhSachHADAO.save(danhSachHA);
        return ResponseEntity.ok(danhSachHA);
    }

    @PutMapping("{id}")
    public ResponseEntity<DanhSachHA> put(@PathVariable("id") Integer idDanhSachHA, @RequestBody DanhSachHA danhSachHA){
        if(!danhSachHADAO.existsById(idDanhSachHA)){
            return ResponseEntity.notFound().build();
        }
        danhSachHADAO.save(danhSachHA);
         return ResponseEntity.ok(danhSachHA);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idDanhSachHA){
       if(!danhSachHADAO.existsById(idDanhSachHA)){
            return ResponseEntity.notFound().build();
        }
        danhSachHADAO.deleteById(idDanhSachHA);
        return ResponseEntity.ok().build();
    }
}
