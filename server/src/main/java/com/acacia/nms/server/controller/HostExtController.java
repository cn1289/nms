package com.acacia.nms.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acacia.common.dao.HostExtDao;
import com.acacia.common.dao.pojo.HostExt;
import com.acacia.common.db.DataAccessException;
@Controller
public class HostExtController {
	private HostExtDao hostExtDao;
	@RequestMapping("hostNames")
	public @ResponseBody List<HostExt> getHostNameMap() throws DataAccessException {
		return hostExtDao.list();
	}
	@RequestMapping("rename")
	public @ResponseBody Integer rename(@RequestParam("macAddress")String macAddress,@RequestParam("hostName")String hostName){
		Integer result=1;
		 try {
			hostExtDao.rename(macAddress,hostName);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=0;
		}
		 return result;
	}
	@Autowired
	public void setHostExtDao(HostExtDao hostExtDao) {
		this.hostExtDao = hostExtDao;
	}
	
}
