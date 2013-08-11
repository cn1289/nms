package com.acacia.common.dao.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="radio")
public class Radio extends BaseData{
	private static final long serialVersionUID = 6612820642517860135L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="ap_id")
	private Long apId;
	@Column(name="enable")
	private int enable;
	@Column(name="channel")
	private String channel;
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="radioId")
	private Set<Network> networks;
	public Set<Network> getNetworks() {
		return networks;
	}
	public void setNetworks(Set<Network> networks) {
		this.networks = networks;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getApId() {
		return apId;
	}
	public void setApId(Long apId) {
		this.apId = apId;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
