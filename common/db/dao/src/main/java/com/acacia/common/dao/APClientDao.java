package com.acacia.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.acacia.common.dao.pojo.APClient;
/**
 * apclient表操作类
 * @author zhonglizhi
 *
 */
@Repository("apClientDao")
public class APClientDao extends AbstractDao<APClient> {
	private Logger log =LoggerFactory.getLogger(this.getClass());
	@Override
	protected Logger getLog() {
		return log;
	}

	@Override
	protected Class<APClient> getDataClazz() {
		return APClient.class;
	}

}
