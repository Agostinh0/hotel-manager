package com.cavalcanti.hotelmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCheckOutDateTimeException extends RuntimeException{
	
	private static final long serialVersionUID = -3559385722798249758L;

	public InvalidCheckOutDateTimeException(String message) {
		super(message);
	}
}
