package com.example.request;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InQueryRequest {
	private List<String> descriptions;
}
