package org.sopt.spring.security;

import org.sopt.spring.security.dto.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {

	private static final String ATTRIBUTE_USER = "user";

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String handleIndexPageRequest(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && authentication.getDetails() instanceof User) {
			
			User user = (User) authentication.getDetails();
			model.addAttribute(ATTRIBUTE_USER, user);
		}
		
		return "index";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String handleLoginPageRequest() {
		
		return "login";
	}
	
	@RequestMapping(path = "/entry", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_USER')")
	public String handleAuthorizedPageRequest(Model model) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
		model.addAttribute(ATTRIBUTE_USER, user);
		
		return "entry";
	}
	
	@RequestMapping(path = "/admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String handleAdminPageRequest(Model model) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
		model.addAttribute(ATTRIBUTE_USER, user);
		
		return "admin";
	}
}
