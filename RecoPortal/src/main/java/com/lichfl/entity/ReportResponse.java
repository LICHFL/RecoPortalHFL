package com.lichfl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HFLBRS_REPORT_FE")
public class ReportResponse {

	@Id
	private int hrfRepId;
	private String hrfReportFileName;
	private String hrfReportRunStart;
	private String hrfReportRunMsg;
	private String hrfChildRepId;
	private String hrfBankCode;
	private String hrfReportServerPath;
	// private String path;
}
