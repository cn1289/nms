package com.acacia.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.acacia.common.dao.pojo.AP;
/**
 * ap表操作类
 * @author zhonglizhi
 *
 */
@Repository("apDao")
public class APDao extends AbstractDao<AP> {
	private Logger log =LoggerFactory.getLogger(this.getClass());
	@Override
	protected Logger getLog() {
		return log;
	}

	@Override
	protected Class<AP> getDataClazz() {
		return AP.class;
	}

}
