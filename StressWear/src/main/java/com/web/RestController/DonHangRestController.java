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

import com.fasterxml.jackson.databind.JsonNode;
import com.web.DAO.DonHangDAO;
import com.web.Entity.DonHang;
import com.web.service.DonHangService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/donhang")
public class DonHangRestController {

    @Autowired
    DonHangDAO donHangDAO;

    @Autowired
    DonHangService donhangSevice;

    @PostMapping()
    public DonHang create(@RequestBody JsonNode donhangData) {
        return donhangSevice.create(donhangData);
    }

    @GetMapping()
    public ResponseEntity<List<DonHang>> findAll() {
        return ResponseEntity.ok(donHangDAO.findAll());
    }

  

    @GetMapping("{id}")
    public ResponseEntity<DonHang> findById(@PathVariable("id") String idDonHang) {
        Optional<DonHang> optional = donHangDAO.findById(Integer.valueOf(idDonHang));
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());

    }

    @GetMapping("paging")
    public ResponseEntity<Page<DonHang>> findAllPage(
            @RequestParam(name = "idDonHang", defaultValue = "") String idDonHang,
            @RequestParam("page") Optional<Integer> numberpage) {
        Pageable pageableDH = PageRequest.of(numberpage.orElse(0), 5);
        Page<DonHang> pageDonHang = donHangDAO.findById(idDonHang, pageableDH);
        return ResponseEntity.ok(pageDonHang);
    }

    // @PostMapping("")
    // public ResponseEntity<DonHang> post(@RequestBody DonHang donHang) {
    // if (donHangDAO.existsById(donHang.getIdDonHang())) {
    // return ResponseEntity.badRequest().build();
    // }
    // donHangDAO.save(donHang);
    // return ResponseEntity.ok(donHang);
    // }

    @PutMapping("{id}")
    public ResponseEntity<DonHang> put(@PathVariable("id") Integer idDonHang, @RequestBody DonHang donHang) {
        if (!donHangDAO.existsById(idDonHang)) {
            return ResponseEntity.notFound().build();
        }
        donHangDAO.save(donHang);
        return ResponseEntity.ok(donHang);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idDonHang) {
        if (!donHangDAO.existsById(idDonHang)) {
            return ResponseEntity.notFound().build();
        }
        donHangDAO.deleteById(idDonHang);
        return ResponseEntity.ok().build();
    }
}
