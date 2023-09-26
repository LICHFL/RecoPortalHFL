package com.lichfl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportParam {

	private String reportType;
	private String bankCodeRept;
	private String datetimepickerFromRept;
	private String datetimepickerToRept;

}
