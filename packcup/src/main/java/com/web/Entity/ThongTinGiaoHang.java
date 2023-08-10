package com.web.Entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
