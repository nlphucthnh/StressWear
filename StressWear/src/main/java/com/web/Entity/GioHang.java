package com.web.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
