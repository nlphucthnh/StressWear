package com.web.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.DAO.DanhSachKTDAO;
import com.web.DAO.NhomLoaiDAO;
import com.web.DAO.SanPhamChiTietDAO;
import com.web.DAO.SanPhamDAO;
import com.web.DAO.TaiKhoanDAO;
import com.web.Entity.NhomLoai;
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

    @Autowired
    TaiKhoanDAO tkdao;

    @Autowired
    NhomLoaiDAO nlDao;

    // Sản phẩm
    @GetMapping("User/product")
    public String product(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 12, Sort.by("tenSanPham").ascending());
        var products = dao.findAll(pageable);
        var numberOfPages = products.getTotalPages();
        List<NhomLoai> nhomloai = nlDao.findAll();
        model.addAttribute("nhomloai", nhomloai);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("products", products);
        model.addAttribute("currIndex", p.orElse(0));
        return "User/User-product";
    }

    // Chi tiết sản phẩm
    @GetMapping("/User/product/edit/{idSanPham}")
    public String product_Item(Model model, @PathVariable("idSanPham") Integer id) {
        SanPham products = dao.findById(id).get();
        List<SanPhamChiTiet> productItem = spctdao.findByidsanpham(id);
        List<NhomLoai> nhomloai = nlDao.findAll();
        model.addAttribute("nhomloai", nhomloai);
        List<TaiKhoan> tk = tkdao.findByTenDangNhap("thienlc");
        model.addAttribute("user", tk);
        model.addAttribute("products", products);
        model.addAttribute("productitem", productItem);
        return "User/User-product-Item";
    }

    // List Sản phẩm
    @GetMapping("/User/productlist/{idNhomLoai}")
    public String productList(Model model, @PathVariable("idNhomLoai") Integer id) {
        List<SanPham> nhomloaisanpham = dao.findByNhomLoai(id);
        List<NhomLoai> nhomloai = nlDao.findAll();
        model.addAttribute("nhomloaisanpham", nhomloaisanpham);
        model.addAttribute("nhomloai", nhomloai);
        return "User/User-product-List";
    }

    // Sắp xếp theo a-z, z-a
    @PostMapping("/User/productlist/{idNhomLoai}/name")
    public String productListPage(Model model, @PathVariable("idNhomLoai") Integer idNhomLoai,
            @RequestParam("selectName") String select) {
        // model.addAttribute("title", idNhomLoai);
        if (select.equals("AZ")) {
            List<SanPham> prolists = dao.findByLoaiSanpham("%" + idNhomLoai + "%",
                    Sort.by(Direction.ASC, "tenSanPham"));
            model.addAttribute("nhomloaisanpham", prolists);
            model.addAttribute("messagename", "Lọc từ A - Z");
        } else {
            List<SanPham> prolists = dao.findByLoaiSanpham("%" + idNhomLoai + "%",
                    Sort.by(Direction.DESC, "tenSanPham"));
            model.addAttribute("nhomloaisanpham", prolists);
            model.addAttribute("messagename", "Lọc từ Z - A");
        }
        return "User/User-product-List";
    }

    // //Lọc theo giá
    @PostMapping("/User/productlist/{idNhomLoai}/price")
    public String productListPagePrice(Model model, @PathVariable("idNhomLoai") Integer idNhomLoai,
            @RequestParam("selectPrice") String selectPrice) {
        model.addAttribute("title", idNhomLoai);
        switch (selectPrice) {
            case "1":
                model.addAttribute("messages", "Lọc từ 0đ đến 100.000đ");
                List<SanPham> prolists0_1 = dao.findByLoaiSanphamPrice(idNhomLoai,
                        Sort.by(Direction.ASC, "tenSanPham"), 0,
                        100000);
                model.addAttribute("nhomloaisanpham", prolists0_1);
                break;
            case "2":
                model.addAttribute("messages", "Lọc từ 100.000đ đến 200.000đ");
                List<SanPham> prolists1_5 = dao.findByLoaiSanphamPrice(idNhomLoai,
                        Sort.by(Direction.ASC, "tenSanPham"),
                        100000,
                        200000);
                model.addAttribute("nhomloaisanpham", prolists1_5);
                break;
            case "3":
                model.addAttribute("messages", "Lọc từ 200.000đ đến 300.000đ");
                List<SanPham> prolists5_10 = dao.findByLoaiSanphamPrice(idNhomLoai,
                        Sort.by(Direction.ASC, "tenSanPham"),
                        200000,
                        300000);
                model.addAttribute("nhomloaisanpham", prolists5_10);
                break;
            case "4":
                model.addAttribute("messages", "Lọc từ 300.000đ đến 400.000đ");
                List<SanPham> prolists10_15 = dao.findByLoaiSanphamPrice(
                        idNhomLoai,
                        Sort.by(Direction.ASC, "tenSanPham"),
                        300000,
                        400000);
                model.addAttribute("nhomloaisanpham", prolists10_15);
                break;
            case "5":
                model.addAttribute("messages", "Lọc từ 400.000đ đến 500.000đ");
                List<SanPham> prolists15_20 = dao.findByLoaiSanphamPrice(
                        idNhomLoai,
                        Sort.by(Direction.ASC, "tenSanPham"),
                        400000,
                        500000);
                model.addAttribute("nhomloaisanpham", prolists15_20);
                break;
            case "6":
                model.addAttribute("messages", "Lọc từ 500.000đ Trở lên");
                List<SanPham> prolists20_ = dao.findByLoaiSanphamPrice(idNhomLoai,
                        Sort.by(Direction.ASC, "tenSanPham"),
                        500000,
                        10000000);
                model.addAttribute("nhomloaisanpham", prolists20_);
                break;
            default:
                model.addAttribute("messages", "Lỗi");
                break;
        }

        return "User/User-product-List";
    }

}
