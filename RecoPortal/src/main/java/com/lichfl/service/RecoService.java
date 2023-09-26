package com.lichfl.service;

import java.util.List;

import com.lichfl.entity.ReportResponse;
import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
import com.lichfl.model.ReportParam;
import com.lichfl.model.SubmitMatches;

public interface RecoService {

	/*
	 * List<BookDto> fetchBookResults(String glCode, String fromDate, String toDate,
	 * String catg, String tranType) throws Exception;
	 */

	String getPayModes(String recoCode) throws Exception;

	List<BookDto> fetchBookResults(RecoFilter recoFilter) throws Exception;

	String submitMatchingKeys(List<SubmitMatches> submitMatchesList, String username);

	int submitReport(ReportParam reportParam) throws Exception;

//	List<ReportResponse> getReportRecords(int reportId) throws Exception;

	List<ReportResponse> getReportFiles(String bankCode) throws Exception;

}
