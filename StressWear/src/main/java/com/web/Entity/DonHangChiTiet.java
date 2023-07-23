package com.web.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "don_hang_chi_tiet")
public class DonHangChiTiet implements Serializable  {
    @Id
    @Column(name = "id_don_hang_chi_tiet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDonHangChiTiet;

    @Column(name = "so_luong")
    private int soLuong;

    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    SanPham sanPhamDHCT;

    @ManyToOne
    @JoinColumn(name = "id_don_hang")
    DonHang donHangDHCT;
}
