//package com.pyt.uh.realm;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.realm.jdbc.JdbcRealm;
//import org.apache.shiro.util.JdbcUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyUserRealm extends JdbcRealm{
//	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
//	 String username,userpassword;
//	 Map<String,String> credentials = new HashMap<>();
//	public void data(String name, String password) {
//		 this.username= name;
//		this.userpassword = password;
//		this.credentials.put(username, userpassword);
//	}
//	
//	@Override
//	 private String[] getPasswordForUser(JdbcTemplate jdbcTemplate, String username) throws SQLException {
//
//        String[] result;
//        boolean returningSeparatedSalt = false;
//        switch (saltStyle) {
//        case NO_SALT:
//        case CRYPT:
//        case EXTERNAL:
//            result = new String[1];
//            break;
//        default:
//            result = new String[2];
//            returningSeparatedSalt = true;
//        }
//        
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = conn.prepareStatement(authenticationQuery);
//            ps.setString(1, username);
//
//            // Execute query
//            rs = ps.executeQuery();
//
//            // Loop over results - although we are only expecting one result, since usernames should be unique
//            boolean foundResult = false;
//            while (rs.next()) {
//
//                // Check to ensure only one row is processed
//                if (foundResult) {
//                    throw new AuthenticationException("More than one user row found for user [" + username + "]. Usernames must be unique.");
//                }
//
//                result[0] = rs.getString(1);
//                if (returningSeparatedSalt) {
//                    result[1] = rs.getString(2);
//                }
//
//                foundResult = true;
//            }
//        } finally {
//            JdbcUtils.closeResultSet(rs);
//            JdbcUtils.closeStatement(ps);
//        }
//
//        return result;
//    }
//
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//	    UsernamePasswordToken uToken = (UsernamePasswordToken) token;
//	    System.out.println("utokenn"+uToken);                                                            
//	    if(uToken.getUsername() == null || uToken.getUsername().isEmpty() || !credentials.containsKey(uToken.getUsername())) {
//	          throw new UnknownAccountException("username not found!");
//	    }
//	                                         
//	    return new SimpleAuthenticationInfo(
//	      uToken.getUsername(), 
//	      credentials.get(uToken.getUsername()), 
//	      getName()); 
//	}
//}
