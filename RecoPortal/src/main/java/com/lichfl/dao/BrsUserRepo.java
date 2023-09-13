package com.lichfl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lichfl.entity.BrsUserDetails;

public interface BrsUserRepo extends JpaRepository<BrsUserDetails, String> {

}
