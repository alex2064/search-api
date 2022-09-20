package com.api.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.common.response.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(InvalidInputException.class)
	protected ResponseEntity<ErrorResponse> invalidInputException(InvalidInputException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
	}
}
