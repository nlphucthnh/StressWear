package com.web.Entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
