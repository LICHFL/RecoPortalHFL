package com.lichfl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RecoException {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespMessage> handleRuntimeExceptions(Exception ex) {

		RespMessage respMessage = RespMessage.builder().message(ex.getMessage()).errorStatus(true)
				.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		return new ResponseEntity<>(respMessage, HttpStatus.INTERNAL_SERVER_ERROR);

	}


}
