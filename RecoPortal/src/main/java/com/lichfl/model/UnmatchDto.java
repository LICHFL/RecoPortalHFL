package com.lichfl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnmatchDto {

	private String unmatchingType;
	private String bankCodeUnmatch;
	private String datetimepickerFromUnmatch;
	private String datetimepickerToUnmatch;
	private String tranUnmatchType;
	private String pModeUnmatch;

}
