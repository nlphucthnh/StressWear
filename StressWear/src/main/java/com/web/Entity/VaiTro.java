package com.web.Entity;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "vai_tro")
public class VaiTro {
    @Id
    @Column(name = "id_vai_tro")
    private String idVaiTro;

    @Column(name = "ten_vai_tro")
    private String tenVaiTro;

    @OneToMany(mappedBy = "vaiTro")
    List<VaiTroTaiKhoan> List_VTTK;
}
