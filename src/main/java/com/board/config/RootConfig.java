package com.board.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.board.service"})
@MapperScan(basePackages = {"com.board.mapper"})
@PropertySource(value = {"classpath:/config/${spring.profiles.active}.properties"})
public class RootConfig {

    private final ApplicationContext context;

    @Value("${DriverClassName}")
    String className;
    @Value("${JdbcUrl}")
    String jdbcUrl;
    @Value("${UserId}")
    String userId;
    @Value("${Password}")
    String pw;
    @Value("${spring.profiles.active}")
    String profile;

    /*@Bean
    public DataSource datasource() {
        HikariConfig hikariConfig = new HikariConfig();
        //hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
        //hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
        hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:XE");
        hikariConfig.setUsername("book_ex");
        hikariConfig.setPassword("BOOK_EX");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }*/

    @Bean
    public DataSource datasource() {

        HikariConfig hikariConfig = new HikariConfig();
        //hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
        //hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
        hikariConfig.setDriverClassName(className);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(userId);
        hikariConfig.setPassword(pw);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

    /*@Bean
    public DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("/sql/data.sql").build();
    }*/


    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(datasource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource());
        sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath:/mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.board.domain");
        return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
    }

}
