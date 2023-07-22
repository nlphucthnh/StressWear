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
@Table(name = "hinh_anh")
public class HinhAnh implements Serializable {
    @Id
    @Column(name = "id_hinh_anh")
    private String idHinhAnh;

    @Column(name = "ten_hinh_anh")
    private String tenHinhAnh;

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
