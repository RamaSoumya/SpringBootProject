package com.example.response;

import java.util.Date;

import com.example.request.ErrorDetails;

import lombok.Data;

@Data
public class ErrorHandlerResponse {

	private Date timeStamp;
	private String message;
	private String details;
	
	public ErrorHandlerResponse(ErrorDetails errorDetails) {
		this.timeStamp = errorDetails.getTimeStamp();
		this.message = errorDetails.getMessage();
		this.details = errorDetails.getDetails();
	}
}
