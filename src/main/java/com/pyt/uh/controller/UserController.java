package com.pyt.uh.controller;

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

//	@Autowired
//	MyUserRealm myUserRealm;
	
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
	
	@PostMapping(path="/gauth/{mailid}")
	public List<String> getUsername(@RequestBody User user) {
		return userRepository.getGUser(user.getemailid());
		
	}
}
