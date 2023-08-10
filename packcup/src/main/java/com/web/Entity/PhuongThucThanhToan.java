package com.web.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phuong_thuc_thanh_toan")
public class PhuongThucThanhToan implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phuong_thuc")
    private int idPhuongThuc;

    @Column(name = "ten_phuong_thuc")
    private String tenPhuongThuc;

    @Column(name = "mo_ta_phuong_thuc")
    private String moTaPhuongThuc;

    @JsonIgnore
    @OneToMany(mappedBy = "phuongThucThanhToan")
    List<ThanhToan> ListTT_PTTT;
}
