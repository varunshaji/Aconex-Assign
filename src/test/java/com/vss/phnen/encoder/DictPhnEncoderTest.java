package com.vss.phnen.encoder;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.vss.phnen.PhnEnConfig;
import com.vss.phnen.dictionary.Dictionary;
import com.vss.phnen.dictionary.DictionaryException;

public class DictPhnEncoderTest {

	@Test
	public void testDictPhnEncoder() {
		
		PhnEnConfig config = new PhnEnConfig();
		config.setDictionaryPath("defaultDict.dict");
		Dictionary dictionary = null;
		try {
			dictionary = new Dictionary(config);
		} catch (DictionaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PhnEncoder phoneEncoder = new DictPhnEncoder(dictionary);
		List<String> resultEncodings = null;
		
		try {
			resultEncodings= phoneEncoder.encodePhoneNum("2255.63");
			//resultEncodings= phoneEncoder.encodePhoneNum("9895440710");
		} catch (EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(checkValue(resultEncodings));
		
	}
	private boolean checkValue(List<String>encoding){
		boolean result = false;
		for(String encodeString:encoding){
			if(encodeString.equals("CALL-ME"))result=true;
		}
		return result;
		
	}
	
	/*@Test
	public void testDictPhnEncoderValidation1() {
		
		PhnEnConfig config = new PhnEnConfig();
		config.setDictionaryPath("defaultDict.dict");
		Dictionary dictionary = null;
		try {
			dictionary = new Dictionary(config);
		} catch (DictionaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PhnEncoder phoneEncoder = new DictPhnEncoder(dictionary);
		List<String> resultEncodings = null;
		
		try {
			//resultEncodings= phoneEncoder.encodePhoneNum("1155.63");
			//resultEncodings= phoneEncoder.encodePhoneNum("2151.63");
			//resultEncodings= phoneEncoder.encodePhoneNum("151");
			resultEncodings= phoneEncoder.encodePhoneNum("1.51.5123.1");
		} catch (EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assertNull(resultEncodings);
		assertNotNull(resultEncodings);
		
	}*/

	/*@Test
	public void testEncodePhoneNum() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidate() {
		fail("Not yet implemented");
	}*/

}
