package com.lichfl.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
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
import com.lichfl.model.ReportResponseDto;
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

	@Override
	public List<ReportResponseDto> getReportFiles(String bankCode) throws Exception {

		List<ReportResponse> respList = recoReportDao.findByHrfBankCodeStartsWith(bankCode);
		if (respList.size() < 1) {
			throw new Exception("Record is not present");
		}

		// copy the list from entity to DTO
		List<ReportResponseDto> reportResponseDtoList = respList.stream().map(reportResponse -> {
			ReportResponseDto dto = new ReportResponseDto();

			BeanUtils.copyProperties(reportResponse, dto);
			return dto;

		}).collect(Collectors.toList());

		BeanUtils.copyProperties(bankCode, respList);

		// String filePath = "\\10.0.1.199"+"\\"+bankCode+"\\"+bankCode+"\\"+"BRS"+"\\";

		// sort the list and remove the timestamp
		List<ReportResponseDto> sortedAndModifiedList = reportResponseDtoList.stream()
				.filter(report -> report.getHrfReportServerPath() != null)
				.map(report -> {
					// Create a new ReportResponse with modified hrfSDt
					ReportResponseDto repResp = new ReportResponseDto();
					repResp.setHrfRepId(report.getHrfRepId());
					repResp.setHrfChildRepId(report.getHrfChildRepId());
					repResp.setHrfBankCode(report.getHrfBankCode());
					repResp.setHrfSDt(report.getHrfSDt().substring(0, 10)); // Apply substring here
					repResp.setHrfEDt(report.getHrfEDt().substring(0, 10));// Apply substring here
					repResp.setHrfReportFileName(report.getHrfReportFileName());
					repResp.setHrfReportRunStart(report.getHrfReportRunStart().substring(0, 10));// Apply substring here
					repResp.setHrfReportRunMsg(report.getHrfReportRunMsg());
					repResp.setStatus("Generated");
					;
					// repResp.setHrfReportServerPath(filePath+report.getHrfReportFileName());

					repResp.setHrfReportServerPath(report.getHrfReportServerPath());
					return repResp;
				})
				.sorted(Comparator.comparingInt(ReportResponseDto::getHrfRepId).reversed())
				.limit(50)
				.collect(Collectors.toList());
		return sortedAndModifiedList;
	}

	@Override
	public List<BookDto> getUnmatchRecords(RecoFilter recoFilter) throws Exception {
	
		List<BookDto> resList = null;
		/********* add quotes to paymodes after fetching all paymodes *******/

		try {
			resList = recoCustomRepo.getUnmatchRecords(recoFilter);
			return resList;
		} catch (Exception e) {
			throw new Exception("No Results found for the provided inputs");
		}
		
	}

}
