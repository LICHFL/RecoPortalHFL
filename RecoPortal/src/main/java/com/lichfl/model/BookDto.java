package com.lichfl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

	private String match;
	private String tranCode;
	private String docNo;
	private String docDate;
	private String chequeNo;
	private String payMode;
	private String valueDate;
	private String micrCode;
	private String loanNo;
	private String receiptNo;
	private String orgAmnt;
	private String unadjAmnt;
	private String drCr;
	private String tranType;
}
