package com.gallelloit.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * This Java configuration file extends `WebSecurityConfigurerAdapter` to set the main configurations for
 * Spring Security.
 * 
 * @author pgallello
 *
 */
@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource;

	/**
	 * Sets the Authentication. Two strategies are shown, one of them always commented:
	 * 
	 * * A set of static users
	 * * Configuration for jdbc authentication (database is needed with two basic tables for
	 * security purposes.
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * UserBuilder users = User.withDefaultPasswordEncoder();
		 * For static users approach (for testing or debugging purposes, comment out this lines and
		 * comment the Jdbc authentication one.
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
			.withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));
		*/
		
		// Jdbc authentication		
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
	}

	/**
	 * 
	 * Configures the HTTP security relating a number of routes to different user roles. Spring will do
	 * the work of matching routes with roles to allow or reject accesses on running time.
	 * 
	 * It also defines routes for login and access-denied pages.
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/").hasRole("EMPLOYEE")
				.antMatchers("/leaders/**").hasRole("MANAGER")
				.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
				.formLogin()
					.loginPage("/showMyLoginPage")
					.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
				.logout()
				.permitAll()
			.and()
				.exceptionHandling()
					.accessDeniedPage("/access-denied");
	}

}
