package com.lichfl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecoFilter {

	private String matchingType;
	private String bankCode;
	private String datetimepickerFrom;
	private String datetimepickerTo;
	private String tranType;
	private String desc;
	private String orderBy;
	private String pMode;
	private String chqNo;
}
