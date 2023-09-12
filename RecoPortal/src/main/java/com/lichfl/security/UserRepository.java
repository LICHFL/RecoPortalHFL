package com.lichfl.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<RecoUser, Long> {

	Optional<RecoUser> findByUserName(String username);

}
