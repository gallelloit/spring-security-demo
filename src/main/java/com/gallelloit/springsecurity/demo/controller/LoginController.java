package com.gallelloit.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * Spring Security requirement to customize Login and Access Denied pages
 * The url of the showMyLoginPage and the name of the access-denied jpg are both
 * configured in `DemoSecurityConfig.configure(HttpSecurity)`  
 * 
 * @author pgallello
 *
 */
@Controller
public class LoginController {

	/**
	 * Two versiones of the login page have been created:
	 * * A plain one (plain-login)
	 * * An enhanced one (fancy-login)
	 * 
	 * @return The name of the login jsp.
	 */
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage () {
		
		//return "plain-login";
		return "fancy-login";
		
	}
	
	// Access denied mapping
	@GetMapping("/access-denied")
	public String showMyAccessDeniedPage () {
		
		return "access-denied";
		
	}
	
}
