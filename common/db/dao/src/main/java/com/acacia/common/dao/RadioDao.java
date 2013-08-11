package com.acacia.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.acacia.common.dao.pojo.Radio;
/**
 * radio表操作类
 * @author zhonglizhi
 *
 */
@Repository("radioDao")
public class RadioDao extends AbstractDao<Radio> {
	private Logger log =LoggerFactory.getLogger(this.getClass());
	@Override
	protected Logger getLog() {
		return log;
	}

	@Override
	protected Class<Radio> getDataClazz() {
		return Radio.class;
	}

}
