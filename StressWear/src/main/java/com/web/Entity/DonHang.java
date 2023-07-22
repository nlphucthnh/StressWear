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
import jakarta.persistence.OneToOne;
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
@Table(name = "don_hang")
public class DonHang implements Serializable {
    @Id
    @Column(name = "id_don_hang")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDonHang;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_tao")
    private Date ngayTao = new Date();

    @Column(name = "phuong_thuc_thanh_toan")
    private String phuongThucThanhToan;

    @Column(name = "trang_thai_don_Hang")
    private String trangThaiDonHang;

    @ManyToOne
	@JoinColumn(name = "tai_khoan_mua")
	TaiKhoan taiKhoanMuaHang;
   
    @OneToOne
    @JoinColumn(name = "id_thong_tin_giao_hang")
    ThongTinGiaoHang thongTinGiaoHang;

    @JsonIgnore
    @OneToOne(mappedBy = "donHangTT")
    ThanhToan thanhToanDH;

    @JsonIgnore
    @OneToMany(mappedBy = "donHangDHCT")
    List<DonHangChiTiet> ListDHCT;

}
