package com.web.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
