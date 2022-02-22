package com.ads.adshelpdesk.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegrityViolationException(String message, Throwable cause) { // tras a causa da exceção
		super(message, cause);
	}

	public DataIntegrityViolationException(String message) { // menssagem
		super(message);
	}
	
	

}
