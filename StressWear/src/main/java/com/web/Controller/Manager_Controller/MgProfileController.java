package com.web.Controller.Manager_Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.DAO.TaiKhoanDAO;
import com.web.DAO.ThongTinTaiKhoanDAO;
import com.web.Entity.ThongTinTaiKhoan;

@Controller
@RequestMapping("/manager")
public class MgProfileController {
    @Autowired
    HttpSession session;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ThongTinTaiKhoanDAO thongTinTaiKhoanDAO;

    @Autowired
    TaiKhoanDAO taiKhoanDAO;

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

    @PostMapping("profile/update")
    public String updateInfor(@ModelAttribute("ThongTinTK") ThongTinTaiKhoan thongTinTaiKhoan) {
        if (thongTinTaiKhoan != null) {
            String tenDangNhap = (String) session.getAttribute("tenDangNhapLogin");
            ThongTinTaiKhoan thongTinTaiKhoan2 = thongTinTaiKhoanDAO.findBytaiKhoanTTTK(tenDangNhap);
            thongTinTaiKhoan.setIdThongTinTaiKhoan(thongTinTaiKhoan2.getIdThongTinTaiKhoan());
            thongTinTaiKhoan.setTaiKhoanTTTK(thongTinTaiKhoan2.getTaiKhoanTTTK());
            thongTinTaiKhoanDAO.save(thongTinTaiKhoan);
        }
        return "redirect:/manager/profile";
    }
}
