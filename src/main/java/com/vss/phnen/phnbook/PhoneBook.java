package com.vss.phnen.phnbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vss.phnen.PhnEnConfig;
import com.vss.phnen.dictionary.Dictionary;
import com.vss.phnen.dictionary.DictionaryException;

/**
 * 
 * Holds the list of phone numbers which need to be encoded. Here the Phonebook open the files and read the numbers into memory.
 * 
 * @author shajivarun@yahoo.co,in
 * @since 26-10-2013
 *
 */

public class PhoneBook {
	
		
	List<String> phoneNumbers = null;
	
	private static Logger logger = Logger.getLogger(PhoneBook.class.getName());
	
	public PhoneBook (PhnEnConfig config) throws  PhoneBookException{
		phoneNumbers = new ArrayList();
		if(config.getPhoneBookPath()!=null){
			initialize(config.getPhoneBookPath());
		}else{
			logger.log(Level.INFO, "No phonebook files mentioned.Need to feed the number from console");
		}
		
		
	}
	
	/**
	 * Initializes the phonebook by reading from the phone book file as
	 * mentioned from the command line using the -d argument.
	 * 
	 * @param path
	 * @throws PhoneBookException
	 */
	private void initialize(String path) throws  PhoneBookException{
		BufferedReader bufReader = null;
		 
		try {
 
			String phnNumber; 
			bufReader = new BufferedReader(new FileReader(path)); 
			while ((phnNumber = bufReader.readLine()) != null) {
				String number = phnNumber.trim();
				phoneNumbers.add(validateNumber(number));
			}
 
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error while reading phoneBook file : "+path+" .Check if  filepath is correct!");
			throw new PhoneBookException(PhoneBookException.FAILED_OPEN_PHONEBOOK, e);
		} finally {
			try {
				if (bufReader != null)bufReader.close();
			} catch (IOException ex) {
				logger.log(Level.SEVERE,"Error while reading phoneBook file : "+path);
				throw new PhoneBookException(PhoneBookException.FAILED_OPEN_PHONEBOOK, ex);	
			}
		}
	 }
	
	/**
	 * Does validation upon the passed in phone numbers
	 * @param number
	 * @return
	 * @throws PhoneBookException
	 */
	private String validateNumber(String number) throws PhoneBookException{
		if(number.length()>0){
			number.replace("-","");
			try{
				Double.valueOf(number);
			}catch(NumberFormatException ex){
				throw new PhoneBookException(PhoneBookException.INVALID_PHONE_NUMBER_PHONEBOOK+"'"+number+"'");
			}
			
		}else{
			throw new PhoneBookException(PhoneBookException.INVALID_PHONE_NUMBER_PHONEBOOK+"'"+number+"'");
		}
		return number;
		
	}
	
	/**
	 * Method used to add phone numbers.DFoes validation beofre adding to the PhoneBook.
	 */
	public void addPhoneNumbers(List<String> numbers) throws PhoneBookException{
		for(String number:numbers){
			validateNumber(number);
		}
		phoneNumbers.addAll(numbers);
	}
	

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}



	
}
