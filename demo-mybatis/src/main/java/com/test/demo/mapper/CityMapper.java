package com.test.demo.mapper;

import com.test.demo.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description
 * <p>
 * DATE 2019/5/15.
 *
 * @author wangliangliang.
 */

@Mapper
public interface CityMapper {

    @Select("select id, name, map, state, country from city")
    List<City> findAll();

    @Select("select id, name, map, state, country from city where id = #{id}")
    City findById(@Param("id") Long id);
}
