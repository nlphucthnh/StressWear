package com.web.Controller.User_Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.DAO.NhomLoaiDAO;
import com.web.DAO.SanPhamDAO;
import com.web.DAO.TaiKhoanDAO;
import com.web.DAO.ThongTinTaiKhoanDAO;
import com.web.Entity.NhomLoai;
import com.web.Entity.ThongTinTaiKhoan;

@Controller
public class UrHomeController {
    @Autowired
    SanPhamDAO dao;

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

    @GetMapping("/")
    public String index(Model model) {
        var products = dao.findAll();
        List<NhomLoai> nhomloai = nlDao.findAll();
        model.addAttribute("nhomloai", nhomloai);
        model.addAttribute("products", products);

        String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        if (tenDangNhap != null) {
            ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
            if (thongTinTaiKhoan != null) {
                model.addAttribute("ThongTinTK", thongTinTaiKhoan);
            }else {
                ThongTinTaiKhoan thongTinTaiKhoan2 = new ThongTinTaiKhoan();
                thongTinTaiKhoan2.setTaiKhoanTTTK(taiKhoanDAO.findById(tenDangNhap).get());
                thongTinTaiKhoanDAO.save(thongTinTaiKhoan2);
                model.addAttribute("ThongTinTK", thongTinTaiKhoan2);
            }
        }
        return "User/User-index";
    }
}
