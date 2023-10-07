package com.lichfl.entity;

import jakarta.persistence.Column;
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
	private String hrfChildRepId;
	private String hrfBankCode;
	@Column(name="HRF_S_DT")
	private String hrfSDt;
	@Column(name="HRF_E_DT")
	private String hrfEDt;
	private String hrfReportFileName;
	private String hrfReportRunStart;
	private String hrfReportRunMsg;
	private String hrfReportServerPath;
}
