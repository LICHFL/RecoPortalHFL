package com.lichfl.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class RecoMatchRepo {

	@PersistenceContext
	private EntityManager entityManager;

	public void executeMatchProc(int matchKey, int broKeyNo, String narration, double amount, String matchType)
			throws SQLException {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("BRS_MATCH_BANK_RECO");

		// Register input parameters
		storedProcedure.registerStoredProcedureParameter("matchKey", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("broKeyNo", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("narration", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("amount", Double.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("matchType", String.class, ParameterMode.IN);

		// Set parameter values
		storedProcedure.setParameter("matchKey", matchKey);
		storedProcedure.setParameter("broKeyNo", broKeyNo);
		storedProcedure.setParameter("narration", narration);
		storedProcedure.setParameter("amount", amount);
		storedProcedure.setParameter("matchType", matchType);

		// Execute the stored procedure

		storedProcedure.execute();
	}
}
