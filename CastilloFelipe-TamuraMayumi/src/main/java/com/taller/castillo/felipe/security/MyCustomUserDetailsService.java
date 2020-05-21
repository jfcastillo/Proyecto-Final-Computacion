package com.taller.castillo.felipe.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taller.castillo.felipe.model.TsscAdmin;
import com.taller.castillo.felipe.repository.TsscAdminRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private TsscAdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		TsscAdmin admin = adminRepository.findByUserName(username);

		if (admin != null) {
			User.UserBuilder builder = User.withUsername(username);
			builder.password(admin.getPassword());
			builder.roles(admin.getSuperAdmin());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}