package com.pyt.uh.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.pyt.uh.model.User;



@Repository
public class UserRepository{
	
private static Logger log = LoggerFactory.getLogger(UserRepository.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired 
	private RedisTemplate<String, String> templ;
	
    public List<String> getUserName(String name,String password) {
    	Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("CURRENT USER" + subject);
        if (!subject.isAuthenticated()) {
        	UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        	token.setRememberMe(true);
	        try {
	            subject.login(token);
	            if(subject.isAuthenticated()) {	            	
	                log.info("User Logon Authentication Successful. Session: "+ subject.getSession() );
	                templ.opsForValue().set(subject.toString(), subject.getSession().toString());
	            }
	        } catch (UnknownAccountException uae) {           
	        	log.error("Username Not Found!", uae);        
	        } catch (IncorrectCredentialsException ice) {     
	        	log.error("Invalid Credentials!", ice);       
	        } catch (LockedAccountException lae) {            
	        	log.error("Your Account is Locked!", lae);
	        } catch (AuthenticationException e) {
	        	e.printStackTrace();
	        	log.error("User name or password error, login failure");
	        }
        }
		return null;
        }
   
    public List<Map<String, Object>> Function_map() {
    	List<Map<String, Object>> queryAnswers = jdbcTemplate.queryForList("select * from functions ");
		for(Map<String,Object> detail : queryAnswers) {
		System.out.println(detail);
    	
    }
		return queryAnswers;
    }
    
    public List<Map<String, Object>> Designations_map () {
    	List<Map<String, Object>> queryAnswers = jdbcTemplate.queryForList("select * from levels");
		for(Map<String,Object> detail : queryAnswers) {
		System.out.println(detail);
    	
    }
    	return queryAnswers;
    }
    
    public List<Map<String, Object>> Roles_map () {
    	List<Map<String, Object>> queryAnswers = jdbcTemplate.queryForList("select * from role");
		for(Map<String,Object> detail : queryAnswers) {
		System.out.println(detail);
    	
    }
    	return queryAnswers;
    }
    
    public List<Map<String, Object>> Function_Role_map (int id) {
    	List<Map<String, Object>> queryAnswers = jdbcTemplate.queryForList("select r.role_id,r.function_id,r.level_id,r.policies\n" + 
    			"from role r,user u\n" + 
    			"where u.user_id ='"+id+"' AND\n" + 
    			"r.role_id = u.role_id ");
		for(Map<String,Object> detail : queryAnswers) {
		System.out.println(detail);
    	
    }
    	return queryAnswers;
    }
    
    public List<Map<String, Object>> ValidatePolicy (int user_id, int id) {
    	List<Map<String, Object>> queryAnswers = jdbcTemplate.queryForList("select valid from policies where user_id = '"+user_id+"' and id = '"+id+"'");
		for(Map<String,Object> detail : queryAnswers) {
		System.out.println(detail);
    	
    }
    	return queryAnswers;
    }
    public List<Map<String, Object>> new_user(String emailid,String name, String password) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
    	List<Map<String, Object>> queryAnswers = jdbcTemplate.queryForList("INSERT INTO user( 'emailid','username', 'password', 'active') VALUES ('"+emailid+"','"+name+"','"+password+"',1) ");
		for(Map<String,Object> detail : queryAnswers) {
		System.out.println(detail);
    	
    }
		return queryAnswers;
    }
    public List<Map<String, Object>> remove_user(String name) {
    	List<Map<String, Object>> queryAnswers = jdbcTemplate.queryForList("UPDATE user SET 'active'=0 WHERE username= '"+name+"' ");
		for(Map<String,Object> detail : queryAnswers) {
		System.out.println(detail);
    	
    }
		return queryAnswers;
    }
}