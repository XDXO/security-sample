package org.sopt.spring.security.provider;

import org.sopt.spring.security.dto.User;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class SecurityAuthenticationProvider implements AuthenticationProvider {
	
	private final String ERROR_MESSAGE_NOT_FOUND = "Account named with username %s not found.";
	private final String ERROR_MESSAGE_BAD_CREDENTIAL = "Username and Password are incorrect.";

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		if (!User.USER_TABLE.containsKey(username)) {
			
			throw new AuthenticationCredentialsNotFoundException(String.format(ERROR_MESSAGE_NOT_FOUND, username));
		}
		
		User user = User.USER_TABLE.get(username);
		
		if (!user.getPassword().equals(password)) {
			
			throw new BadCredentialsException(ERROR_MESSAGE_BAD_CREDENTIAL);
		}
		
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
		result.setDetails(user);
		
		return result;
	}

	public boolean supports(Class<?> type) {
		
		return type.equals(UsernamePasswordAuthenticationToken.class);
	}
}
