package com.web.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kich_thuoc")
public class KichThuoc implements Serializable {
    @Id
    @Column(name = "id_kich_thuoc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idKichThuoc;

    @Column(name = "ten_kich_thuoc")
    private char tenKichThuoc;

    @JsonIgnore
    @OneToMany(mappedBy = "kichThuoc")
    List<DanhSachKT> ListDSKT;
}
