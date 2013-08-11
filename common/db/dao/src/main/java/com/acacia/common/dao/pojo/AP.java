package com.acacia.common.dao.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ap")
public class AP extends BaseData{
	private static final long serialVersionUID = -2361606188010618139L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="mac_address")
	private String macAddress;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="ap_type_id")
	private APType apType;
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="apId")
	private Set<Radio> radios;
	public Set<Radio> getRadios() {
		return radios;
	}
	public void setRadios(Set<Radio> radios) {
		this.radios = radios;
	}
	public Long getId() {
		return id;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public APType getApType() {
		return apType;
	}
	public void setApType(APType apType) {
		this.apType = apType;
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
}
