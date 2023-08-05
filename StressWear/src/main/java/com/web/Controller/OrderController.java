package com.web.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.DAO.PhuongThucThanhToanDAO;
import com.web.DAO.TaiKhoanDAO;
import com.web.DAO.ThongTinGiaoHangDAO;
import com.web.Entity.PhuongThucThanhToan;
import com.web.Entity.TaiKhoan;
import com.web.Entity.ThongTinGiaoHang;

@Controller
public class OrderController {

    @GetMapping("/order/checkout")
    public String checkout() {
    return "User/User-adreess";
    }

    @GetMapping("/order/list")
    public String list(){
    return "order/list";
    }

    @GetMapping("/order/detail/{id}")
    public String detail(){
    return "order/detail";
    }







    // @GetMapping("/home/index")
    // public String demo(Model model) {
    //     model.addAttribute("message","This is home");
    //     return "User/demo";
    // }

    // @GetMapping("/home/about")
    // public String demo1(Model model) {
    //     model.addAttribute("message","This is introduction page");
    //     return "User/demo";
    // }


}
