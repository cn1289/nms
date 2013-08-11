package com.acacia.common.dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 网卡地址和供应商对照表基类
 * @author zhonglizhi
 *
 */
@Entity
@Table(name="mac_vendor")
public class MacVendor extends BaseData{
	private static final long serialVersionUID = 4392672444787110125L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="mac_address")
	private String macAddress;
	@Column(name="vendor")
	private String vendor;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
}
