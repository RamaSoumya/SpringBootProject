package com.example.request;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CreateErrorDetailsRequest {

	private Date timeStamp;
	private String message;
	private String details;
	private HttpStatus status;
	
}
