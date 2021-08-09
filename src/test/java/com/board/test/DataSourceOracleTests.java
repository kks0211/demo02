package com.board.test;


import com.board.config.RootConfig;
import com.board.config.RootConfigDev;
import com.board.config.RootConfigReal;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.internal.Classes;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
//@ContextConfiguration(classes = {RootConfig.class})
@ContextConfiguration(classes = {RootConfigDev.class, RootConfigReal.class})
@ActiveProfiles("dev")
public class DataSourceOracleTests {

    @Setter(onMethod_ = {@Autowired})
    private DataSource dataSource;

    @Setter(onMethod_ = {@Autowired})
    private SqlSessionFactory sqlSessionFactory;

    static final Logger log = LoggerFactory.getLogger(DataSourceOracleTests.class);

    @Test
    public void testConnection() {
        try (Connection con = dataSource.getConnection()){
            log.info("con : {}",con);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testMabatis() {
        try (SqlSession session = sqlSessionFactory.openSession();
            Connection con = session.getConnection();
        ) {
            log.info("session : {}", session);
            log.info("con : {}",con);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
