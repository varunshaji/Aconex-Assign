package com.vss.phnen.encoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vss.phnen.dictionary.DictEntry;
import com.vss.phnen.dictionary.Dictionary;


/**
 * Dictionary Based encoder for encoding the phone numbers. Uses recusrion nnd permutation for generating the possible phn numbers.
 * 
 * @author shajivarun@yahoo.co.in
 * @since 26-10-2013
 * @version 1.0
 *
 */

public class DictPhnEncoder implements PhnEncoder {
	
	/**
	 * Dictionary used for encoding
	 */
	Dictionary dictionary = null;
	
	private static Logger logger = Logger.getLogger(DictPhnEncoder.class.getName());
	
	
	public DictPhnEncoder(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	
	

	/**
	 * Encodes the phone numbers
	 * @throws EncodeException 
	 */
	
	//TODO clean code by moving flagcounts into permuStat
	public List<String> encodePhoneNum(String phoneNumber) throws EncodeException {
		
		
		List<String> resultEncodings = null;
		Boolean invalidNumber = false;
		
		//initializing flag
		char[] phnNumChars = phoneNumber.toCharArray();			
		int[] flagCounts = new int[phnNumChars.length];
		int firstEncodeAvailable=phnNumChars.length-1;
				
		for(int i=0;i<phnNumChars.length;i++){
			if(phnNumChars[i]!='.'){
				DictEntry dictEntry = dictionary.getEncodeMap().get(String.valueOf(phnNumChars[i]));
				if(dictEntry!=null){
					flagCounts[i]=dictEntry.getEncoderChars().length;
				}
			}
			if(flagCounts[i]>0&&i<firstEncodeAvailable){
				firstEncodeAvailable=i;
			}
		}
		
		// do validation that no to consecutive numbers can remain unencoded.
		for(int i=1;i<flagCounts.length;i++){
			if(flagCounts[i]==0&&flagCounts[i]==flagCounts[i-1]&&phnNumChars[i]!='.'&&phnNumChars[i-1]!='.'){
				logger.log(Level.WARNING,"The phoneNumber : "+phoneNumber+". Cannot be encoded as there is two consecutive non encodable digits.");
				throw new EncodeException(EncodeException.CONSECUTIVE_DIGIT_NO_CODE);
			}
			
		}
		
		
				
		//permutation logic
				
		PermutStat permutStat = new PermutStat();				
		permutStat.setPermutStringCurState(phoneNumber);
		permutStat.setOriginalPhoneNumber(phoneNumber);
		permutStat.setFirstEncodeAvailable(firstEncodeAvailable);
				
				
		computePermut(flagCounts,permutStat, flagCounts.length-1);
		
		//Calling to validate for the rules on the encodings
		resultEncodings = validate(permutStat.getEncodings());
		return resultEncodings;
	}
	
	

	/**
	 * Constructs the string form - state information held in pemutStat , order, interCount.
	 * 
	 * @param flagCounts - Hold the array of total number of possible encode characters for each digit in the phone number.
	 * @param permutStat - Hold the flags, current string that undergoes encoding through out the recursion logic.
	 * @param order - hold the index of the character currently encoding
	 * @param interCount - hold the index of the character which replaces the original phone number digit.
	 * @return
	 */
    PermutStat constructNumCharEnc(int flagCounts[],PermutStat permutStat,int order,int interCount){

		String toConvert = permutStat.getPermutStringCurState();		
		
		char[] toConvertArray = toConvert.toCharArray();
		int pos = (toConvertArray.length-1)-order;		
     	String dictKeyChar = String.valueOf(permutStat.getOriginalPhoneNumber().toCharArray()[pos]);		
		
     	/**
     	 * The condition to check if entire phonenumber encoding started changes if the initial character has no code. In
     	 * such case extra check need to be done. for eg) 15167.9 here if initial 1 has no codes in then <code>order==(toConvertArray.length-1)</code>
     	 * wont be correct as when the oder reaches 5 then the interCount will be 0 as the initial character 1 has no code. So in such cases the check need to be done 
     	 * till the first digit where the encoding is available.
     	 * 
     	 * Hence it will be <code>order==((toConvertArray.length-1)-permutStat.getFirstEncodeAvailable())</code>
     	 * 
     	 */
     	
		 if(order==((toConvertArray.length-1)-permutStat.getFirstEncodeAvailable())&&!permutStat.isLastOrder()){
			 permutStat.setLastOrder(true);			 
		 }
		 
		
		if(interCount>0){
			toConvertArray[pos]=dictionary.getEncodeMap().get(dictKeyChar).getEncoderChars()[interCount-1];
			if(permutStat.isLastOrder()){
				String resultEncoding = new String(toConvertArray).replace(".", "-");
				logger.log(Level.FINEST,"Adding encoding - "+resultEncoding);
				permutStat.getEncodings().add(resultEncoding);
			}			
		}
		logger.log(Level.FINEST,"New encoding - "+new String(toConvertArray)+" order="+order+" interCount="+interCount);
		permutStat.setPermutStringCurState(new String(toConvertArray));
		return permutStat;    	 
     }
	
    
    /**
     * Recursive function that create the permutation from which the construct/encode string method is called.
     * 
     * @param flagCounts - Hold the array of total number of possible encode characters for each digit in the phone number.
     * @param permutStat - Hold the flags, current string that undergoes encoding through out the recursion logic.
     * @param order - hold the index of the character currently encoding
     */
	void computePermut(int[] flagCounts,PermutStat permutStat,int order){
		int interCount = flagCounts[(flagCounts.length-1)-order];
		if(interCount==0&&order!=0)computePermut(flagCounts,permutStat,--order);
		while(interCount>=0){
			int backOrder = order;			
			if(order!=0)computePermut(flagCounts,permutStat,--order);
			order = backOrder;			
			permutStat = constructNumCharEnc(flagCounts,permutStat,order,interCount);
			interCount--;			
		}		
	}
	
	
	/**
	 * Hold the state information for the recursions during encoding.
	 * 
	 * @author  shajivarun@yahoo.co.in
	 *
	 */
	class PermutStat{		
        /**
         * Hold the current state of the string that is getting encoded.
         */
		String permutStringCurState;
		
		/**
		 * State if the permutation progressed to the last phase where every letter in the ohonenumber
		 * gets encoded. And this ensures that the encodings is complete.
		 */
		boolean lastOrder;
		
		/**
		 * Hold the first position of the phonenumber from where encoding is available as per the dictionay
		 */
		int firstEncodeAvailable;
		
		String originalPhoneNumber;
		
		/**
		 * Store the result of each encoding as a separate entry.
		 */
		List<String> encodings = new ArrayList();
		
		public boolean isLastOrder() {
			return lastOrder;
		}

		public void setLastOrder(boolean lastOrder) {
			this.lastOrder = lastOrder;
		}

		public String getPermutStringCurState() {
			return permutStringCurState;
		}

		public void setPermutStringCurState(String permutStringCurState) {
			this.permutStringCurState = permutStringCurState;
		}

		public String getOriginalPhoneNumber() {
			return originalPhoneNumber;
		}

		public void setOriginalPhoneNumber(String originalPhoneNumber) {
			this.originalPhoneNumber = originalPhoneNumber;
		}

		public List<String> getEncodings() {
			return encodings;
		}

		public void setEncodings(List<String> encodings) {
			this.encodings = encodings;
		}

		public int getFirstEncodeAvailable() {
			return firstEncodeAvailable;
		}

		public void setFirstEncodeAvailable(int firstEncodeAvailable) {
			this.firstEncodeAvailable = firstEncodeAvailable;
		}

	
	}


	@Override
	/**
	 * Method to add additional validations. Currently this removes duplicates
	 * and sorts the output encodings.
	 */
	public List<String> validate(List<String> phoneNumbers) {
		Set<String> uniqueSortedNumbers = new TreeSet();
		for(String phoneNumber:phoneNumbers){
			uniqueSortedNumbers.add(phoneNumber);
		}
		return new ArrayList<String> (uniqueSortedNumbers);
	}

}
