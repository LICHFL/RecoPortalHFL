package com.lichfl.service;

import java.util.Optional;

import com.lichfl.entity.BrsUserDetails;

public interface BrsUserService {

	Optional<BrsUserDetails> getUserDetails(String username);

}
