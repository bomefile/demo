package com.test.demo.service.impl;

import com.test.demo.domain.City;
import com.test.demo.mapper.CityMapper;
import com.test.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 * <p>
 * DATE 2019/5/15.
 *
 * @author wangliangliang.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;
    @Override
    public List<City> findAll() {
        return cityMapper.findAll();
    }

    @Override
    public Long count() {
        return null;
    }
}
