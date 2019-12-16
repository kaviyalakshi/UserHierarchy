package com.pyt.uh.model;

import org.springframework.data.relational.core.mapping.Table;

public class Function {

		private Integer id;
		private String function_name;
		private String role_name;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getFunction_name() {
			return function_name;
		}
		public void setFunction_name(String function_name) {
			this.function_name = function_name;
		}
		public String getRole_name() {
			return role_name;
		}
		public void setRole_name(String role_name) {
			this.role_name = role_name;
		}
		
}
