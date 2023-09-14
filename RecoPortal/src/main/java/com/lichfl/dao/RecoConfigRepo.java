package com.lichfl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lichfl.entity.RecoConfig;

public interface RecoConfigRepo extends JpaRepository<RecoConfig, Integer> {

	RecoConfig findByRecoCode(String recoCode);

}
