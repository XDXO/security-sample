package org.sopt.spring.security.service;

import org.sopt.spring.security.dto.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	private static final String ERROR_MESSAGE_USER_NOT_FOUND = "Account named with username %s not found.";

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (User.USER_TABLE.containsKey(username))
			return User.USER_TABLE.get(username);
		
		throw new UsernameNotFoundException(String.format(ERROR_MESSAGE_USER_NOT_FOUND, username)); 
	}
}
