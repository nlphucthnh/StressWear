package com.web.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "don_hang")
@JsonIgnoreProperties(ignoreUnknown = true)
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
