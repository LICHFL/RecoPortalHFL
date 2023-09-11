package com.lichfl.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
import com.lichfl.service.RecoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RecoController {

	@Autowired
	private Gson gson;

	@GetMapping("/")
	public String home() {
		System.out.println("Home Page");

		return "home";
	}

	@Autowired
	RecoService recoService;

	@GetMapping("/mainPage")
	public String mainPageLoad() {
		return "main";
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
