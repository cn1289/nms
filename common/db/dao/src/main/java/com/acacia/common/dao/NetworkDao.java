package com.acacia.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.acacia.common.dao.pojo.Network;
/**
 * network表操作类
 * @author zhonglizhi
 *
 */
@Repository("networkDao")
public class NetworkDao extends AbstractDao<Network> {
	private Logger log =LoggerFactory.getLogger(this.getClass());
	@Override
	protected Logger getLog() {
		return log;
	}

	@Override
	protected Class<Network> getDataClazz() {
		return Network.class;
	}

}
