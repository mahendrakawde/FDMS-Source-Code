package com.aldorsolutions.webservice.util;

public class InvalidDataException extends Exception {
	
	public InvalidDataException(){}
	
	public InvalidDataException(String msg){
		super(msg);
	}
}
