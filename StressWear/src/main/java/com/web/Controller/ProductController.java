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
 

}
