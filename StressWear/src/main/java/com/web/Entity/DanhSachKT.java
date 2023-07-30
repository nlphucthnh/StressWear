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
@IdClass(DanhSachKTKey.class)
@Table(name = "danh_sach_kt")
public class DanhSachKT {
    @Id
    @Column(name = "id_kich_thuoc")
    private int idKichThuoc;
    @Id
    @Column(name = "id_san_pham_chi_tiet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSanPhamChiTiet;

    @ManyToOne
    @JoinColumn(name = "id_kich_thuoc", insertable = false, updatable = false)
    private KichThuoc kichThuoc;

    @ManyToOne
    @JoinColumn(name = "id_san_pham_chi_tiet", insertable = false, updatable = false)
    private SanPhamChiTiet sanPhamChiTiet;

}
