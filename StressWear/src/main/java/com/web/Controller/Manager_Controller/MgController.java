package com.web.Controller.Manager_Controller;

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
@RequestMapping("/manager")
public class MgController {

    @Autowired
    HttpSession session;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ThongTinTaiKhoanDAO thongTinTaiKhoanDAO;

    @Autowired
    TaiKhoanDAO taiKhoanDAO;

    @GetMapping("filter")
    public String getFilterPage(Model model ) {
        String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
        if (thongTinTaiKhoan != null) {
            model.addAttribute("ThongTinTK", thongTinTaiKhoan);
        }
        return "Manager/Manager-filter-page";
    }

    @GetMapping("login")
    public String getLoginPage(Model model ) {
        String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
        if (thongTinTaiKhoan != null) {
            model.addAttribute("ThongTinTK", thongTinTaiKhoan);
        }
        return "Manager/Manager-login-page";
    }

    @GetMapping("order")
    public String getOrderPage(Model model ) {
        String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
        if (thongTinTaiKhoan != null) {
            model.addAttribute("ThongTinTK", thongTinTaiKhoan);
        }
        return "Manager/Manager-order-page";
    }

    @GetMapping("product")
    public String getProductPage(Model model ) {
        String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
        if (thongTinTaiKhoan != null) {
            model.addAttribute("ThongTinTK", thongTinTaiKhoan);
        }
        return "Manager/Manager-product-page";
    }

    @GetMapping("profile")
    public String getProfliePage(Model model) {
        String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
        if (thongTinTaiKhoan != null) {
            model.addAttribute("ThongTinTK", thongTinTaiKhoan);
        } else {
            ThongTinTaiKhoan thongTinTaiKhoan2 = new ThongTinTaiKhoan();
            thongTinTaiKhoan2.setTaiKhoanTTTK(taiKhoanDAO.findById(tenDangNhap).get());
            thongTinTaiKhoanDAO.save(thongTinTaiKhoan2);
            model.addAttribute("ThongTinTK", thongTinTaiKhoan2);
        }
        return "Manager/Manager-profile-page";
    }

    @GetMapping("revenue")
    public String getRevenuePage(Model model) {
        String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
        if (thongTinTaiKhoan != null) {
            model.addAttribute("ThongTinTK", thongTinTaiKhoan);
        }
        return "Manager/Manager-revenue-page";
    }

    @GetMapping("account")
    public String getAccountPage(Model model) {
        String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
        ThongTinTaiKhoan thongTinTaiKhoan = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
        if (thongTinTaiKhoan != null) {
            model.addAttribute("ThongTinTK", thongTinTaiKhoan);
        }
        return "Manager/Manager-account-page";
    }

}
