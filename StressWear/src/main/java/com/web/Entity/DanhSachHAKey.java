package com.web.Entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanhSachHAKey implements Serializable {
    private String idHinhAnh;
    private Integer idSanPhamChiTiet;
    
}
