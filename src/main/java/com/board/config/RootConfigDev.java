package com.board.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
@ComponentScan(basePackages = {"com.board.service", "com.board.dao"})
@MapperScan(basePackages = {"com.board.mapper"})
public class RootConfigDev {

    /*@Bean
    public DataSource datasourceDev() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        //hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://127.0.0.1:3306/book?serverTimezone=UTC&useSSL=false");
        hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/book?serverTimezone=UTC&useSSL=false");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }*/

    @Bean
    public DataSource datasourceDev() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.h2.Driver");
        //hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        //hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://127.0.0.1:3306/book?serverTimezone=UTC&useSSL=false");
        hikariConfig.setJdbcUrl("jdbc:h2:mem://localhost/~/book");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasourceDev());
        return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
    }
}
