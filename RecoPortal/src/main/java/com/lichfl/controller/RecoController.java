package com.lichfl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lichfl.entity.BrsUserDetails;
import com.lichfl.exception.RespMessage;
import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
import com.lichfl.model.ReportParam;
import com.lichfl.model.SubmitMatches;
import com.lichfl.model.UnmatchDto;
import com.lichfl.model.UnmatchDto;
import com.lichfl.security.RecoUserDetails;
import com.lichfl.service.BrsUserService;
import com.lichfl.service.RecoService;
import com.lichfl.util.ApplicationConstant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RecoController {

	@Autowired
	RecoService recoService;

	@Autowired
	private Gson gson;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private BrsUserService brsUserService;


	@GetMapping({ "/login", "/" })
	public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {

		if (error != null) {
			model.addAttribute("error", true);
			System.out.println(error);

		}
		return "loginPage";
	}

	@GetMapping("/main")
	public String homePage(@AuthenticationPrincipal RecoUserDetails userDetails, Map<String, Object> model
	// @ModelAttribute("respMessage") RespMessage respMessage
	)

			throws Exception {


		String username = userDetails.getUsername();
		Optional<BrsUserDetails> brsUser = null;
		if (username != null) {
			brsUser = brsUserService.getUserDetails(username);
			log.info("brsUser ::" + brsUser);
		}

		List<String> partnerBankList = Arrays.stream(brsUser.get().getBankcode().split(","))
				 .filter(s -> !s.contains("CMS2"))
				.collect(Collectors.toList());

		String paymodes = recoService.getPayModes(ApplicationConstant.PAYMODE);

		List<String> payModeList = Arrays.stream(paymodes.split(",")).collect(Collectors.toList());
		log.info("payModeList ::" + payModeList);

		log.info("partnerBankList ::" + partnerBankList);

		model.put("userData", brsUser.get());
		model.put("payModeList", payModeList);
		model.put("partnerBankList", partnerBankList);
		// model.put("respMessage", respMessage);

		return "main";
	}

	@GetMapping("/error")
	public String authenticationFailure(Model model, HttpServletRequest request) {

		return "error";
	}

	@GetMapping(value = "/sessionout")
	public String sessionOut(Model model) {
		return "loginPage";
	}

	@PostMapping("/getBookRec")
	public String loadMatchScreen(RecoFilter recoFilter, Map<String, Object> model) {

		log.info("recoFilter::" + recoFilter);

		List<BookDto> bookDtoList = null;

		try {

			bookDtoList = recoService.fetchBookResults(recoFilter);
			// bookDtoList.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();

		}
		model.put("bookDtoList", bookDtoList);
		return "matchingTable";

	}

	@PostMapping("/getFreezeRecords")
	public String loadFreezeScreen(RecoFilter recoFilter, Map<String, Object> model) {

		log.info("recoFilter::" + recoFilter);

		List<BookDto> bookDtoList = null;

		try {
			bookDtoList = recoService.fetchBookResults(recoFilter);

		} catch (Exception e) {
			e.printStackTrace();

		}
		model.put("bookDtoList", bookDtoList);
		return "freezeTable";

	}

	@PostMapping("/submitMatchData")
	@ResponseBody
	public RespMessage submitMatchingKeys(@RequestParam(value = "brokey") String jsonReq,
			@AuthenticationPrincipal RecoUserDetails userDetails, RedirectAttributes redirectAttributes)
			throws JsonMappingException, JsonProcessingException {

		log.info("submitMatches :: " + jsonReq);

		List<SubmitMatches> submitMatchesList = Arrays.asList(objectMapper.readValue(jsonReq, SubmitMatches[].class));

		String username = userDetails.getUsername();

		String result = recoService.submitMatchingKeys(submitMatchesList, username);

		RespMessage respMessage = RespMessage.builder().message(result).errorStatus(false).status(HttpStatus.CREATED)
				.build();
		return respMessage;

		// redirectAttributes.addFlashAttribute("respMessage", respMessage);

		// return "redirect:/main";

	}

	@PostMapping("/submitReport")
	@ResponseBody
//	public Map<String, Object> submitReport(ReportParam reportParam, Map<String, Object> model) throws Exception {
	public String submitReport(ReportParam reportParam, Map<String, Object> model) throws Exception {
		log.info("reportParam" + reportParam);

		int reportId = recoService.submitReport(reportParam);
		log.info("reportId ::" + reportId);
		return String.valueOf(reportId);

	}

	@GetMapping("/getReportFiles")
	public String getReportFiles(Map<String, Object> model, @AuthenticationPrincipal RecoUserDetails userDetails)
			throws Exception {

		Optional<BrsUserDetails> userDetails2 = brsUserService.getUserDetails(userDetails.getUsername());
		// userDetails2.get().getBankcode();
		// bankCode = "LUHDFCCMS1";

		log.info("userDetails2.get().getBankcode() ::" + userDetails2.get().getBankcode());

		List<com.lichfl.model.ReportResponseDto> reportList = recoService
				.getReportFiles(userDetails2.get().getUsrBranchCode());

		// reportList.forEach(System.out::println);

		log.info("repResponse ::" + reportList);

		model.put("reportList", reportList);
		return "reportTable";
	}

	@PostMapping("/getUnmatchRecords")
	public String getUnmatchRecords(UnmatchDto unmatchDto, Map<String, Object> model) {
		
		System.out.println("RecoController.getUnmatchRecords()");

		log.info("unmatchDto::" + unmatchDto);

		List<BookDto> bookDtoList = null;

		try {

			bookDtoList = recoService.getUnmatchRecords(unmatchDto);
			// bookDtoList.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();

		}
		model.put("bookDtoList", bookDtoList);
		return "unmatchingTable";

	}

}
