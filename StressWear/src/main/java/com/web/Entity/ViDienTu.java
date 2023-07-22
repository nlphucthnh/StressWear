package com.web.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vi_dien_tu")
public class ViDienTu implements Serializable{
    @Id
    @Column(name = "id_vi_dien_tu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idViDienTu;

    @Column(name = "ten_vi_dien_tu")
    private String tenViDienTu;

    @Column(name = "so_dien_thoai_vi")
    private String soDienThoaiVi;

    @Column(name = "ma_giao_dich")
    private String maGiaoDich;

    @OneToOne
    @JoinColumn(name = "id_thanh_toan")
    ThanhToan thanhToanVDT;
}
