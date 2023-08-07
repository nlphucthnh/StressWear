package com.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.web.Entity.DonHang;

public interface DonHangService {

    DonHang create(JsonNode donhangData);

    DonHang findById(int id);
}
