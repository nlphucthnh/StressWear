package com.web.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "vai_tro_tai_khoan", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"ten_dang_nhap", "id_vai_tro"})
})
public class VaiTroTaiKhoan {
    @Id
    @Column(name = "id_vai_tro_tai_khoan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVaiTroTaiKhoan;

    @ManyToOne
    @JoinColumn(name = "id_vai_tro")
    VaiTro vaiTro;

    @ManyToOne
    @JoinColumn(name = "ten_dang_nhap")
    TaiKhoan taiKhoan;
}
