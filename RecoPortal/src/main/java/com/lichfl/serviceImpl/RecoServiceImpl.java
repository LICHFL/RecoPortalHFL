package com.lichfl.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichfl.dao.RecoConfigRepo;
import com.lichfl.dao.RecoCustomRepo;
import com.lichfl.dao.RecoMatchRepo;
import com.lichfl.entity.RecoConfig;
import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
import com.lichfl.service.RecoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecoServiceImpl implements RecoService {

	@Autowired
	RecoCustomRepo customRepo;

	@Autowired
	RecoConfigRepo configRepo;

	@Autowired
	RecoMatchRepo recoMatchRepo;

	@Override
	public List<BookDto> fetchBookResults(RecoFilter recoFilter) throws Exception {
		List<BookDto> resList = null;

		try {
			resList = customRepo.fetchBookResults(recoFilter);
			return resList;
		} catch (Exception e) {
			throw new Exception("No Results found for the provided inputs");
		}

	}

	@Override
	public String getPayModes(String recoCode) throws Exception {
		RecoConfig recoConfig = null;

		recoConfig = configRepo.findByRecoCode(recoCode);
		if (!(recoConfig.getRecoParamValue().isBlank() || recoConfig.getRecoParamValue().isEmpty())) {
			return recoConfig.getRecoParamValue();
		} else
			throw new Exception("Could not fetch payment modes!");
	}

	@Override
	public String submitMatchingKeys(String matchKey, List<String> broKeyList, String username, double amount) {

		String remarks = "Manual Matching Confirm by User as on - " + LocalDateTime.now() + " : username";
		String message = "";

		broKeyList.forEach(System.out::println);
		// System.out.println("broKeyList ::" + broKeyList);

		broKeyList.forEach(key -> {

			System.out.println("key:" + Integer.parseInt(key));

			try {
				recoMatchRepo.executeMatchProc(Integer.parseInt(key), Integer.parseInt(matchKey), remarks, amount,
						String.valueOf('M'));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Failed- Error ::" + e.getMessage());
			}

			log.info("matchKey:" + Integer.parseInt(matchKey));
		});
		return "success";

	}

}
