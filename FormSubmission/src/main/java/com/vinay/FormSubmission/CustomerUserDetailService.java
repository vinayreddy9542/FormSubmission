package com.vinay.FormSubmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepositary repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repo.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("User name not found");
		}
		return new CustomerUserDetails(user);
	}

}
