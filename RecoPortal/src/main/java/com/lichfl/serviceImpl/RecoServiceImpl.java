package com.lichfl.serviceImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.lichfl.dao.RecoConfigRepo;
import com.lichfl.dao.RecoCustomRepo;
import com.lichfl.dao.RecoMatchRepo;
import com.lichfl.entity.RecoConfig;
import com.lichfl.exception.OracleCustomException;
import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
import com.lichfl.model.SubmitMatches;
import com.lichfl.service.RecoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@PropertySource("classpath:applConstant.properties")
public class RecoServiceImpl implements RecoService {

	@Autowired
	RecoCustomRepo customRepo;

	@Autowired
	RecoConfigRepo configRepo;

	@Autowired
	RecoMatchRepo recoMatchRepo;

	@Value("${reco.matchType}")
	String matchType;

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
	public String submitMatchingKeys(List<SubmitMatches> submitMatchesList, String username) {
		// TODO Auto-generated method stub

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Manual Matching Confirm by -").append(username).append(" User as on - ")
				.append(LocalDateTime.now()).append(" by : ");

		String remarks = stringBuilder.toString();
		log.info(remarks);

		log.info("match type::" + matchType);
		submitMatchesList.forEach(System.out::println);

		submitMatchesList.forEach(key -> {

			System.out.println("key:" + Integer.parseInt(key.getBrokey()));

			try {
				recoMatchRepo.executeMatchProc(Integer.parseInt(key.getBrokey()), Integer.parseInt(key.getMatchkey()),
						remarks, Double.parseDouble(key.getAmount()), matchType);
			}

			catch (Exception e) {
				e.printStackTrace();

				ExtractMessage extractMessage = new ExtractMessage();

				String errorMessage = extractMessage.extractErrorMessage(e.getMessage());

				throw new RuntimeException(errorMessage);
			}

		});
		return "success";
	}

}
