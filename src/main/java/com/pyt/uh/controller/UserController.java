package com.pyt.uh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pyt.uh.repository.UserRepository;


@RestController
@RequestMapping(path="/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping
	public String user()
	{
		return "data";
	}
	
	@GetMapping(path="/login/{name}/{password}")
	public List<String> getUserName(@PathVariable("name") String name, @PathVariable("password") String password)
	{
		return userRepository.getUserName(name,password);
	}
	
	@GetMapping(path="/gauth/{mailid}")
	public List<String> getUsername(@PathVariable("mailid") String mailid) {
		return userRepository.getGUser(mailid);
		
	}
//	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = { "Content-type=application/json" })
//	public GenericResponse login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
//	
//	
//		
//		
}
