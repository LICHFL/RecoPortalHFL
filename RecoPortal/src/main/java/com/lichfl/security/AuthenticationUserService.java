package com.lichfl.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("username :: " + username);
		Optional<RecoUser> user = userRepository.findByUserName(username);
		System.out.println("user ::" + user);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user.map(RecoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User name was not found !!"));

	}

}
