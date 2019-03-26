package com.booway.Exception;

public class RestClientException extends RuntimeException {

	private static final long serialVersionUID = 1035304966185162022L;
	
	public RestClientException(String msg) {
		super(msg);
	}

}
