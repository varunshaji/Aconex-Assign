package com.vss.phnen.phnbook;

public class PhoneBookException extends Exception {


	public static String FAILED_OPEN_PHONEBOOK="Failed to open phone book files";
	
	public static String INVALID_PHONE_NUMBER_PHONEBOOK="Inavlid phonenumber in phone book :";

	public PhoneBookException(String msg,Exception ex) {
		super(msg, ex);
	}
	
	public PhoneBookException(String msg) {
		super(msg);
	}
	

}
