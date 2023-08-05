package com.web.Controller.User_Controller;

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
public class UrOrderController {
    @Autowired
    TaiKhoanDAO tkdao;

    @Autowired
    ThongTinGiaoHangDAO ttghdao;

    @Autowired
    PhuongThucThanhToanDAO pThucThanhToanDAO;

    @GetMapping("/User/address")
    public String order(Model model) {
        List<TaiKhoan> tk = tkdao.findByTenDangNhap("thienlc");
        model.addAttribute("user", tk);

        return "User/User-adreess";
    }

    @GetMapping("/User/pay")
    public String pay(Model model) {
        List<TaiKhoan> tk = tkdao.findByTenDangNhap("thienlc");
        List<PhuongThucThanhToan> ptthanhtoan = pThucThanhToanDAO.findAll();
        model.addAttribute("phuongthucthanhtoan", ptthanhtoan);
        model.addAttribute("user", tk);
        return "User/User-pay";
    }

    @PostMapping("/User/address")
    public String address(Model model, @RequestParam("fullname") String fullname, @RequestParam("phone") String phone,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "district", required = false) String district,
            @RequestParam(name = "commune", required = false) String commune,
            @RequestParam(name = "apartment_number", required = false) String apartment_number) {

        String diaChi = district + ", " + commune + ", " + apartment_number;
        ThongTinGiaoHang thongtingiaohang = new ThongTinGiaoHang();
        thongtingiaohang.setTenNguoiMua(fullname);
        thongtingiaohang.setSoDienThoai(phone);
        thongtingiaohang.setDiaChi(city);
        thongtingiaohang.setDiaChiChiTiet(diaChi);
        // ttghdao.save(thongtingiaohang);
        model.addAttribute("message", "Lưu thông tin giao hàng thành công");
        return "User/User-pay";
    }

    @PostMapping("/User/pay")
    public String address(Model model) {
        model.addAttribute("message", "Lưu thông tin giao hàng thành công");
        return "User/User-orderdetail";
    }
}
