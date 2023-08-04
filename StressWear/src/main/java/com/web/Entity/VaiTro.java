package com.web.Entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")

@Data
// @AllArgsConstructor
// @NoArgsConstructor
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
