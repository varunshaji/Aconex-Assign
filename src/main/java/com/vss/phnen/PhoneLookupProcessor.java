package com.vss.phnen;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vss.phnen.dictionary.Dictionary;
import com.vss.phnen.dictionary.DictionaryException;
import com.vss.phnen.encoder.DictPhnEncoder;
import com.vss.phnen.encoder.EncodeException;
import com.vss.phnen.encoder.PhnEncoder;
import com.vss.phnen.phnbook.PhoneBook;
import com.vss.phnen.phnbook.PhoneBookException;

/**
 * 
 * @author shajivarun@yahoo.co.in
 * @since 26-10-2013
 *
 */

public class PhoneLookupProcessor {
	
	private static Logger logger = Logger.getLogger(PhoneLookupProcessor.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(String arg:args)
		logger.log(Level.FINEST,"arguments :"+arg);
		//Call the CmdLineInterpretor for interpreting the inputs
		
		List<String>  encodings = null;
		
		//Get config from the interpretor
		PhnEnConfig config=null;
		try {
			config = CmdLineInterpret.getConfig(args);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Please check the commandline arguments before executing the program.Error with the program arguments!");
		}
		
		
		if(config!=null){
			//Create dictionary passing the configuraition
			Dictionary dictionary=null;
			try {
				dictionary = new Dictionary(config);
			} catch (DictionaryException e) {
				// TODO Auto-generated catch block
				logger.log(Level.SEVERE, "Couldnt load the Dictionary for the encoder!.");
			}
			
			//Create phonebook passing the configuration
			PhoneBook phoneBook=null;
			try {
				phoneBook = new PhoneBook(config);
			} catch (PhoneBookException e1) {
				logger.log(Level.SEVERE, "Couldnt load phone numbers.",e1);
			}
			if(phoneBook!=null&&dictionary!=null){				
			
				PhnEncoder phoneEncoder = new DictPhnEncoder(dictionary);
				
				String outcome = "";
				do{
					//if no phone book files read from console.
					while(phoneBook.getPhoneNumbers().size()==0){
						try {
							phoneBook.addPhoneNumbers(CmdLineInterpret.readPhoneNumbers());
						} catch (PhoneBookException e) {
							logger.log(Level.SEVERE, "Invalid phone number!!");
						}
					}
					
					//Iterate over phn numbers
					for(String phoneNumber:phoneBook.getPhoneNumbers()){			
						//encode phn numbers
						try {
							encodings = phoneEncoder.encodePhoneNum(phoneNumber);
							CmdLineInterpret.displayEncodings(phoneNumber,encodings);
						} catch (EncodeException e) {
							//Skipping the encode exception
						}				
					  }
					outcome = CmdLineInterpret.getInputFrmUser("Enter 'E'xit | 'R'etry ");
					phoneBook.getPhoneNumbers().clear();
				}while(outcome.equals("R"));
				
			}
		}

	}
	
	

}
