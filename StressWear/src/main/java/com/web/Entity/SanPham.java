package com.web.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class SanPham implements Serializable {
    @Id
    @Column(name = "id_san_pham")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSanPham;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "gia_san_pham")
    private double giaSanPham;
    
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "ngay_tao_san_pham")
	private Date ngayTaoSanPham = new Date();


    @Column(name = "mo_ta_san_pham")
    private String moTaSanPham;

    @Column(name = "so_luong")
    private int soLuongSP = 0;

    @Column(name = "trang_thai_ton_kho")
    private boolean trangThaiTonKho = (soLuongSP > 0) ? true : false;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPhamGH")
    List<GioHang> ListGH;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPhamDHCT")
    List<DonHangChiTiet> ListDHCT;

    @ManyToOne
    @JoinColumn(name = "id_nhom_loai")
    NhomLoai nhomLoaiSP;

    @ManyToOne
    @JoinColumn(name = "id_khuyen_mai")
    KhuyenMai khuyenMaiSP;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPhamSPCT")
    List<SanPhamChiTiet> ListSPCT_SP;
}
