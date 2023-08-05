package com.web.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.DAO.DanhSachKTDAO;
import com.web.DAO.NhomLoaiDAO;
import com.web.DAO.SanPhamChiTietDAO;
import com.web.DAO.SanPhamDAO;
import com.web.DAO.TaiKhoanDAO;
import com.web.Entity.NhomLoai;
import com.web.Entity.TaiKhoan;

@Controller
public class CartController {

    @Autowired
    SanPhamDAO dao;

    @Autowired
    SanPhamChiTietDAO spctdao;

    @Autowired
    DanhSachKTDAO ktdao;

    @Autowired
    TaiKhoanDAO tkdao;

    @Autowired
    NhomLoaiDAO nlDao;
        // User tam
    @GetMapping("/User/product/sale")
    public String demo(Model model) {
        List<NhomLoai> nhomloai = nlDao.findAll();
        model.addAttribute("nhomloai", nhomloai);
        List<TaiKhoan> tk = tkdao.findByTenDangNhap("thienlc");
        model.addAttribute("user", tk);
        return "User/User-cart";
    }
}
