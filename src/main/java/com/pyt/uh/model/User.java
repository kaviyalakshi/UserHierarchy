package com.pyt.uh.model;

import org.springframework.data.relational.core.mapping.Table;

@Table(value="user")
public class User {
	
	private Integer id;
	private String name;
	private String username;
	private String password;
	private String designation;
	private Integer active;
	private String emailid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String emailid) {
		this.name = name;
	}
	public String getemailid() {
		return emailid;
	}
	public void setemailid(String emailid) {
		this.emailid = emailid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
	
}
