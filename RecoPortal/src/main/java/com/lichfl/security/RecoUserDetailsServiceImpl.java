package com.lichfl.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RecoUserDetailsServiceImpl implements RecoUserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public String saveUsers(List<RecoUser> recouser) {

		recouser.forEach(user -> {
			String encodedPassword = encoder.encode(user.getPassword());
			System.out.println(encodedPassword);
			user.setPassword(encodedPassword);
		});

		userRepository.saveAll(recouser);
		return "Users Added Successfully";
	}

}
