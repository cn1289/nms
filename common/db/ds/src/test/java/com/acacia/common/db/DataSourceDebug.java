package com.acacia.common.db;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-datasource.xml" })
public class DataSourceDebug {
    
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ComboPooledDataSource ds;

    @Test
    public void testDsProperties() {
        assertNotNull(ds);
        log.info("ctimeout:" + ds.getCheckoutTimeout());
        log.info("utimeout:" + ds.getUnreturnedConnectionTimeout());
        try {
            log.info("longin timeout:" + ds.getLoginTimeout());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

}
