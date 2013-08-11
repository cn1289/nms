package com.acacia.nms.server.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acacia.common.dao.APDao;
import com.acacia.common.dao.APModifyDao;
import com.acacia.common.dao.EncryptionDao;
import com.acacia.common.dao.NetworkDao;
import com.acacia.common.dao.RadioDao;
import com.acacia.common.dao.pojo.AP;
import com.acacia.common.dao.pojo.APModify;
import com.acacia.common.dao.pojo.Encryption;
import com.acacia.common.dao.pojo.Network;
import com.acacia.common.dao.pojo.Radio;
import com.acacia.common.db.DataAccessException;

@Controller
public class APController {
	private NetworkDao networkDao;
	private RadioDao radioDao;
	private EncryptionDao encryptionDao;
	private APDao apDao;
	private APModifyDao apModifyDao;
	@RequestMapping("getNetworkById")
	public @ResponseBody Network getNetworkById(@RequestParam("id") Long id) throws DataAccessException{
		return networkDao.getById(id);
	}
	@RequestMapping("apList")
	public @ResponseBody List<AP> getAPs() throws DataAccessException{
		return apDao.list();
	}
	@RequestMapping("radios/{apId}")
	public @ResponseBody Set<Radio> getRadiosByAPId(@PathVariable("apId") Long apId) throws DataAccessException{
		return apDao.getById(apId).getRadios();
	}
	@RequestMapping("networks/{radioId}")
	public @ResponseBody Set<Network> getNetworksByRadioId(@PathVariable("radioId") Long radioId) throws DataAccessException{
		return radioDao.getById(radioId).getNetworks();
	}
	@RequestMapping("updateNetwork")
	public@ResponseBody Integer updateSSid(@RequestParam("ssid")String ssid,
			@RequestParam("networkId")Long networkId,
			@RequestParam("encryptionKey")String encryptionKey){
		
		try {
			Network network = networkDao.getById(networkId);
			if(network!=null){
				if(ssid!=null && !ssid.equals("")){
					network.setSsid(ssid);
					networkDao.update(network);
				}
				if(encryptionKey!=null && !encryptionKey.equals("")){
					Encryption encryption = network.getEncryption();
					if(encryption!=null){
						encryption.setEncryptionKey(encryptionKey);
						encryptionDao.update(encryption);
					}
				}
				Radio radio = radioDao.getById(network.getRadioId());
				if(radio!=null){
					APModify apModify=new APModify();
					apModify.setApId(radio.getApId());
					apModifyDao.deleteByAPId(radio.getApId());
					apModifyDao.add(apModify);
				}
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	@Autowired
	public void setNetworkDao(NetworkDao networkDao) {
		this.networkDao = networkDao;
	}
	@Autowired
	public void setRadioDao(RadioDao radioDao) {
		this.radioDao = radioDao;
	}
	@Autowired
	public void setEncryptionDao(EncryptionDao encryptionDao) {
		this.encryptionDao = encryptionDao;
	}
	@Autowired
	public void setApDao(APDao apDao) {
		this.apDao = apDao;
	}
	@Autowired
	public void setApModifyDao(APModifyDao apModifyDao) {
		this.apModifyDao = apModifyDao;
	}
}
