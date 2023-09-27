package com.lichfl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {

	private int hrfRepId;
	private String hrfChildRepId;
	private String hrfBankCode;
	private String hrfSDt;
	private String hrfEDt;
	private String hrfReportFileName;
	private String hrfReportRunStart;
	private String hrfReportRunMsg;
	private String hrfReportServerPath;

}
