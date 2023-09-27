package com.lichfl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lichfl.entity.ReportResponse;

public interface RecoReportDao extends JpaRepository<ReportResponse, Integer> {

	List<ReportResponse> findByHrfBankCodeStartsWith(String bankCode);

}
