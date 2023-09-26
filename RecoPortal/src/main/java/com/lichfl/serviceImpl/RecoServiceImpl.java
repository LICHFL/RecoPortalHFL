package com.lichfl.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.lichfl.dao.IRecoCustomRepo;
import com.lichfl.dao.RecoConfigRepo;
import com.lichfl.dao.RecoMatchRepo;
import com.lichfl.dao.RecoReportDao;
import com.lichfl.entity.RecoConfig;
import com.lichfl.entity.ReportResponse;
import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
import com.lichfl.model.ReportParam;
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
	IRecoCustomRepo recoCustomRepo;

	@Autowired
	RecoReportDao recoReportDao;

	/*
	 * @Value("${reco.matchType}") String matchType;
	 */

	@Autowired
	CustomStringUtil extractMessage;

	@Override
	public List<BookDto> fetchBookResults(RecoFilter recoFilter) throws Exception {
		List<BookDto> resList = null;
		/********* add quotes to paymodes after fetching all paymodes *******/

		try {
			resList = recoCustomRepo.fetchBookResults(recoFilter);
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

	@Override
	public int submitReport(ReportParam reportParam) throws Exception {

		int reportId = recoCustomRepo.submitReport(reportParam);

		return reportId;
	}

	/*
	 * @Override public List<ReportResponse> getReportRecords(int reportId) throws
	 * Exception {
	 * 
	 * String str = String.valueOf(reportId); log.info("hrfChildRepId ::" + str);
	 * 
	 * //Thread.sleep(2000);
	 * 
	 * List<ReportResponse> respList =
	 * recoReportDao.findByHrfChildRepIdStartsWith(str);
	 * log.info("respList.size() ::" + respList.size()); if (respList.size() < 1) {
	 * throw new Exception("Record is not present"); } return respList;
	 * 
	 * }
	 */

	@Override
	public List<ReportResponse> getReportFiles(String bankCode) throws Exception {

		List<ReportResponse> respList = recoReportDao.findByHrfBankCode(bankCode);
		if (respList.size() < 1) {
			throw new Exception("Record is not present");
		}
		return respList;
	}

}
