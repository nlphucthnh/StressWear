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
@Table(name = "kich_thuoc")
public class KichThuoc implements Serializable {
    @Id
    @Column(name = "id_kich_thuoc")
    private String idKichThuoc;

    @Column(name = "ten_kich_thuoc")
    private String tenKichThuoc;

    @JsonIgnore
    @OneToMany(mappedBy = "kichThuoc")
    List<DanhSachKT> ListDSKT;
}
