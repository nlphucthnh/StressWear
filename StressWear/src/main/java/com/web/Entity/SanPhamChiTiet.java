package com.web.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "san_pham_chi_tiet")
public class SanPhamChiTiet implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_san_pham_chi_tiet")
    private int idSanPhamChiTiet;

    @Column(name = "ten_mau")
    private String tenMau;

    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    SanPham sanPhamSPCT;
    
    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    MauSac mauSacSPCT;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPhamChiTiet")
    List<DanhSachHA> ListDSHA_SPCT;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPhamChiTiet")
    List<DanhSachKT> ListDSKT_SPCT;
}
