package com.test.demo.service;

import com.test.demo.domain.City;

import java.util.List;

/**
 * Description
 * <p>
 * DATE 2019/5/15.
 *
 * @author wangliangliang.
 */
public interface CityService {

    List<City> findAll();

    City getCityById(Long id);
}
