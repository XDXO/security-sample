package org.sopt.spring.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthenticationProcessSuccessHandler implements AuthenticationSuccessHandler {

	private static final String SESSION_USER = "user";

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute(SESSION_USER) == null) {
			
			session.setAttribute(SESSION_USER, authentication);
		}
		
		response.sendRedirect("/");
	}	
}