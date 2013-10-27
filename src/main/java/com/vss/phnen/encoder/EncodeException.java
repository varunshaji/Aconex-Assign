package com.vss.phnen.encoder;

public class EncodeException extends Exception {
	
	public static String CONSECUTIVE_DIGIT_NO_CODE = "Two consecutive digits with no codes. Cannot be encoded with current dictionary";
	
	public EncodeException(String msg,Throwable ex) {
		super(msg,ex);
	}
	
	public EncodeException(String msg) {
		super(msg);
	}

}
