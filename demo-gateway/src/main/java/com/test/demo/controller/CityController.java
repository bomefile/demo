package com.test.demo.controller;

import com.test.demo.domain.City;
import com.test.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description
 * <p>
 * DATE 2019/5/15.
 *
 * @author wangliangliang.
 */

@RestController
@RequestMapping("/")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("getAllCity")
    public List<City> getAllCity(){
        return cityService.findAll();
    }

    @RequestMapping("getById")
    public City getAllCity(Long id){
        return cityService.getCityById(id);
    }
}
