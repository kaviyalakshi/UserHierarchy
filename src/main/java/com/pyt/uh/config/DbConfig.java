//package com.pyt.uh.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//public class DbConfig {
//	@Bean
//    @Primary
//    public DataSource mySqlDataSource() 
//    {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url("jdbc:mysql://localhost:3306/plato");
//        dataSourceBuilder.username("root");
//        dataSourceBuilder.password("1234");
//        return dataSourceBuilder.build();
//    }
//
//
//	 
//	    
//}
