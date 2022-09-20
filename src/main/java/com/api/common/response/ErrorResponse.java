package com.api.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private String errorType;
	private String message;
	
	public ErrorResponse(String message) {
		this.errorType = MessageUtils.invalidArgument;
		this.message = message;
	}
}
