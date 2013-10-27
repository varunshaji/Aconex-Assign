package com.vss.phnen;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CmdLineInterpretTest {

	@Test
	public void testReadPhoneNumbers() {
		//List<String> phoneNumbers = CmdLineInterpret.readPhoneNumbers();
		assert(true);
	}
	
	@Test
	public void testDisplayEncodings() {
		String phoneNumber = "987981-453";
		String[] numbers = new String[] {"QWEQWE-123","ADASDA-QWE","QWQWER-rty","ASDASD-SSA"};
		CmdLineInterpret.displayEncodings(phoneNumber, Arrays.asList(numbers));
		String phoneNumber2 = "987981-453";
		String[] numbers2 = new String[] {"QWEQWE-123","ADASDA-QWE","QWQWER-rty","ASDASD-SSA"};
		CmdLineInterpret.displayEncodings(phoneNumber2, Arrays.asList(numbers2));
		assert(true);
	}
	
	@Test
	public void testgetConfig1() {
		PhnEnConfig config = null;
		try {
			 config = CmdLineInterpret.getConfig(new String[]{"PhoneBook.phn","-d","defaultDict.dict"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(config.getPhoneBookPath());
		assertNotNull(config.getDictionaryPath());
		
	}
	
	@Test
	public void testgetConfig2() {
		PhnEnConfig config = null;
		try {
			config = CmdLineInterpret.getConfig(new String[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(config);
	}
	
	@Test
	public void testgetConfig3() {
		PhnEnConfig config = null;
		try {
			config = CmdLineInterpret.getConfig(new String[]{"PhoneBook.phn"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(config.getPhoneBookPath());
	}
	
	@Test
	public void testgetConfig4() {
		PhnEnConfig config = null;
		try {
			config = CmdLineInterpret.getConfig(new String[]{"-d","defaultDict.dict"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(config.getDictionaryPath());
	}

}
