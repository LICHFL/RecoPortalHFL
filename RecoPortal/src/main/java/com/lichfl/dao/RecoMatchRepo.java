package com.lichfl.dao;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class RecoMatchRepo {

	@PersistenceContext
	private EntityManager entityManager;

	public void executeMatchProc(int broKeyNo, int matchKey, String narration, int amount, String matchType) {
	        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("hfl_brs.BRS_MATCH_BANK_RECO");

	        // Register input parameters
	        storedProcedure.registerStoredProcedureParameter("broKeyNo", Integer.class, ParameterMode.IN);
	        storedProcedure.registerStoredProcedureParameter("matchKey", Integer.class, ParameterMode.IN);
	        storedProcedure.registerStoredProcedureParameter("narration", String.class, ParameterMode.IN);
	        storedProcedure.registerStoredProcedureParameter("amount", Integer.class, ParameterMode.IN);
	        storedProcedure.registerStoredProcedureParameter("matchType", String.class, ParameterMode.IN);

	        // Set parameter values
	        storedProcedure.setParameter("broKeyNo", broKeyNo);
	        storedProcedure.setParameter("matchKey", matchKey);
	        storedProcedure.setParameter("narration", narration);
	        storedProcedure.setParameter("amount", amount);
	        storedProcedure.setParameter("matchType", matchType);

	        // Execute the stored procedure
	
	        storedProcedure.execute();
	}
}
