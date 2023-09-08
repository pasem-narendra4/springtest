package com.centella.backend.common;

public class AccessDeniedException extends RuntimeException{
	
	public AccessDeniedException(String message){
		super(message);
	}
}
