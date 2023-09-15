package com.lichfl.service;

import java.util.List;

import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;

public interface RecoService {

	/*
	 * List<BookDto> fetchBookResults(String glCode, String fromDate, String toDate,
	 * String catg, String tranType) throws Exception;
	 */

	String getPayModes(String recoCode) throws Exception;

	List<BookDto> fetchBookResults(RecoFilter recoFilter) throws Exception;

}
