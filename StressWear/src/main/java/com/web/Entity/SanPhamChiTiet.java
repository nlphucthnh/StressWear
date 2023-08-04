package com.web.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_tao")
    private Date ngayTao = new Date();

    @JsonIgnore
    @OneToMany(mappedBy = "sanPhamChiTiet")
    List<DanhSachHA> ListDSHA_SPCT;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPhamChiTiet")
    List<DanhSachKT> ListDSKT_SPCT;
}
