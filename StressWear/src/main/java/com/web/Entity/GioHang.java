package com.web.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gio_hang")
public class GioHang implements Serializable  {
    @Id
    @Column(name = "id_gio_hang")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGioHang;

    @Column(name = "so_luong")   
    private int soLuong;

    @Column(name = "phi_van_chuyen")
    private double phiVanChuyen;

    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    SanPham sanPhamGH;
    
    @OneToOne
    @JoinColumn(name = "ten_dang_nhap")
    TaiKhoan taiKhoanGH;

}
