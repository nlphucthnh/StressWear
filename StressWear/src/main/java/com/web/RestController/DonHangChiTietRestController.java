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

import com.web.DAO.DonHangChiTietDAO;
import com.web.DAO.DonHangDAO;
import com.web.Entity.DonHang;
import com.web.Entity.DonHangChiTiet;
import com.web.Entity.ThongKe;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/donhangchitiet")
public class DonHangChiTietRestController {

    @Autowired
    DonHangChiTietDAO donHangChiTietDAO;
    
    @Autowired
    DonHangDAO donHangDAO;

    @GetMapping
    public ResponseEntity<List<DonHangChiTiet>> findAll(){
        return ResponseEntity.ok(donHangChiTietDAO.findAll());
    }

    @GetMapping("thongkesanpham")
    public ResponseEntity<List<ThongKe>> findListThongKeSP() {
        return ResponseEntity.ok(donHangChiTietDAO.findListThongKeSP());
    }

       @GetMapping("paging")
    public ResponseEntity<Page<ThongKe>> findAllPage2(@RequestParam("page") Optional<Integer> numberpage) {
        Pageable pageableTK = PageRequest.of(numberpage.orElse(0), 5);
        Page<ThongKe> pageThongKe = donHangChiTietDAO.findListThongKeSP_NAMESP(pageableTK);
        return ResponseEntity.ok(pageThongKe);
    }
    
    @GetMapping("donhang/{id}")
    public ResponseEntity<List<DonHangChiTiet>> find_DHCT_by_DH(@PathVariable("id") Integer idDonHang){
        Optional<DonHang> donHang = donHangDAO.findById(idDonHang);
        return ResponseEntity.ok(donHangChiTietDAO.findByDonHangDHCT(donHang));
    }

    @GetMapping("{id}")
    public ResponseEntity<DonHangChiTiet> findById(@PathVariable("id") String idDonHangChiTiet){
       Optional<DonHangChiTiet> optional = donHangChiTietDAO.findById(Integer.valueOf(idDonHangChiTiet));
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
        
    }

    @PostMapping()
    public ResponseEntity<DonHangChiTiet> post(@RequestBody DonHangChiTiet donHangChiTiet){
        if(donHangChiTietDAO.existsById(donHangChiTiet.getIdDonHangChiTiet())){
            return ResponseEntity.badRequest().build();
        }
        donHangChiTietDAO.save(donHangChiTiet);
        return ResponseEntity.ok(donHangChiTiet);
    }

    @PutMapping("{id}")
    public ResponseEntity<DonHangChiTiet> put(@PathVariable("id") Integer idDonHangChiTiet, @RequestBody DonHangChiTiet donHangChiTiet){
        if(!donHangChiTietDAO.existsById(idDonHangChiTiet)){
            return ResponseEntity.notFound().build();
        }
        donHangChiTietDAO.save(donHangChiTiet);
         return ResponseEntity.ok(donHangChiTiet);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idDonHangChiTiet){
       if(!donHangChiTietDAO.existsById(idDonHangChiTiet)){
            return ResponseEntity.notFound().build();
        }
        donHangChiTietDAO.deleteById(idDonHangChiTiet);
        return ResponseEntity.ok().build();
    }
}
