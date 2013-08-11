package com.acacia.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.acacia.common.dao.pojo.MacVendor;
/**
 * 网卡地址和供应商关系表操作类
 * @author zhonglizhi
 *
 */
@Repository("macVendorDao")
public class MacVendorDao extends AbstractDao<MacVendor> {
	private Logger log =LoggerFactory.getLogger(this.getClass());
	@Override
	protected Logger getLog() {
		return log;
	}

	@Override
	protected Class<MacVendor> getDataClazz() {
		return MacVendor.class;
	}

}
