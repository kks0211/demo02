package com.board.test;

import com.board.config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
@ContextConfiguration(classes = {RootConfig.class})
public class DataSourceTest {

    @Inject
    private DataSource ds;

    @Test
    public void testConnection() throws Exception {

        try (Connection con = ds.getConnection()) {
            System.out.println("con =====> " + con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
