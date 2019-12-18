package com.pyt.uh.model;

import org.springframework.data.relational.core.mapping.Table;

@Table(value="policy")
public class Policy {

	private Integer id;
	private Integer policy_id;
	private String decription;
	private Integer user_id;
	
	public Integer getId() {
		return id;
	}
	public Integer getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	
	
}

