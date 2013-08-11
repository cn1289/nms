package com.acacia.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.acacia.common.dao.pojo.Encryption;
/**
 * encryption表操作类
 * @author zhonglizhi
 *
 */
@Repository("encryptionDao")
public class EncryptionDao extends AbstractDao<Encryption> {
	private Logger log =LoggerFactory.getLogger(this.getClass());
	@Override
	protected Logger getLog() {
		return log;
	}

	@Override
	protected Class<Encryption> getDataClazz() {
		return Encryption.class;
	}

}
