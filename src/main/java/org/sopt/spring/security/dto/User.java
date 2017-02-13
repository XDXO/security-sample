package org.sopt.spring.security.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

	private static final long serialVersionUID = -2748502499852327237L;

	private static final GrantedAuthority AUTHORITY_USER = new SimpleGrantedAuthority("ROLE_USER");
	private static final GrantedAuthority AUTHORITY_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");
	
	public static final int ROLE_USER = 1;
	public static final int ROLE_ADMIN = 2;
	
	public static final Map<String, User> USER_TABLE;
	
	static {

		USER_TABLE = new HashMap<String, User>();
		
		USER_TABLE.put("dongheon.user", new User("dongheon.user","my_password", User.ROLE_USER));
		USER_TABLE.put("dongheon.admin", new User("dongheon.admin", "admin_password", User.ROLE_ADMIN));
	}
	
	private String username;
	private String password;
	
	private List<GrantedAuthority> authorities;
	
	public User() {
		
	}

	public User(String username, String password, int role) {
	
		setUsername(username);
		setPassword(password);
		
		authorities = new ArrayList<GrantedAuthority>();
		
		if (role >= ROLE_USER) {
			
			authorities.add(AUTHORITY_USER);
		}
			
		if (role >= ROLE_ADMIN) {
			
			authorities.add(AUTHORITY_ADMIN);
		}
	}
	
	public User(String username, String password, List<GrantedAuthority> authorities) {
		
		setUsername(username);
		setPassword(password);
		setAuthorities(authorities);
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
	public void setAuthorities(List<GrantedAuthority> authorities) { this.authorities = new ArrayList<GrantedAuthority>(authorities); }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; } 
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; } 
	

	public boolean isAccountNonExpired() { return true; }
	public boolean isAccountNonLocked() { return true; }
	public boolean isCredentialsNonExpired() { return true; }
	public boolean isEnabled() { return true; }
	

	
}
