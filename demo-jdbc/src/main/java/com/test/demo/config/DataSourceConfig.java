package com.test.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Description
 * <p>
 * DATE 2019/5/15.
 *
 * @author wangliangliang.
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource(){
        HikariDataSource hikariCP = new HikariDataSource();
        hikariCP.setJdbcUrl(jdbcUrl);
        hikariCP.setUsername(userName);
        hikariCP.setPassword(password);
        return hikariCP;
    }
}
