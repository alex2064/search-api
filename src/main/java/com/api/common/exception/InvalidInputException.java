package com.api.common.exception;

public class InvalidInputException extends RuntimeException {
	
	private static final long serialVersionUID = -15648451110506L;
	
	public InvalidInputException() {
		super("잘못 입력하였습니다.");
	}
	
	public InvalidInputException(String message) {
		super(message);
	}
}
