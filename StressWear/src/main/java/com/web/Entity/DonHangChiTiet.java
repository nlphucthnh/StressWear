package com.web.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
