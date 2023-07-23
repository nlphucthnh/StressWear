package com.web.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(DanhSachHAKey.class)
@Table(name = "danh_sach_ha")
public class DanhSachHA {
    @Id
    @Column(name = "id_hinh_anh")
    private String idHinhAnh;
    @Id
    @Column(name = "id_san_pham_chi_tiet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSanPhamChiTiet;

    @ManyToOne
    @JoinColumn(name = "id_hinh_anh", insertable = false, updatable = false)
    private HinhAnh hinhAnh;

    @ManyToOne
    @JoinColumn(name = "id_san_pham_chi_tiet", insertable = false, updatable = false)
    private SanPhamChiTiet sanPhamChiTiet;

    // other fields, constructors, getters and setters
}
