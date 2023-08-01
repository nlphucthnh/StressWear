package com.web.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vai_tro_tai_khoan")
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
