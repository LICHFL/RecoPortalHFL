package com.lichfl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lichfl.entity.BrsUserDetails;
import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
import com.lichfl.model.SubmitMatches;
import com.lichfl.security.RecoUserDetails;
import com.lichfl.service.BrsUserService;
import com.lichfl.service.RecoService;

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

	private static final String RECO_CODE = "paymode";

	@GetMapping({ "/", "/loginPage" })
	public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {

		System.out.println("RecoController.loginPage()");

		if (error != null) {
			model.addAttribute("error", true);
			System.out.println(error);

		}
		return "loginPage";
	}

	@GetMapping("/main")
	public String mainPage(Model model) {

		log.info("RecoController.mainPageLoad() : Getmaping");

		String result = (String) model.getAttribute("result");
		log.info("result :" + result);

		model.addAttribute("result", "result");
		return "main";
	}

	@PostMapping("/main")
	public String homePage(@AuthenticationPrincipal RecoUserDetails userDetails, Map<String, Object> model)
			throws Exception {

		String username = userDetails.getUsername();
		Optional<BrsUserDetails> brsUser = null;
		if (username != null) {
			brsUser = brsUserService.getUserDetails(username);
			log.info("brsUser ::" + brsUser);
		}

		List<String> partnerBankList = Arrays.stream(brsUser.get().getBankcode().split(","))
				.collect(Collectors.toList());

		String paymodes = recoService.getPayModes(RECO_CODE);

		List<String> payModeList = Arrays.stream(paymodes.split(",")).collect(Collectors.toList());
		log.info("payModeList ::" + payModeList);

		// List<String>payModes= Arrays.stream(null)

		log.info("partnerBankList ::" + partnerBankList);

		model.put("userData", brsUser.get());
		model.put("payModeList", payModeList);
		model.put("partnerBankList", partnerBankList);

		return "main";
	}

	@GetMapping("/error")
	public String authenticationFailure(Model model, HttpServletRequest request) {

		System.out.println("RecoController.authenticationFailure()");
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
	public String submitMatchingKeys(@RequestParam(value = "brokey") String jsonReq,
			@AuthenticationPrincipal RecoUserDetails userDetails, RedirectAttributes redirectAttributes)
			throws JsonMappingException, JsonProcessingException {

		log.info("submitMatches :: " + jsonReq);

		List<SubmitMatches> submitMatchesList = Arrays.asList(objectMapper.readValue(jsonReq, SubmitMatches[].class));

		System.out.println("submitMatchesList ::" + submitMatchesList.toString());

		String username = userDetails.getUsername();
		double amount = 1000;

		String result = recoService.submitMatchingKeys(submitMatchesList, username);
		redirectAttributes.addFlashAttribute("result", result);

		return "redirect:/main";

	}

}
