package com.web.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.DAO.DanhSachKTDAO;
import com.web.DAO.SanPhamChiTietDAO;
import com.web.DAO.SanPhamDAO;
import com.web.Entity.DanhSachKT;
import com.web.Entity.DanhSachKTKey;
import com.web.Entity.SanPham;
import com.web.Entity.SanPhamChiTiet;
import com.web.Entity.TaiKhoan;

@Controller
public class ProductController {
    @Autowired
    SanPhamDAO dao;

    @Autowired
    SanPhamChiTietDAO spctdao;

    @Autowired
    DanhSachKTDAO ktdao;

    @GetMapping("User/product")
    public String product(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 12, Sort.by("tenSanPham").ascending());
        var products = dao.findAll(pageable);
        var numberOfPages = products.getTotalPages();
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("products", products);
        model.addAttribute("currIndex", p.orElse(0));
        return "Product/User-product";
    }

    @GetMapping("/User/product/edit/{idSanPham}")
    public String product_Item(Model model, @PathVariable("idSanPham") Integer id) {
        SanPham products = dao.findById(id).get();
        List<SanPhamChiTiet> productItem = spctdao.findByidsanpham(id);
        model.addAttribute("products", products);
        model.addAttribute("productitem", productItem);
        return "Product/User-product-Item";
    }

}
