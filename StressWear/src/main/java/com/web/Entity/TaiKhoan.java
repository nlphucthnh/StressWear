package com.web.Entity;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
// @AllArgsConstructor
// @NoArgsConstructor
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

    @JsonIgnore
    @OneToMany(mappedBy = "taiKhoan", fetch = FetchType.EAGER)
    List<VaiTroTaiKhoan> List_VTTK;


}
