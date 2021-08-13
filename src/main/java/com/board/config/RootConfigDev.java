package com.board.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.board.service"})
@MapperScan(basePackages = {"com.board.mapper"})
public class RootConfigDev {

    /*
    @Bean
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
    }
    */

    @Bean
    public DataSource datasourceDev() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.h2.Driver");
        hikariConfig.setJdbcUrl("jdbc:h2:mem://localhost/~/test;Mode=Oracle");
        //hikariConfig.setJdbcUrl("jdbc:h2:tcp://localhost/~/test;Mode=Oracle");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

    @Bean
    public DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("/sql/data.sql").build();
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(datasourceDev());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasourceDev());
        sqlSessionFactoryBean.setDataSource(h2DataSource());

        return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
    }

}