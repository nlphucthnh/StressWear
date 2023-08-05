package com.web.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.DAO.NhomLoaiDAO;
import com.web.DAO.SanPhamDAO;
import com.web.Entity.NhomLoai;

@Controller
public class IndexController {
    @Autowired
    SanPhamDAO dao;

    @Autowired
    NhomLoaiDAO nlDao;

    @GetMapping("main")
    public String index(Model model) {
        var products = dao.findAll();
        List<NhomLoai> nhomloai = nlDao.findAll();
        model.addAttribute("nhomloai", nhomloai);
        model.addAttribute("products", products);
        return "User/User-index";
    }
}
