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

	// @Column(name = "match")
	private String match;

//	@Column(name = "tranCode")
	private String tranCode;

	// @Column(name = "docNo")
	private String docNo;

//	@Column(name = "docDate")
	private String docDate;

//	@Column(name = "chequeNo")
	private String chequeNo;

	// @Column(name = "pMode")
	private String payMode;

	// @Column(name = "valueDate")
	private String valueDate;

	// @Column(name = "micrCode")
	private String micrCode;

	// @Column(name = "loanNo")
	private String loanNo;

	private String receiptNo;

	// @Column(name = "orgAmnt")
	private String orgAmnt;

	// @Column(name = "unadjAmnt")
	private String unadjAmnt;

	// @Column(name = "drCr")
	private String drCr;

}
