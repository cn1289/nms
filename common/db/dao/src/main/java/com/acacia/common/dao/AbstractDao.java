package com.acacia.common.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.acacia.common.db.DataAccessException;
import com.acacia.common.db.IData;

/**
 * AbstractDao, 抽象DAO.
 * @author zhonglizhi.
 * @param <T>
 *        subclass of IAbstractData.
 */
public abstract class AbstractDao<T extends IData> {

    private SessionFactory sessionFactory;

    /**
     * Constructor.
     */
    public AbstractDao() {
    }

    protected abstract Logger getLog();

    protected abstract Class<T> getDataClazz();

    /**
     * 增加对象.
     * @param data
     *        <T>
     */
    public void add(final T data) throws DataAccessException {
        Session session = null;
        try {
            if (data != null) {
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(data);
                session.getTransaction().commit();
                if (getLog() != null && getLog().isDebugEnabled()) {
                    getLog().debug(
                            "add data: "
                                    + (data == null ? "null" : data.toString()));
                }
            } else {
                throw new DataAccessException("不能存贮空对象！");
            }
        } catch (final HibernateException e) {
            throw new DataAccessException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 增加或更新对象.
     * @param data
     *        <T>
     */
    public void saveOrUpdate(final T data) throws DataAccessException {
        Session session = null;
        try {
            if (data != null) {
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.saveOrUpdate(data);
                session.getTransaction().commit();
                if (getLog() != null && getLog().isDebugEnabled()) {
                    getLog().debug(
                            "add data: "
                                    + (data == null ? "null" : data.toString()));
                }
            } else {
                throw new DataAccessException("不能存贮空对象！");
            }
        } catch (final HibernateException e) {
            throw new DataAccessException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    /**
     * 删除对象.
     * @param data
     *        <T>
     */
    public void delete(final T data) throws DataAccessException {
        Session session = null;
        try {
            if (data != null) {
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.delete(data);
                session.getTransaction().commit();
            } else {
                throw new DataAccessException("不能删除空对象！");
            }
        } catch (final HibernateException e) {
            throw new DataAccessException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    /**
     * 根据ID删除对象
     * @param id
     * @throws DataAccessException
     */
    public void deleteById(final Object id)
            throws DataAccessException {
        final T data = getById(id);
        if (data != null) {
            delete(data);
        }
    }
    /**
     * 更新对象.
     * @param data
     *        <T>
     */
    public void update(final T data) throws DataAccessException {
        Session session = null;
        try {
            if (data != null) {
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.update(data);
                session.getTransaction().commit();
                if (getLog() != null && getLog().isDebugEnabled()) {
                    getLog().debug(
                            "update data: "
                                    + (data == null ? "null" : data.toString()));
                }
            } else {
                throw new DataAccessException("不能更新空对象！");
            }
        } catch (final HibernateException e) {
            throw new DataAccessException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 通过ID获取对象.
     * @param id
     *        Object
     * @return <T>
     */
    public T getById(final Object id) throws DataAccessException {
        Session session = null;
        try {
            if (getDataClazz() == null) {
                return null;
            } else {
                session = sessionFactory.openSession();
                final Criteria criteria = session
                        .createCriteria(getDataClazz()).add(
                                Restrictions.idEq(id));
                @SuppressWarnings("unchecked")
                final T data = (T) criteria.uniqueResult();
                return data;
            }
        } catch (final HibernateException e) {
            throw new DataAccessException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 获取对象数量.
     * @return int.
     */
    public int count() throws DataAccessException {
        return count(null);
    }

    /**
     * 获取对象数量.
     * @param criterions
     *        list of Criterion.
     * @return int.
     */
    public int count(final List<Criterion> criterions)
            throws DataAccessException {
        Session session = null;
        try {
            if (getDataClazz() == null) {
                throw new DataAccessException("无法查询未知对象！");
            } else {
                session = sessionFactory.openSession();
                final Criteria criteria = session
                        .createCriteria(getDataClazz());
                if (criterions != null && !criterions.isEmpty()) {
                    for (final Criterion criterion : criterions) {
                        criteria.add(criterion);
                    }
                }
                criteria.setProjection(Projections.rowCount());
                return ((Long) criteria.list().get(0)).intValue();
            }
        } catch (final HibernateException e) {
            throw new DataAccessException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 获取全部对象.
     * @return List of <T> .
     */
    public List<T> list() throws DataAccessException {
        return list(-1, -1);
    }

    /**
     * 获取全部对象.
     * @param startPt
     *        从0开始
     * @param number
     *        每页数据量
     * @return List of <T> .
     */
    public List<T> list(final int startPt, final int number)
            throws DataAccessException {
        return list(startPt, number, null, null);
    }

    /**
     * 获取全部对象.
     * @param startPt
     *        从0开始, 如果为-1，忽略该参数.
     * @param number
     *        每页数据量, 如果为-1，忽略该参数.
     * @param criterions
     *        list of Criterion, 如果为null，忽略该参数.
     * @param orders
     *        list of Orders, 如果为null, 忽略该参数.
     * @return List of <T> .
     * @throws DataAccessException 数据访问错误时.
     */
    @SuppressWarnings("unchecked")
    public List<T> list(final int startPt, final int number,
            final List<Criterion> criterions, final List<Order> orders)
            throws DataAccessException {
        Session session = null;
        try {
            if (getDataClazz() == null) {
                throw new DataAccessException("无法查询未知对象！");
            } else {
                session = sessionFactory.openSession();
                final Criteria criteria = session
                        .createCriteria(getDataClazz());
                if (criterions != null && !criterions.isEmpty()) {
                    for (final Criterion criterion : criterions) {
                        criteria.add(criterion);
                    }
                }
                if (orders != null && !orders.isEmpty()) {
                    for (final Order order : orders) {
                        criteria.addOrder(order);
                    }
                }
                if (number > -1) {
                    criteria.setMaxResults(number);
                }
                if (startPt > -1) {
                    criteria.setFirstResult(startPt);
                }
                final List<T> result = criteria.list();
                if (getLog() != null && getLog().isDebugEnabled()) {
                    //getLog().debug(result == null ? "null" : result.toString());
                    getLog().debug(result == null ? "null" : "size:" + result.size());
                }
                return result;
            }
        } catch (final HibernateException e) {
            throw new DataAccessException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 获取指定的对象.
     * @param criterions list of Criterion, 如果为null，忽略该参数.
     * @param orders list of Orders, 如果为null, 忽略该参数.
     * @return <T>
     * @throws DataAccessException 数据访问错误时.
     */
    public T selectFirst(final List<Criterion> criterions,
            final List<Order> orders) throws DataAccessException {
        final List<T> list = list(0, -1, criterions, orders);
        if (null != list && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取唯一的对象.
     * @param criterions list of Criterion, 如果为null，返回null.
     * @return
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    public T selectUiqueOne(final List<Criterion> criterions)
            throws DataAccessException {
        Session session = null;
        try {
            if (getDataClazz() == null) {
                throw new DataAccessException("无法查询未知对象！");
            } else if (criterions == null || criterions.isEmpty()) {
                return null;
            } else {
                session = sessionFactory.openSession();
                final Criteria criteria = session
                        .createCriteria(getDataClazz());
                for (final Criterion criterion : criterions) {
                    criteria.add(criterion);
                }
                return (T) criteria.uniqueResult();
            }
        } catch (final HibernateException e) {
            throw new DataAccessException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
