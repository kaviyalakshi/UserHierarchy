package com.pyt.uh.repository;

import java.util.List;
import java.util.Map;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends JdbcRealm{
	private static Logger log = LoggerFactory.getLogger(UserRepository.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    public List<String> getUserName(String name,String password){
    	String pass1 = password;
		List<Map<String, Object>> queryAnswers = jdbcTemplate.queryForList("select password from user where name = '" + name + "' " );	
		for(Map<String,Object> detail : queryAnswers) {
			if(pass1.equals((detail.get("password")).toString())) {
				System.out.println("yes matched");
//				IniRealm iniRealm = new IniRealm("classpath:application.properties");
//				SecurityManager securityManager = new DefaultSecurityManager(iniRealm);
//				SecurityUtils.setSecurityManager(securityManager);
//				Subject currentUser = SecurityUtils.getSubject();
				
			    Subject currentUser = SecurityUtils.getSubject();
			    Session session = currentUser.getSession();
			    session.setAttribute("someKey", "aValue");
			    String value = (String) session.getAttribute("someKey");
			    if (value.equals("aValue")) {
			    log.info("Retrievedcorrect value! [" + value + "]");
			    }
			    if (!currentUser.isAuthenticated()) {               
					UsernamePasswordToken token = new UsernamePasswordToken(name,password);
			   	    System.out.println(token+"tokennn");
				    token.setRememberMe(true);  
				    
			    }
//					  try {                                             
//					      currentUser.login(token);                       
//					  } catch (UnknownAccountException uae) {           
//					      log.error("Username Not Found!", uae);        
//					  } catch (IncorrectCredentialsException ice) {     
//					      log.error("Invalid Credentials!", ice);       
//					  } catch (LockedAccountException lae) {            
//					      log.error("Your Account is Locked!", lae);    
//					  } catch (AuthenticationException ae) {            
//					      log.error("Unexpected Error!", ae);           
//					  }                                                 
//					}
			}
			System.out.println(detail);
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
  
    
//    SecurityUtils.setSecurityManager(securityManager);
//    currentUser = SecurityUtils.getSubject();
//    Session session = currentUser.getSession();
//     session.setAttribute("someKey", "aValue");
//     String value = (String) session.getAttribute("someKey");
//     if (value.equals("aValue")) {
//     log.info("Retrievedcorrect value! [" + value + "]");
//     }
//    // let's login the current user so we can check against roles and
//     // permissions:
//     if (!currentUser.isAuthenticated()) {
//     UsernamePasswordToken token = new UsernamePasswordToken(
//     username, pass);
//     token.setRememberMe(true);
//     currentUser.login(token);
//    }
    
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
//    throws AuthenticationException {
//    UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//
//    String username = upToken.getUsername();
//    checkNotNull(username, "Null usernames are not allowed by this realm.");
//
//    String password = pass;
//    checkNotNull(password, "No account found for user [" + username + "]");
//
//    return
//    new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
//    }
//
//    private void checkNotNull(Object reference, String message) {
//    if (reference == null) {
//    throw new AuthenticationException(message);
//    }
//    }
    }
