package com.web.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "hinh_anh")
public class HinhAnh implements Serializable {
    @Id
    @Column(name = "id_hinh_anh")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idHinhAnh;

    @Column(name = "ten_hinh_anh")
    private String tenHinhAnh;

    @Column(name = "duong_dan")
    private String duongDan;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_dang")
    private Date ngayDang = new Date();

    @Column(name = "dung_luong_anh")
    private double dungLuongAnh;

    @JsonIgnore
    @OneToMany(mappedBy = "hinhAnh")
    List<DanhSachHA> ListDSHA;

}
