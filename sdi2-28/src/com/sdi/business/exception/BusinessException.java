package com.sdi.business.exception;

/**
 * Represent any system exception that prevents the business layer to get its
 * work done.
 *  The caller to any method that throws this exception it is supposed 
 *  to do anything as it represent far problems.
 *  
 */
public class BusinessException extends RuntimeException {

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = 1058931814311702510L;
}
