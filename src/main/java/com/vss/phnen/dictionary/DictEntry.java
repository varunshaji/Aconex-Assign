package com.vss.phnen.dictionary;

/**
 * Each entry signifiea a entry in the dictionary consisting of an 
 * digit and possible encoding charaters allowed.
 * 
 * @author shajivarun@yahoo.co.in
 *
 */
public class DictEntry {
	
	/**
	 * Hold the phonenumber digit .
	 */
	private int phnNumDig;
	
	/**
	 * Hold the possible characters that can be used for encoding the respective phone number digit.
	 */
	private char[] encoderChars;
	
	public DictEntry(int phnNumDig, char[] encoderChars) {
		this.phnNumDig = phnNumDig;
		this.encoderChars = encoderChars;
	}
	
	public int getPhnNumDig() {
		return phnNumDig;
	}

	public char[] getEncoderChars() {
		return encoderChars;
	}

	

}
