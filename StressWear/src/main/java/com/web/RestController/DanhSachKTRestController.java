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

    @GetMapping("sanphamchitiet/{id}")
    public ResponseEntity<List<DanhSachKT>> findByListSPCTById(@PathVariable("id") Integer idSanPhamChiTiet){
        return ResponseEntity.ok(danhSachKTDAO.findByIdSanPhamChiTiet(idSanPhamChiTiet));        
    }

    @GetMapping("{id}")
    public ResponseEntity<DanhSachKT> findById(@PathVariable("id") Integer idDanhSachKT){
       Optional<DanhSachKT> optional = danhSachKTDAO.findById(idDanhSachKT);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<DanhSachKT> post(@RequestBody DanhSachKT danhSachKT){
        if(danhSachKTDAO.existsById(danhSachKT.getIdDanhSachKT())){
            return ResponseEntity.badRequest().build();
        }
        DanhSachKT danhSachKT2 = danhSachKTDAO.save(danhSachKT);
        return ResponseEntity.ok(danhSachKT2);
    }

    @PutMapping("{id}")
    public ResponseEntity<DanhSachKT> put(@PathVariable("id") Integer idDanhSachKT, @RequestBody DanhSachKT danhSachKT){
        if(!danhSachKTDAO.existsById(idDanhSachKT)){
            return ResponseEntity.notFound().build();
        }
        danhSachKTDAO.save(danhSachKT);
         return ResponseEntity.ok(danhSachKT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idDanhSachKT){
       if(!danhSachKTDAO.existsById(idDanhSachKT)){
            return ResponseEntity.notFound().build();
        }
        danhSachKTDAO.deleteById(idDanhSachKT);
        return ResponseEntity.ok().build();
    }
}
