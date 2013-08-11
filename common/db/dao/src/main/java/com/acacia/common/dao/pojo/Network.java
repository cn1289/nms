package com.acacia.common.dao.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="network")
public class Network extends BaseData{
	private static final long serialVersionUID = 3668382171532234134L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="radio_id")
	private Long radioId;
	@Column(name="SSID")
	private String ssid;
	@Column(name="VLAN_ID")
	private Long vlanId;
	@Column(name="ENABLE")
	private int enable;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="ENCRYPTION_ID")
	private Encryption encryption;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRadioId() {
		return radioId;
	}
	public void setRadioId(Long radioId) {
		this.radioId = radioId;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public Long getVlanId() {
		return vlanId;
	}
	public void setVlanId(Long vlanId) {
		this.vlanId = vlanId;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Encryption getEncryption() {
		return encryption;
	}
	public void setEncryption(Encryption encryption) {
		this.encryption = encryption;
	}
	
}
