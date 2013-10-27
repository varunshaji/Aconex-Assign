package com.vss.phnen.dictionary;

public class DictionaryException extends Exception {
	
	public static String FAILED_OPEN_DICT="Failed to open dictionary file";

	public DictionaryException(String msg,Exception ex) {
		// TODO Auto-generated constructor stub
		super(msg, ex);
	}

}
