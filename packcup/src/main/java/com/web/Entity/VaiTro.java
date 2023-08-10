package com.web.Entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
@SuppressWarnings("serial")

@Data
@Entity
@Table(name = "vai_tro")
public class VaiTro {
    @Id
    @Column(name = "id_vai_tro")
    private String idVaiTro;

    @Column(name = "ten_vai_tro")
    private String tenVaiTro;
    
    @JsonIgnore
    @OneToMany(mappedBy = "vaiTro")
    List<VaiTroTaiKhoan> List_VTTK;
}
