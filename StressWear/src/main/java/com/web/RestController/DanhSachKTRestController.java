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
@RequestMapping("/api/danhsachkt")
public class DanhSachKTRestController {

    @Autowired
    DanhSachKTDAO danhSachKTDAO;

    @GetMapping
    public ResponseEntity<List<DanhSachKT>> findAll(){
        return ResponseEntity.ok(danhSachKTDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<DanhSachKT> findById(@PathVariable("id") DanhSachKTKey idDanhSachKT){
       Optional<DanhSachKT> optional = danhSachKTDAO.findById(idDanhSachKT);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<DanhSachKT> post(@RequestBody DanhSachKT danhSachKT){
        DanhSachKTKey DanhSachKTKey = new DanhSachKTKey(danhSachKT.getIdKichThuoc(), danhSachKT.getIdSanPhamChiTiet());
        if(danhSachKTDAO.existsById(DanhSachKTKey)){
            return ResponseEntity.badRequest().build();
        }
        danhSachKTDAO.save(danhSachKT);
        return ResponseEntity.ok(danhSachKT);
    }

    @PutMapping("{id}")
    public ResponseEntity<DanhSachKT> put(@PathVariable("id") DanhSachKTKey idDanhSachKT, @RequestBody DanhSachKT danhSachKT){
        if(!danhSachKTDAO.existsById(idDanhSachKT)){
            return ResponseEntity.notFound().build();
        }
        danhSachKTDAO.save(danhSachKT);
         return ResponseEntity.ok(danhSachKT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") DanhSachKTKey idDanhSachKT){
       if(!danhSachKTDAO.existsById(idDanhSachKT)){
            return ResponseEntity.notFound().build();
        }
        danhSachKTDAO.deleteById(idDanhSachKT);
        return ResponseEntity.ok().build();
    }
}
