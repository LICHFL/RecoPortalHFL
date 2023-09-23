package com.lichfl.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CustomStringUtil {

	public String extractErrorMessage(String ErrMsg) {

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

	public String addQuoteToStringValue(String msg) {
		String[] values = msg.split(",");

		// Add a single quote before and after each value, then join them back together
		// with commas
		String modifiedString = Arrays.stream(values).map(value -> "'" + value + "'").collect(Collectors.joining(","));
		
		return modifiedString;
	}
}
