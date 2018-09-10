package com.gallelloit.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * 
 * This project has no xml files for configuration purposes. All the configurations are set in Java files.
 * 
 * This Java configuration class enables WebMvc (`@EnableWebMvc`), sets a package base for component
 * scanning and sets a *.properties file for static configuration values.
 * 
 * Java Configuration File
 * 
 * @author pgallello
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.gallelloit.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

	// Set a variable to hold the properties
	@Autowired
	private Environment env;
	
	// Logger configuration
	private Logger logger = Logger.getLogger(getClass().getName());
	
	/**
	 * Define a bean for VewResolver
	 * @return The generated ViewResolver
	 */
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
		
	}
	
	/**
	 * Define a bean for our security datasource.
	 * 
	 * All the values are taken from `persistence-mysql.properties`.
	 * 
	 * @return 
	 */
	@Bean
	public DataSource securityDataSource() {
		
		// Create connetion pull
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// Set the jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		// Log the connection properties
		logger.info(">>>jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info(">>>jdbc.user=" + env.getProperty("jdbc.user"));
		logger.info(">>>connection.pool.initialPoolSize=" + env.getProperty("connection.pool.initialPoolSize"));
		logger.info(">>>connection.pool.minPoolSize=" + env.getProperty("connection.pool.minPoolSize"));
		logger.info(">>>connection.pool.maxPoolSize=" + env.getProperty("connection.pool.maxPoolSize"));
		logger.info(">>>connection.pool.maxIdleTime=" + env.getProperty("connection.pool.maxIdleTime"));
		
		// Set the database connection properties
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// Set the connection pool properties
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	// Helper method to read environment property and convert it to int
	private int getIntProperty(String propertyName) {
		
		String propertyValue = env.getProperty(propertyName);
		
		// Covnert it to int
		int intPropertyValue = Integer.parseInt(propertyValue);
		
		return intPropertyValue;
		
	}
	
}
