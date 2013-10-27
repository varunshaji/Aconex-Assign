package com.vss.phnen.encoder;

import java.util.List;

public interface PhnEncoder {
	
	public List<String> encodePhoneNum(String phoneNumber) throws EncodeException;
	
	public List<String> validate(List<String> phoneNumbers);
	

}
