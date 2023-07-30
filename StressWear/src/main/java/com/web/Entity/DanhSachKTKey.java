package com.web.Entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanhSachKTKey implements Serializable{
    private int idKichThuoc;
    private int idSanPhamChiTiet;
}
