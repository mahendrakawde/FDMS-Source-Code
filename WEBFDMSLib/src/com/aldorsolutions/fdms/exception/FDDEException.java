package com.aldorsolutions.fdms.exception;

import com.aldorsolutions.webservice.xsd.fdde.service.ErrorCodeType;

public class FDDEException extends RuntimeException {
	private ErrorCodeType errorCode;

	public FDDEException(String message, Throwable cause) {
		super(message, cause);
		errorCode = ErrorCodeType.Others;
	}

	public FDDEException(String message) {
		super(message);
		errorCode = ErrorCodeType.Others;
	}

	public FDDEException(Throwable cause) {
		super(cause);
		errorCode = ErrorCodeType.Others;
	}
	
	public FDDEException(ErrorCodeType code, String message){
		super(message);
		errorCode = code;
	}

	public ErrorCodeType getErrorCode() {
		return errorCode;
	}
	
}
