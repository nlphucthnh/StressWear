package com.web.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")

@Data
// @AllArgsConstructor
// @NoArgsConstructor
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
