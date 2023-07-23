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

import com.web.DAO.DanhSachKTDAO;
import com.web.Entity.DanhSachKT;
import com.web.Entity.DanhSachKTKey;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/DanhSachKT")
public class DanhSachKTRestController {

    @Autowired
    DanhSachKTDAO DanhSachKTDAO;

    @GetMapping
    public ResponseEntity<List<DanhSachKT>> findAll(){
        return ResponseEntity.ok(DanhSachKTDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<DanhSachKT> findById(@PathVariable("id") DanhSachKTKey idDanhSachKT){
       Optional<DanhSachKT> optional = DanhSachKTDAO.findById(idDanhSachKT.getIdKichThuoc(),idDanhSachKT.getIdSanPhamChiTiet());
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<DanhSachKT> post(@RequestBody DanhSachKT DanhSachKT){
        DanhSachKTKey DanhSachKTKey = new DanhSachKTKey(DanhSachKT.getIdKichThuoc(), DanhSachKT.getIdSanPhamChiTiet());
        if(DanhSachKTDAO.existsById(DanhSachKTKey)){
            return ResponseEntity.badRequest().build();
        }
        DanhSachKTDAO.save(DanhSachKT);
        return ResponseEntity.ok(DanhSachKT);
    }

    @PutMapping("{id}")
    public ResponseEntity<DanhSachKT> put(@PathVariable("id") DanhSachKTKey idDanhSachKT, @RequestBody DanhSachKT DanhSachKT){
        if(!DanhSachKTDAO.existsById(idDanhSachKT)){
            return ResponseEntity.notFound().build();
        }
        DanhSachKTDAO.save(DanhSachKT);
         return ResponseEntity.ok(DanhSachKT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") DanhSachKTKey idDanhSachKT){
       if(!DanhSachKTDAO.existsById(idDanhSachKT)){
            return ResponseEntity.notFound().build();
        }
        DanhSachKTDAO.deleteById(idDanhSachKT);
        return ResponseEntity.ok().build();
    }
}