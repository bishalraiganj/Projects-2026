package com.bishaladhikary.organization_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter @Setter
public class ErrorResponseDTO {
	private String timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	private Map<String, Object> details;

	public ErrorResponseDTO(String timestamp, int status, String error, String message, String path, Map<String, Object> details) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.details = details;
	}


}

