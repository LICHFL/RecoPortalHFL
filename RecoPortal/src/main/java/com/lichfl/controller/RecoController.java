package com.lichfl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lichfl.model.BookDto;
import com.lichfl.service.RecoService;

@Controller
public class RecoController {

	@GetMapping("/")
	public String home() {
		System.out.println("Home Page");

		return "home";
	}

	@Autowired
	RecoService recoService;

	// @PostMapping("/main")
	@GetMapping("/main")
	// public String home(String glCode, String fromDate, String toDate, String
	// catg, Map<String, Object> model) {
	public String home(Model model) {
		List<BookDto> bookDtoList = null;

		String glCode = "LUHDFCCMS1";
		String fromDate = "01-APR-2023";
		String toDate = "30-JUN-2023";
		String catg = "O";

		try {
			bookDtoList = recoService.fetchBookResults(glCode, fromDate, toDate, catg);
		} catch (Exception e) {
			e.printStackTrace();

		}
		// bookDtoList.forEach(System.out::println);
		model.addAttribute("bookDtoList", bookDtoList);

		return "main";
	}

}
