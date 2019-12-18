package com.pyt.uh.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pyt.uh.model.AdminUser;
import com.pyt.uh.model.Function_Role_map;
import com.pyt.uh.model.Policy;
import com.pyt.uh.model.User;
import com.pyt.uh.repository.UserRepository;


@RestController
@RequestMapping(path="/api")
public class UserController{
	
	@Autowired
	UserRepository userRepository;

	@PostMapping
	public String user()
	{
		return "data";
	}
	
	
	@PostMapping(path="/login")
	public List<String> getUserName(@RequestBody AdminUser user)
	{
		return userRepository.getUserName(user.getUsername(),user.getPassword());
		
	}
	
//	@GetMapping(value="/glogin")
//	public Principal user(Principal principal) {
//		userRepository.getGUser(principal, null);
//		return principal;
//	}
//	
	@GetMapping(value="/functions")
	public List<Map<String, Object>> functions() {
		
		return userRepository.Function_map();
	}
	
	@RequestMapping(value="/roles")
	public List<Map<String, Object>> Roles() {
		
		return userRepository.Roles_map();
	}
	
	@GetMapping(value="/designations")
	public List<Map<String, Object>> Designations() {
		
		return userRepository.Designations_map();
	}
	
	@GetMapping(value="/getPolicy/{id}")
	public List<Map<String, Object>> functions_Role_Mapping(@PathVariable int id) {
		
		return userRepository.Function_Role_map(id);
	}
	
	@GetMapping(value="/validatePolicy/{user_id}/{id}")
	public List<Map<String, Object>> getPolicy(@PathVariable int user_id, @PathVariable int id) {
		
		return userRepository.ValidatePolicy(user_id,id);
	}
	
}
