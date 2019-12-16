package com.pyt.uh.repository;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository{
	
	private static Logger log = LoggerFactory.getLogger(UserRepository.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    public List<String> getUserName(String name,String password){
   	String pass1 = password;
	IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
	SecurityManager securityManager = new DefaultSecurityManager(iniRealm);
	SecurityUtils.setSecurityManager(securityManager);
	Subject currentUser = SecurityUtils.getSubject();
    System.out.println("CURRENT USER" + currentUser);
    if (!currentUser.isAuthenticated()) {  
    	List<Map<String, Object>> queryAnswers = jdbcTemplate.queryForList("select password from user where name = '" + name + "' " );
		for(Map<String,Object> detail : queryAnswers) {
			System.out.println("coming inn"+detail);
			if(pass1.equals((detail.get("password")).toString())) {
				UsernamePasswordToken token = new UsernamePasswordToken(name,password);
		   	    System.out.println("TOKEN" + token);
			    token.setRememberMe(true);  
			    System.out.println("yes");
			    try {                                             
				      currentUser.login(token);                       
				  } catch (UnknownAccountException uae) {           
				      log.error("Username Not Found!", uae);        
				  } catch (IncorrectCredentialsException ice) {     
				      log.error("Invalid Credentials!", ice);       
				  } catch (LockedAccountException lae) {            
				      log.error("Your Account is Locked!", lae);    
				  } catch (AuthenticationException ae) {            
				      log.error("Unexpected Error!", ae);           
				  }                                                 
		    	
	Session session = currentUser.getSession();
    session.setAttribute(name, token);
    String value = (String) session.getAttribute(name);
    if (value.equals(name)) {
    log.info("Retrvedcorrect value! [" + value + "]");
    }
    System.out.println("current user "+currentUser);
			    }
		}
			    }
		return null;		
	}
    
    public List<String> getGUser(String emailid){
    	String email = emailid;
		List<Map<String, Object>> queryAnswers = jdbcTemplate.queryForList("select name from user where emailid = '" + emailid + "' " );	
		for(Map<String,Object> detail : queryAnswers) {
			if(email.equals((detail.get("name")).toString())) {
				System.out.println("yes matched");
			}
			System.out.println(detail);
		}
		return null;		
	}
  
}