package com.pyt.uh.model;

import org.springframework.data.relational.core.mapping.Table;

@Table(value="Function_Role_map")
public class Function_Role_map {

	private Integer id;
	private Integer frm_id;
	private Integer function_id;
	private Integer role_id;
	private Integer desig_id;
	private Integer user_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFrm_id() {
		return frm_id;
	}
	public void setFrm_id(Integer frm_id) {
		this.frm_id = frm_id;
	}
	public Integer getFunction_id() {
		return function_id;
	}
	public void setFunction_id(Integer function_id) {
		this.function_id = function_id;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getDesig_id() {
		return desig_id;
	}
	public void setDesig_id(Integer desig_id) {
		this.desig_id = desig_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	

}