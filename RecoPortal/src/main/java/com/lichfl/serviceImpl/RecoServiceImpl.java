package com.lichfl.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.lichfl.dao.RecoConfigRepo;
import com.lichfl.dao.RecoMatchRecordsRepo;
import com.lichfl.dao.RecoMatchRepo;
import com.lichfl.entity.RecoConfig;
import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
import com.lichfl.model.SubmitMatches;
import com.lichfl.service.RecoService;
import com.lichfl.util.ApplicationConstant;
import com.lichfl.util.CustomStringUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@PropertySource("classpath:applConstant.properties")
public class RecoServiceImpl implements RecoService {

	@Autowired
	RecoConfigRepo configRepo;

	@Autowired
	RecoMatchRepo recoMatchRepo;

	@Autowired
	RecoMatchRecordsRepo recoMatchRecordsRepo;

	/*
	 * @Value("${reco.matchType}") String matchType;
	 */

	@Autowired
	CustomStringUtil extractMessage;

	@Override
	public List<BookDto> fetchBookResults(RecoFilter recoFilter) throws Exception {
		List<BookDto> resList = null;
		/********* add quotes to paymodes after fetching all paymodes *******/
		/*
		 * if (recoFilter.getPMode().isEmpty())
		 * 
		 * { // // recoFilter.setPMode(extractMessage.addQuoteToStringValue(getPayModes(
		 * ApplicationConstant.PAYMODE)));
		 * 
		 * String paymodes = getPayModes(ApplicationConstant.PAYMODE);
		 * recoFilter.setPMode(Arrays.stream(paymodes.split(",")).collect(Collectors.
		 * toList()));
		 * 
		 * }
		 */
		try {
			resList = recoMatchRecordsRepo.fetchBookResults(recoFilter);
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
	public String submitMatchingKeys(List<SubmitMatches> submitMatchesList, String username) {
		// TODO Auto-generated method stub

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Manual Matching Confirmed by -").append(username).append(" User as on - ")
				.append(LocalDateTime.now()).append(" by : ");

		String remarks = stringBuilder.toString();
		log.info(remarks);

		log.info("match type::" + ApplicationConstant.MATCHTYPE);
		submitMatchesList.forEach(System.out::println);

		submitMatchesList.forEach(key -> {

			System.out.println("key:" + Integer.parseInt(key.getBrokey()));

			try {
				recoMatchRepo.executeMatchProc(Integer.parseInt(key.getBrokey()), Integer.parseInt(key.getMatchkey()),
						remarks, Double.parseDouble(key.getAmount()), ApplicationConstant.MATCHTYPE);
			}

			catch (Exception e) {
				e.printStackTrace();

				String errorMessage = extractMessage.extractErrorMessage(e.getMessage());

				throw new RuntimeException(errorMessage);
			}

		});
		return "success";
	}

}
