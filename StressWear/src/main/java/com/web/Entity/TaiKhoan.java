package com.web.Entity;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tai_khoan")
public class TaiKhoan implements Serializable {
    @Id
    @Column(name = "ten_dang_nhap")
    private String tenDangNhap;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "email")
    private String email;

    @Column(name = "trang_thai")
    private boolean trangThai;

    @Column(name = "vai_tro")
    private String vaiTro;

    // Relationship 1-1
    @JsonIgnore
    @OneToOne(mappedBy = "taiKhoanTTTK")
    ThongTinTaiKhoan thongTinTaiKhoan;

    @JsonIgnore
    @OneToMany(mappedBy = "taiKhoanMuaHang")
    List<DonHang> ListDonHang;

    @JsonIgnore
    @OneToOne(mappedBy = "taiKhoanGH")
    GioHang gioHangTK;

    @OneToMany(mappedBy = "taiKhoan")
    List<VaiTroTaiKhoan> List_VTTK;


}
