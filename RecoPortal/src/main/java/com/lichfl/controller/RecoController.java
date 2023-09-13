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

import com.google.gson.Gson;
import com.lichfl.entity.BrsUserDetails;
import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
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
	private BrsUserService brsUserService;

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
	public String mainPage() {

		System.out.println("RecoController.mainPageLoad()");
		return "main";
	}

	@PostMapping("/main")
	public String homePage(@AuthenticationPrincipal RecoUserDetails userDetails, Map<String, Object> model) {

		String username = userDetails.getUsername();
		Optional<BrsUserDetails> brsUser = null;
		if (username != null) {
			brsUser = brsUserService.getUserDetails(username);
			log.info("brsUser ::" + brsUser);
		}

		List<String> partnerBankList = Arrays.stream(brsUser.get().getBankcode().split(","))
				.collect(Collectors.toList());

		log.info("partnerBankList ::" + partnerBankList);

		model.put("userData", brsUser);
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
	public String home(RecoFilter recoFilter, Map<String, Object> model) {

		log.info("recoFilter::" + recoFilter);

		List<BookDto> bookDtoList = null;

		try {
			bookDtoList = recoService.fetchBookResults(recoFilter.getBankCode(), recoFilter.getDatetimepickerFrom(),
					recoFilter.getDatetimepickerTo(), recoFilter.getMatchingType(), recoFilter.getTranType());
		} catch (Exception e) {
			e.printStackTrace();

		}
		model.put("bookDtoList", bookDtoList);
		return "matchingTable";

	}

}
