package com.lichfl.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichfl.dao.RecoCustomRepo;
import com.lichfl.model.BookDto;
import com.lichfl.service.RecoService;

@Service
public class RecoServiceImpl implements RecoService {

	@Autowired
	RecoCustomRepo customRepo;

	@Override
	public List<BookDto> fetchBookResults(String glCode, String fromDate, String toDate, String catg, String tranType)
			throws Exception {

		List<BookDto> resList = customRepo.fetchBookResults(glCode, fromDate, toDate, catg, tranType);
		return resList;

	}

}
