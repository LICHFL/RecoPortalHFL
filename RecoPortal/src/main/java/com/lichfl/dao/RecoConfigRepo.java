package com.lichfl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lichfl.entity.RecoConfig;

public interface RecoConfigRepo extends JpaRepository<RecoConfig, Integer> {

	RecoConfig findByRecoCode(String recoCode);

	// @formatter:off 


	@Query(value = "CALL BRS_MATCH_BANK_RECO(:broKeyNo, :matchKey, :narration, :amount, :matchType);", nativeQuery = true)
	void executeMatchProc(
			
			   @Param("broKeyNo") int broKeyNo,
		        @Param("matchKey") int matchKey,
		        @Param("narration") String narration,
		        @Param("amount") int amount,
		        @Param("matchType") String matchType
			);
	// @formatter:on
}
