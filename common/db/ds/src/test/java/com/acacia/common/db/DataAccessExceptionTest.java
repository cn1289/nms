package com.acacia.common.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.junit.Test;

public class DataAccessExceptionTest {

    @Test
    public void testDataAccessException() {
        DataAccessException ex = new DataAccessException();
        assertNotNull(ex);
        ex = new DataAccessException("msg");
        assertEquals(ex.getMessage(), "msg");
        ex = new DataAccessException(new Throwable());
        assertNotNull(ex.getCause());
        ex = new DataAccessException("msg", new Throwable());
        assertEquals(ex.getMessage(), "msg");
        assertNotNull(ex.getCause());
    }

    @Test
    public void testIData() {
        final DummyData dd = new DummyData();
        assertTrue(dd instanceof Serializable);
    }

    // test data.
    class DummyData implements IData {

        private static final long serialVersionUID = 6389116075944160431L;

    }

}
