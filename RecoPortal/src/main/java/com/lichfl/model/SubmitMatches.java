package com.lichfl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitMatches {

	private String matchkey;
	private String broKey;
	private String amount;

}
