package com.vss.phnen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vss.phnen.phnbook.PhoneBook;


/**
 * Handles all the Command and user interaction activities. The class provides static methods for getting inputs from
 * commandline as well as sidplaying to command prompt. It also creates the configuration object by parsing the incoming 
 * commandline arguments.
 * 
 * @author shajivarun@yahoo.co.in
 * @since 26-10-2013
 *
 */
public class CmdLineInterpret {
	
	private static Logger logger = Logger.getLogger(PhoneBook.class.getName());
	

	/**
	 * Reads a list of phone numbers from the command prompt.
	 * @return phoneNumbers
	 */
	public static List<String> readPhoneNumbers(){
		
		List<String> phoneNumberList = new ArrayList();		
		System.out.println("Enter the phone numbers you want to encode . Enter 'P' to start process");
		
		try{
			BufferedReader br = 
	                      new BufferedReader(new InputStreamReader(System.in));
	 
			String input;
	 
			while((input=br.readLine())!=null){
				if(input.equals("P"))break;
				phoneNumberList.add(input.trim());
			}
	 
		}catch(IOException io){
			io.printStackTrace();
		}	
		
		return phoneNumberList;
		
	}
	
	/**
	 * 
	 * Creates and return the Phone encoder configuration code by parsing
	 * the commandline argumnents.
	 * 
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static PhnEnConfig getConfig(String[] args) throws Exception{
		
		String filePath =null;
		
		String dictPath =null;
		
		PhnEnConfig config = new PhnEnConfig();
		
		if(args.length==1){
			logger.log(Level.FINEST,"Case 1");
			String arg = args[0];			
			filePath = arg;			
			config.setPhoneBookPath(filePath);	
		}	
		
		if(args.length==2){
			logger.log(Level.FINEST,"Case 2");
			String arg = args[0];
			if(arg.equals("-d")){
				dictPath = args[1];
				config.setDictionaryPath(dictPath);
			}else{
				filePath = arg;
				config.setPhoneBookPath(filePath);	
			}	
			
		}	
		
		if(args.length==3){
			logger.log(Level.FINEST,"Case 3");
			filePath = args[0];
			config.setPhoneBookPath(filePath);	
			if(args[1].equals("-d")){
				dictPath = args[2];
				config.setDictionaryPath(dictPath);
			}
			
			
		}
		
		logger.log(Level.FINEST,"Filepath :"+config.getPhoneBookPath());
		logger.log(Level.FINEST,"Dictpath :"+config.getDictionaryPath());
		return config;
		
	}
	
	/**
	 * Used to display the encodings and phone numbers on to screen.
	 * @param phoneNumber
	 * @param encodings
	 */
	public static void displayEncodings(String phoneNumber,List<String> encodings){
		
		System.out.println("-------------------------------------------------------");
		System.out.println("Encodings for phone no:"+phoneNumber);
		System.out.println();
		for(String encodeString:encodings){
			System.out.println(encodeString);
		}
		System.out.println("-------------------------------------------------------");
		
	}
	
	/**
	 * Used to get the input from users. Generic method which can be used to get
	 * decision based ocmmand from the users for determining eg) when to quit the program.
	 * @param message
	 * @return
	 */
	public static String getInputFrmUser(String message){
		String outcome = null;
		
		List<String> phoneNumberList = new ArrayList();		
		System.out.println(message);
		
		try{
			BufferedReader br = 
	                      new BufferedReader(new InputStreamReader(System.in));	 
			outcome=br.readLine();			
           
		}catch(IOException io){
			io.printStackTrace();
		}	
		return outcome;
		
	}
	
	
	
	

}
