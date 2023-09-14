package com.lichfl.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichfl.dao.RecoConfigRepo;
import com.lichfl.dao.RecoCustomRepo;
import com.lichfl.entity.RecoConfig;
import com.lichfl.model.BookDto;
import com.lichfl.service.RecoService;

@Service
public class RecoServiceImpl implements RecoService {

	@Autowired
	RecoCustomRepo customRepo;

	@Autowired
	RecoConfigRepo configRepo;

	

	@Override
	public List<BookDto> fetchBookResults(String glCode, String fromDate, String toDate, String catg, String tranType)
			throws Exception {

		List<BookDto> resList = customRepo.fetchBookResults(glCode, fromDate, toDate, catg, tranType);
		return resList;

	}

	@Override
	public String getPayModes(String recoCode) {
		RecoConfig recoConfig=null;

		recoConfig = configRepo.findByRecoCode(recoCode);
		if (!(recoConfig.getRecoParamValue().isBlank() || recoConfig.getRecoParamValue().isEmpty())) {
			return recoConfig.getRecoParamValue();
		} else
			throw new NullPointerException("Could not fetch payment modes!");
	}

}
