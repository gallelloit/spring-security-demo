package com.gallelloit.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * Main controller for the web application. As explained in the config file, it has just three possible pages:
 * - Home, with simple information and a tiny menu for redirecting to leader and administrator pages
 * - Leaders page (for managers)
 * - Systems page (for administrators)
 * 
 * @author pgallello
 *
 */
@Controller
public class DemoController {

	@GetMapping("/")
	public String home() {
		
		return "home";
	
	}
	
	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
		
	}
	
	@GetMapping("/systems")
	public String showSystem() {
		
		return "systems";
		
	}
	
}
