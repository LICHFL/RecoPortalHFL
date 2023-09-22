package com.lichfl.serviceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractMessage {

	String extractErrorMessage(String ErrMsg) {

        Pattern pattern = Pattern.compile("ORA-20001: (.*?) ERR5:", Pattern.DOTALL);


		Matcher matcher = pattern.matcher(ErrMsg);

		if (matcher.find()) {
			String extractedText = matcher.group(1);
            extractedText = extractedText.replaceAll("\\s*<EOL>\\s*", " ");


			return extractedText;
		} else {
			return ErrMsg;
		}

	}
}
