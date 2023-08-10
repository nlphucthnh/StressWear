package com.web.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "danh_sach_kt")
public class DanhSachKT implements Serializable {
    @Id
    @Column(name = "id_danh_sach_kt")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDanhSachKT;

    @ManyToOne
    @JoinColumn(name = "id_kich_thuoc", insertable = false, updatable = false)
    private KichThuoc kichThuoc;

    @ManyToOne
    @JoinColumn(name = "id_san_pham_chi_tiet", insertable = false, updatable = false)
    private SanPhamChiTiet sanPhamChiTiet;

}
