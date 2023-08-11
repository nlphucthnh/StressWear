package com.web.Controller.User_Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.DAO.TaiKhoanDAO;
import com.web.DAO.ThongTinTaiKhoanDAO;
import com.web.Entity.ThongTinTaiKhoan;

@Controller
@RequestMapping("/user")
public class UrController {

     @Autowired
    HttpSession session;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ThongTinTaiKhoanDAO thongTinTaiKhoanDAO;

    @Autowired
    TaiKhoanDAO taiKhoanDAO;

    @GetMapping("about")
    public String about(Model model) {
        String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
        if (thongTinTaiKhoan != null) {
            model.addAttribute("ThongTinTK", thongTinTaiKhoan);
        }
        return "User/User-about";
    }

    @GetMapping("contact")
    public String contact(Model model) {
        String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
        if (thongTinTaiKhoan != null) {
            model.addAttribute("ThongTinTK", thongTinTaiKhoan);
        }
        return "User/User-contact";
    }

    @GetMapping("changetab")
    public String changetab() {
        return "User/User_changeTab";
    }

}
