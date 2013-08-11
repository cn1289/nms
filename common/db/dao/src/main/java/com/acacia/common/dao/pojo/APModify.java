package com.acacia.common.dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ap_modify")
public class APModify extends BaseData{
	private static final long serialVersionUID = -3686292028182944012L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="AP_ID")
	private Long apId;
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
}
