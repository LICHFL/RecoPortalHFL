package com.lichfl.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RecoException {
	
	
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public Map<String,String> handleRuntimeExceptions(Exception ex)
	{
		Map<String,String> error = new HashMap<>();
		error.put("message", ex.getMessage());;
		error.put("error", "Y");
		return error;
		
	}
	

}
