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
    private String hoTen = "Unknown"; 
    
    @Column(name = "gioi_tinh")
    private boolean gioiTinh = true;

    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "ngay_sinh")
	private Date ngaySinh = new Date();

    @Column(name = "anh_dai_dien")
    private String anhDaiDien = "https://firebasestorage.googleapis.com/v0/b/ztress-zear.appspot.com/o/img_project%2FNull.png?alt=media&token=d26ce611-dce6-49a3-ba46-9f92cbc1a70d";

    @Column(name = "anh_nen")
    private String anhNen = "https://firebasestorage.googleapis.com/v0/b/ztress-zear.appspot.com/o/img_project%2Fstock-photo-1073420532.jpg?alt=media&token=f90867be-f6ed-432a-b000-2ee5b4080423";


    // Relationship 1-1
    @OneToOne
    @JoinColumn(name = "ten_dang_nhap")
    TaiKhoan taiKhoanTTTK;
}
