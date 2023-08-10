package com.web.Controller.User_Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.DAO.NhomLoaiDAO;
import com.web.DAO.PhuongThucThanhToanDAO;
import com.web.DAO.TaiKhoanDAO;
import com.web.DAO.ThongTinGiaoHangDAO;
import com.web.Entity.NhomLoai;
import com.web.Entity.PhuongThucThanhToan;
import com.web.Entity.TaiKhoan;
import com.web.Entity.ThongTinGiaoHang;
import com.web.service.DonHangService;

@Controller
public class UrOrderController {
    @Autowired
    TaiKhoanDAO tkdao;

    @Autowired
    ThongTinGiaoHangDAO ttghdao;

    @Autowired
    PhuongThucThanhToanDAO pThucThanhToanDAO;

    @Autowired
    DonHangService donHangService;

    @Autowired
    NhomLoaiDAO nlDao;

    @GetMapping("/User/address")
    public String order(Model model) {
        List<NhomLoai> nhomloai = nlDao.findAll();
        model.addAttribute("nhomloai", nhomloai);
        return "User/User-adreess";
    }

    @GetMapping("/User/pay")
    public String pay(Model model) {
        List<NhomLoai> nhomloai = nlDao.findAll();
        model.addAttribute("nhomloai", nhomloai);
        List<PhuongThucThanhToan> ptthanhtoan = pThucThanhToanDAO.findAll();
        model.addAttribute("phuongthucthanhtoan", ptthanhtoan);
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
        ttghdao.save(thongtingiaohang);
        model.addAttribute("message", "Lưu thông tin giao hàng thành công");
        return "User/User-adreess";
    }

    @PostMapping("/User/pay")
    public String address(Model model) {
        model.addAttribute("message", "Lưu thông tin giao hàng thành công");
        return "User/User-orderdetail";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) {

        List<NhomLoai> nhomloai = nlDao.findAll();
        model.addAttribute("nhomloai", nhomloai);
        model.addAttribute("donhang", donHangService.findById(id));
        return "User/User-orderdetail";
    }

}
