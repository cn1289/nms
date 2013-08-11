package com.acacia.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acacia.common.db.DataAccessException;
import com.acacia.common.db.IData;

@RunWith(PowerMockRunner.class)
public class AbstractDaoTest {
    
    private Logger log = LoggerFactory.getLogger(getClass());

    DummyDao dao = new DummyDao();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;

    class DummyDao extends AbstractDao<DummyData> {

        private Logger slog = LoggerFactory.getLogger(getClass());

        @Override
        protected Logger getLog() {
            return slog;
        }

        @Override
        protected Class<DummyData> getDataClazz() {
            return DummyData.class;
        }

    }

    class DummyData implements IData {
        private static final long serialVersionUID = 1L;
    }

    @Before
    public void init() {
        sessionFactory = mock(SessionFactory.class);
        dao.setSessionFactory(sessionFactory);
        
        session = mock(Session.class);
        transaction = mock(Transaction.class);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);
    }
    
    @Test
    public void testAdd() {
        DummyData data = new DummyData();
        try {
            dao.add(data);
            assertTrue(true);
        } catch (DataAccessException e) {
           log.error("",e);
        }
        
        try {
            dao.add(null);
        } catch (DataAccessException e) {
           assertEquals("不能存贮空对象！", e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        try {
            dao.delete(null);
        } catch (DataAccessException e) {
            assertEquals("不能删除空对象！", e.getMessage());
        }
        
        DummyData data = new DummyData();
        try {
            dao.delete(data);
            assertTrue(true);
        } catch (DataAccessException e) {
           log.error("",e);
        }
        
    }

    @Test
    public void testUpdate() {
        try {
            dao.update(null);
        } catch (DataAccessException e) {
            assertEquals("不能更新空对象！", e.getMessage());
        }
        
        DummyData data = new DummyData();
        try {
            dao.update(data);
            assertTrue(true);
        } catch (DataAccessException e) {
           log.error("",e);
        }
    }

}
