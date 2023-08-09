package com.web.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.web.DAO.TaiKhoanDAO;
import com.web.DAO.VaiTroDAO;
import com.web.DAO.VaiTroTaiKhoanDAO;
import com.web.Entity.TaiKhoan;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/taikhoan")
public class TaiKhoanRestController {

    @Autowired
    TaiKhoanDAO taiKhoanDAO;

    @Autowired
    VaiTroDAO vaiTroDAO;

    @Autowired
    VaiTroTaiKhoanDAO vaiTroTaiKhoanDAO;

    @GetMapping("/rest/authorities")
	public Map<String, Object> getAuthorities() {
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", vaiTroTaiKhoanDAO.findAll());
		data.put("accounts", taiKhoanDAO.findAll());
		data.put("roles", vaiTroDAO.findAll());
		return data;
	}

    @GetMapping
    public ResponseEntity<List<TaiKhoan>> findAll() {
        return ResponseEntity.ok(taiKhoanDAO.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TaiKhoan> findById(@PathVariable("id") String idTaiKhoan) {
        Optional<TaiKhoan> optional = taiKhoanDAO.findById(idTaiKhoan);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());

    }

    @GetMapping("paging")
    public ResponseEntity<Page<TaiKhoan>> findAllPage2(
            @RequestParam(name = "name", defaultValue = "") String nameaccount,
            @RequestParam("page") Optional<Integer> numberpage) {
        Pageable pageableTK = PageRequest.of(numberpage.orElse(0), 5);
        Page<TaiKhoan> pageTaiKhoan = taiKhoanDAO.findByTenDangNhap(nameaccount, pageableTK);
        return ResponseEntity.ok(pageTaiKhoan);
    }

    @PostMapping()
    public ResponseEntity<TaiKhoan> post(@RequestBody TaiKhoan taiKhoan) {
        if (taiKhoanDAO.existsById(taiKhoan.getTenDangNhap())) {
            return ResponseEntity.badRequest().build();
            // 400 Bad Request: Địa chỉ tồi
        }
        taiKhoanDAO.save(taiKhoan);
        return ResponseEntity.ok(taiKhoan);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaiKhoan> put(@PathVariable("id") String tenDangNhap, @RequestBody TaiKhoan taiKhoan) {
        if (!taiKhoanDAO.existsById(tenDangNhap)) {
            return ResponseEntity.notFound().build();
        }
        taiKhoanDAO.save(taiKhoan);
        return ResponseEntity.ok(taiKhoan);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String tenDangNhap) {
        if (!taiKhoanDAO.existsById(tenDangNhap)) {
            return ResponseEntity.notFound().build();
        }
        taiKhoanDAO.deleteById(tenDangNhap);
        return ResponseEntity.ok().build();
    }
}
