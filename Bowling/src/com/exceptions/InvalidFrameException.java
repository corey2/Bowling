package com.exceptions;

public class InvalidFrameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFrameException(String errorMessage) {
		super(errorMessage);
	}
	
}