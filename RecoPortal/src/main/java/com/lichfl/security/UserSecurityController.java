package com.lichfl.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSecurityController {
	@Autowired
	RecoUserDetailsService reoDetailsService;

	@PostMapping("/addUsers")
	String saveUsers(@RequestBody List<RecoUser> recouser) {

		reoDetailsService.saveUsers(recouser);
		return "User  created";

	}

}
