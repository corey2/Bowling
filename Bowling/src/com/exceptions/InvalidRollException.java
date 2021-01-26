package com.exceptions;

public class InvalidRollException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRollException(String errorMessage) {
		super(errorMessage);
	}
	
}
