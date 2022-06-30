package com.example.exceptions;

import org.springframework.http.HttpStatus;

public class BlogApiException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
	
	public BlogApiException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public BlogApiException(String message, HttpStatus status, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
}
