package com.lichfl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lichfl.entity.ReportResponse;

public interface RecoReportDao extends JpaRepository<ReportResponse, Integer> {

	// @Query(value ="select * from HFLBRS_REPORT_FE r where r.HRF_CHILD_REP_ID like
	// :value%", nativeQuery = true)
	// List<ReportResponse> findByHrfChildRepIdStartsWith(String value);

	List<ReportResponse> findByHrfBankCode(String bankCode);

}
