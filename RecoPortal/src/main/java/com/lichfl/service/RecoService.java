package com.lichfl.service;

import java.util.List;

import com.lichfl.model.BookDto;

public interface RecoService {

	List<BookDto> fetchBookResults(String glCode, String fromDate, String toDate, String catg, String tranType)
			throws Exception;

}
