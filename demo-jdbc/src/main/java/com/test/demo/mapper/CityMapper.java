package com.test.demo.mapper;

import com.test.demo.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description
 * <p>
 * DATE 2019/5/15.
 *
 * @author wangliangliang.
 */
public interface CityMapper extends JpaRepository<City,Long>,CityRepositoryCustom {

}
