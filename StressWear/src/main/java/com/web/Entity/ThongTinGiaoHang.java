package com.web.Entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "thong_tin_giao_hang")
public class ThongTinGiaoHang implements Serializable{
    @Id
    @Column(name = "id_thong_tin_giao_hang")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idThongTinGiaoHang;

    @Column(name = "ten_nguoi_mua")
    private String tenNguoiMua;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "dia_chi_chi_tiet")
    private String diaChiChiTiet;

    @JsonIgnore
    @OneToOne(mappedBy = "thongTinGiaoHang")
    DonHang donHangTTGH;

}
