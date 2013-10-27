package com.vss.phnen;

public class PhnEnConfig {

	private String phoneNumber;
	
	public static String defaultDictionaryPath = "defaultDict.dict";
	
	/**
	 * Default path for the Dictionary file.
	 */
	private String dictionaryPath;
	
	private String phoneBookPath;
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDictionaryPath() {
		return dictionaryPath;
	}

	public void setDictionaryPath(String dictionaryPath) {
		this.dictionaryPath = dictionaryPath;
	}

	public String getPhoneBookPath() {
		return phoneBookPath;
	}

	public void setPhoneBookPath(String phoneBookPath) {
		this.phoneBookPath = phoneBookPath;
	}

}
