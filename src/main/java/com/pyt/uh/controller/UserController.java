package com.pyt.uh.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pyt.uh.model.User;
//import com.pyt.uh.realm.MyUserRealm;
import com.pyt.uh.repository.UserRepository;


@RestController
@RequestMapping(path="/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;

	@PostMapping
	public String user()
	{
		return "data";
	}
	
	@PostMapping(path="/login")
	public List<String> getUserName(@RequestBody User user)
	{
		return userRepository.getUserName(user.getName(),user.getPassword());
		
	}
	
	@RequestMapping(value="/glogin")
	public Principal user(Principal principal) {
		userRepository.getGUser(principal, null);
		return principal;
	}
}
