package com.acacia.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.acacia.common.dao.pojo.HostExt;
import com.acacia.common.db.DataAccessException;
/**
 * ap表操作类
 * @author zhonglizhi
 *
 */
@Repository("hostExtDao")
public class HostExtDao extends AbstractDao<HostExt> {
	private Logger log =LoggerFactory.getLogger(this.getClass());
	@Override
	protected Logger getLog() {
		return log;
	}
	
	public void rename(String macAddress,String hostName) throws DataAccessException {
		List<Criterion> criterions=new ArrayList<Criterion>();
		criterions.add(Restrictions.eq("macAddress", macAddress));
		List<HostExt> list=this.list(-1, -1, criterions, null);
		if(list!=null && list.size()>0){
			for(HostExt ext:list){
				ext.setHostName(hostName);
				this.update(ext);
			}
		}else{
			HostExt data=new HostExt();
			data.setMacAddress(macAddress);
			data.setHostName(hostName);
			this.add(data);
		}
	}
	@Override
	protected Class<HostExt> getDataClazz() {
		return HostExt.class;
	}

}
