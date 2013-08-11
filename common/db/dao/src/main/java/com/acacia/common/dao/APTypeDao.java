package com.acacia.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.acacia.common.dao.pojo.APType;
/**
 * apType表操作类
 * @author zhonglizhi
 *
 */
@Repository("apTypeDao")
public class APTypeDao extends AbstractDao<APType> {
	private Logger log =LoggerFactory.getLogger(this.getClass());
	@Override
	protected Logger getLog() {
		return log;
	}

	@Override
	protected Class<APType> getDataClazz() {
		return APType.class;
	}

}
