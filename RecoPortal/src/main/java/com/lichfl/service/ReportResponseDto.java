package com.lichfl.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {

	private int hrfRepId;
	private String hrfReportFileName;
	private String hrfReportRunStart;
	private String hrfReportRunMsg;
	private String path;

}
