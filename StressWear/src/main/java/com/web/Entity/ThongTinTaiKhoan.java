package com.web.Entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "thong_tin_tai_khoan")
public class ThongTinTaiKhoan implements Serializable {

    @Id
    @Column(name = "id_thong_tin_tai_khoan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idThongTinTaiKhoan;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "ngay_sinh")
	private Date ngaySinh = new Date();

    @Column(name = "anh_dai_dien")
    private String anhDaiDien;

    @Column(name = "anh_nen")
    private String anhNen;


    // Relationship 1-1
    @OneToOne
    @JoinColumn(name = "ten_dang_nhap")
    TaiKhoan taiKhoanTTTK;


}
