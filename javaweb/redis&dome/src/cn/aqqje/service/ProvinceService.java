package cn.aqqje.service;

import cn.aqqje.domain.Province;

import java.util.List;

public interface ProvinceService {
    List<Province> findAll();

    String findAllJson();
}
