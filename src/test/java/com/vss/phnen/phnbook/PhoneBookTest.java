package com.vss.phnen.phnbook;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vss.phnen.PhnEnConfig;
import com.vss.phnen.dictionary.Dictionary;
import com.vss.phnen.dictionary.DictionaryException;

public class PhoneBookTest {

	@Test
	public void testPhoneBook() {
		
		PhnEnConfig config = new PhnEnConfig();
		config.setPhoneBookPath("PhoneBook.phn");
		
		PhoneBook phoneBook = null;
		try {
			phoneBook = new PhoneBook(config);
		} catch (PhoneBookException e) {
			e.printStackTrace();
		}
		
		String[] actNumbers = new String[]{"2255.63","9895440710","61280385032","87961.98"};		
		assertArrayEquals(phoneBook.getPhoneNumbers().toArray(), actNumbers);
		
		
	}
	
   public void testPhoneBook2() {
		
		PhnEnConfig config = new PhnEnConfig();
		config.setPhoneBookPath("");
		
		PhoneBook phoneBook = null;
		try {
			phoneBook = new PhoneBook(config);
		} catch (PhoneBookException e) {
			e.printStackTrace();
		}
		
		assertNull(phoneBook);
		
		
	}

	/*@Test
	public void testGetPhoneNumbers() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPhoneNumbers() {
		fail("Not yet implemented");
	}*/

}
