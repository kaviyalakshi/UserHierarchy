//package com.pyt.uh.realm;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.realm.jdbc.JdbcRealm;
//
//public class MyUserRealm extends JdbcRealm{
//	@Override
//	protected AuthenticationInfo 
//	  doGetAuthenticationInfo(AuthenticationToken token)
//	  throws AuthenticationException {
//	                                                                  
//	    UsernamePasswordToken uToken = (UsernamePasswordToken) token;
//	                                                                 
//	    if(uToken.getUsername() == null
//	      || uToken.getUsername().isEmpty()
//	      || !credentials.containsKey(uToken.getUsername())) {
//	          throw new UnknownAccountException("username not found!");
//	    }
//	                                         
//	    return new SimpleAuthenticationInfo(
//	      uToken.getUsername(), 
//	      credentials.get(uToken.getUsername()), 
//	      getName()); 
//	}
//}
