package com.vss.phnen.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vss.phnen.PhnEnConfig;
import com.vss.phnen.encoder.DictPhnEncoder;

/**
 * Hold the entire Dictionary fed for the encoding purpose.
 * @author shajivarun@yahoo.co.in
 *
 */
public class Dictionary {
	
	Map<String,DictEntry> encodeMap = new HashMap();
	
	private static Logger logger = Logger.getLogger(Dictionary.class.getName());
	
	public Dictionary(PhnEnConfig config) throws  DictionaryException{
		
		initialize(config.getDictionaryPath());
		
	}
	
	/**
	 * By default the method looks in the classpath and the running directory of the program.
	 * 
	 * @param path
	 * @throws IOException
	 * @throws DictionaryException 
	 */
	protected void initialize(String path) throws DictionaryException{
		BufferedReader bufReader = null;		
		 
		try { 
			String dictEntry; 
			if(path!=null){
				bufReader = new BufferedReader(new FileReader(path)); 
				}else{
				logger.log(Level.WARNING, "No Dictionary File mentioned.Reverting to default dictionary");
				bufReader = new BufferedReader(new InputStreamReader(
						    getClass().getClassLoader().getResourceAsStream(
						        PhnEnConfig.defaultDictionaryPath)));
				}
			while ((dictEntry = bufReader.readLine()) != null) {
				constructEntries(dictEntry);	
				
			}
 
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error while reading dictionary file : "+path,e);
			throw new DictionaryException(DictionaryException.FAILED_OPEN_DICT, e);			
			
		} finally {
			try {
				
				if (bufReader != null)bufReader.close();
			} catch (IOException ex) {
				logger.log(Level.SEVERE, "Error while reading dictionary file : "+path,ex);
				throw new DictionaryException(DictionaryException.FAILED_OPEN_DICT, ex);	
			}
		}
	}	

	
	/**
	 * Private method that creates the dict entries from the individual lines parsed from
	 * the dict file. The dictfile should be tabseparated fromat of digit and possible encode Strings.
	 * 
	 * @param dictEntries
	 * @return
	 */
	private void  constructEntries(String dictEntries){
		String[] entries = null;
		String[] keyCodes = null;
		
		
		entries = dictEntries.split("\\t");
		
		keyCodes = entries[1].split(",");
		
		char[] keyCodesChar = new char[keyCodes.length];
		
		for(int i = 0;i<keyCodes.length;i++){
			
			//TODO additional validation if the digit entries and albhabets are within range.
			//Also do check to validate that the keycodes are in alphabet range
			if(keyCodes[i].length()==1){
				String code = String.valueOf(keyCodes[i].charAt(0));
				keyCodesChar[i]=code.toUpperCase().charAt(0);
			}
		}
		
		//TODO add validation for the digit entry in DictEntry
		DictEntry dictEntry = new DictEntry(Integer.valueOf(entries[0]), keyCodesChar);		
		encodeMap.put(entries[0],dictEntry);
		
	}
	
	public Map<String, DictEntry> getEncodeMap(){
		return encodeMap;
	}
	

}
