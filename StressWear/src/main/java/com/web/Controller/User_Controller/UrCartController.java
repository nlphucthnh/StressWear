package com.web.Controller.User_Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.DAO.DanhSachKTDAO;
import com.web.DAO.NhomLoaiDAO;
import com.web.DAO.SanPhamChiTietDAO;
import com.web.DAO.SanPhamDAO;
import com.web.DAO.TaiKhoanDAO;
import com.web.DAO.ThongTinTaiKhoanDAO;
import com.web.Entity.NhomLoai;
import com.web.Entity.TaiKhoan;
import com.web.Entity.ThongTinTaiKhoan;

@Controller
public class UrCartController {
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


     @Autowired
    HttpSession session;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ThongTinTaiKhoanDAO thongTinTaiKhoanDAO;

    @Autowired
    TaiKhoanDAO taiKhoanDAO;

        // User tam
    @GetMapping("/User/product/sale")
    public String demo(Model model) {
        List<NhomLoai> nhomloai = nlDao.findAll();
        model.addAttribute("nhomloai", nhomloai);
        // List<TaiKhoan> tk = tkdao.findByTenDangNhap("thienlc");
        // model.addAttribute("user", tk);
         String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
        if (thongTinTaiKhoan != null) {
            model.addAttribute("ThongTinTK", thongTinTaiKhoan);
        }
        return "User/User-cart";
    }
}
