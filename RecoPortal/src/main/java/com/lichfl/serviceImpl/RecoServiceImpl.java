package com.lichfl.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichfl.dao.RecoCustomRepo;
import com.lichfl.dao.RecoCustomRepoTemplate;
import com.lichfl.model.BookDto;
import com.lichfl.service.RecoService;

@Service
public class RecoServiceImpl implements RecoService {

	@Autowired
	RecoCustomRepo customRepo;

	@Autowired
	RecoCustomRepoTemplate cecoCustomRepoTemplate; // TO be corrected

	@Override
	public List<BookDto> fetchBookResults(String glCode, String fromDate, String toDate, String catg) throws Exception {

		List<BookDto> resList = customRepo.fetchBookResults(glCode, fromDate, toDate, catg);
		return resList;

	}

}
