package com.lichfl.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lichfl.dao.BrsUserRepo;
import com.lichfl.entity.BrsUserDetails;
import com.lichfl.service.BrsUserService;

@Service
public class BrsUserServiceImpl implements BrsUserService {

	@Autowired
	BrsUserRepo brsUserRepo;

	@Override
	public Optional<BrsUserDetails> getUserDetails(String username) {

		Optional<BrsUserDetails> user = brsUserRepo.findById(username);
		if (user.isPresent()) {
			return user;
		} else {
			throw new UsernameNotFoundException("User details could not be fetched");
		}

	}

}
