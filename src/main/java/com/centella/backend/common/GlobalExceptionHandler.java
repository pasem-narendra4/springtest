package com.centella.backend.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<APIResponse> handleException(Exception e) {
		APIResponse apiResponse = new APIResponse();
		apiResponse.setError("Oops.. Something went wrong");
		apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<APIResponse> handleAccessDeniedException(AccessDeniedException e) {
		APIResponse apiResponse = new APIResponse();
		apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		
		return ResponseEntity.status(401).body(apiResponse);
	}
}
