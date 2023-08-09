package com.web.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.DAO.TaiKhoanDAO;
import com.web.DAO.VaiTroDAO;
import com.web.DAO.VaiTroTaiKhoanDAO;
import com.web.Entity.TaiKhoan;
import com.web.Entity.VaiTroTaiKhoan;

@RestController
@CrossOrigin(origins = "*")
public class demoRestController {
    @Autowired
    TaiKhoanDAO taiKhoanDAO;

    @Autowired
    VaiTroDAO vaiTroDAO;

    @Autowired
    VaiTroTaiKhoanDAO vaiTroTaiKhoanDAO;

    @GetMapping("/api/vaitrotaikhoan/taikhoan/{id}")
    public ResponseEntity<List<VaiTroTaiKhoan>> find_VTTK_By_TK(@PathVariable("id") String tenDangNhap) {
        TaiKhoan taiKhoan = taiKhoanDAO.findById(tenDangNhap).get();
        return ResponseEntity.ok(vaiTroTaiKhoanDAO.findByTaiKhoan(taiKhoan));
    }

    @GetMapping("/api/taikhoan/notrole")
    public ResponseEntity<List<TaiKhoan>> find_TK_not_VT() {
        return ResponseEntity.ok(taiKhoanDAO.find_TK_NOT_VT());
    }

}
