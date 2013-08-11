package com.acacia.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.acacia.common.dao.pojo.APModify;
import com.acacia.common.db.DataAccessException;
/**
 * apModify表操作类
 * @author zhonglizhi
 *
 */
@Repository("apModifyDao")
public class APModifyDao extends AbstractDao<APModify> {
	private Logger log =LoggerFactory.getLogger(this.getClass());
	@Override
	protected Logger getLog() {
		return log;
	}

	@Override
	protected Class<APModify> getDataClazz() {
		return APModify.class;
	}

	public void deleteByAPId(Long apId) throws DataAccessException {
		List<Criterion> criterions=new ArrayList<Criterion>();
		criterions.add(Restrictions.eq("apId", apId));
		List<APModify> list=this.list(-1, -1, criterions, null);
		if(list!=null && list.size()>0){
			for(APModify am:list){
				this.deleteById(am.getId());
			}
		}
	}

}
