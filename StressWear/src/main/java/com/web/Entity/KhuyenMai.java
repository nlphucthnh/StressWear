package com.web.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "khuyen_mai")
public class KhuyenMai implements Serializable  {
    @Id
    @Column(name = "id_khuyen_mai")
    private String idKhuyenMai;

    @Column(name = "ten_khuyen_Mai")
    private String tenKhuyenMai;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau = new Date();

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc = new Date();

    @Column(name = "pham_tram_khuyen_mai")
    private double phanTramKhuyenMai;

    @JsonIgnore
    @OneToMany(mappedBy = "khuyenMaiSP")
    List<SanPham> ListSP_KM;
}
