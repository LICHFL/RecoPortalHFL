package com.lichfl.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
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

	/*
	 * @GetMapping({"/"}) public String home() { System.out.println("Home Page");
	 * 
	 * return "home"; }
	 */

	@GetMapping({ "/", "/loginPage" })
	public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {

		System.out.println("RecoController.loginPage()");

		if (error != null) {
			model.addAttribute("error", true);
			System.out.println(error);

		}
		return "loginPage"; // Return the name of your login view template
	}

	@GetMapping("/main")
	public String mainPage() {

		System.out.println("RecoController.mainPageLoad()");
		return "main";
	}

	@PostMapping("/main")
	public String homePage() {
		System.out.println("RecoController.homePage()");
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

	/*
	 * @PostMapping("/successPage") public String success() {
	 * 
	 * System.out.println("RecoController.success()"); return "main"; }
	 */

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
