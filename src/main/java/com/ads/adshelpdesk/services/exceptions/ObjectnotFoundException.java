package com.ads.adshelpdesk.services.exceptions;

public class ObjectnotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectnotFoundException(String message, Throwable cause) { // tras a causa da exceção
		super(message, cause);
	}

	public ObjectnotFoundException(String message) { // menssagem
		super(message);
	}
	
	

}
