package com.pyt.uh.realm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.catalina.realm.JDBCRealm;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.pyt.uh.model.User;

public class PYTRealm  extends JDBCRealm{

    private DataSource dataSource;
    public String getName() {
        // TODO Auto-generated method stub
        return "PYTRealm";
    }
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        SimpleAuthenticationInfo info = null;
        
        User user = getUserInfo(userName);
        System.out.println("User name"+user.getUsername()+"==============");
        info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),getName());
        
        return info;
    }
    
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    private User getUserInfo(String username){
        User user = new User();
        String sql = "select username,password from user where username=?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            set = statement.executeQuery();
            
            if(set.next()) {
                String username1 = set.getString(1);
                String password = set.getString(2);
                user.setPassword(password);
                user.setUsername(username1);
            }    
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeAll(connection, set, statement);
        }
        return user;
    }
    
    private void closeAll(Connection conn,ResultSet set,PreparedStatement statement) {
        try {
            if(set != null) {
                set.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}