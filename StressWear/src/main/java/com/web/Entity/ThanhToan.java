package com.web.Entity;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ThanhToan implements Serializable {
    @Id
    @Column(name = "id_thanh_toan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idThanhToan;

    @Column(name = "trang_thai_thanh_toan")
    private String trangThaiThanhToan;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan = new Date();

    @Column(name = "tong_tien")
    private double tongTien;

    @Column(name = "phi_van_chuyen")
    private double phiVanChuyen;

    @OneToOne
    @JoinColumn(name = "id_don_hang")
    DonHang donHangTT;

    @ManyToOne()
    @JoinColumn(name = "id_phuong_thuc")
    PhuongThucThanhToan phuongThucThanhToan;

    @JsonIgnore
    @OneToOne(mappedBy = "thanhToanVDT")
    ViDienTu viDienTu;
}
