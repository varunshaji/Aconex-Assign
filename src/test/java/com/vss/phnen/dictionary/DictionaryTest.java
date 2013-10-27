package com.vss.phnen.dictionary;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.vss.phnen.PhnEnConfig;

public class DictionaryTest {

	@Test
	public void testDictionary() throws IOException {
				
		PhnEnConfig config = new PhnEnConfig();
		config.setDictionaryPath("defaultDict.dict");
		//config.setDictionaryPath(null);
		Dictionary dictionary = null;
		try {
			dictionary = new Dictionary(config);
		} catch (DictionaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String,DictEntry> act = new HashMap();
		
		act.put("2", new DictEntry(1,new char[] {'A','B','C'}));
		act.put("3", new DictEntry(1,new char[] {'D','E','F'}));
		act.put("4", new DictEntry(1,new char[] {'G','H','I'}));
		act.put("5", new DictEntry(1,new char[] {'J','K','L'}));
		act.put("6", new DictEntry(1,new char[] {'M','N','O'}));
		act.put("7", new DictEntry(1,new char[] {'P','Q','R','S'}));
		act.put("8", new DictEntry(1,new char[] {'T','U','V'}));
		act.put("9", new DictEntry(1,new char[] {'W','X','Y','Z'}));	

		
		assertEquals(dictionary.getEncodeMap().keySet(), act.keySet());
		assertArrayEquals(dictionary.getEncodeMap().get("9").getEncoderChars(), act.get("9").getEncoderChars());
		assertArrayEquals(dictionary.getEncodeMap().get("5").getEncoderChars(), act.get("5").getEncoderChars());
		assertArrayEquals(dictionary.getEncodeMap().get("3").getEncoderChars(), act.get("3").getEncoderChars());
	}
	
	@Test
	public void testDictionaryDefault() throws IOException {
				
		PhnEnConfig config = new PhnEnConfig();
		
		
		Dictionary dictionary = null;
		try {
			dictionary = new Dictionary(config);
		} catch (DictionaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String,DictEntry> act = new HashMap();
		
		act.put("2", new DictEntry(1,new char[] {'A','B','C'}));
		act.put("3", new DictEntry(1,new char[] {'D','E','F'}));
		act.put("4", new DictEntry(1,new char[] {'G','H','I'}));
		act.put("5", new DictEntry(1,new char[] {'J','K','L'}));
		act.put("6", new DictEntry(1,new char[] {'M','N','O'}));
		act.put("7", new DictEntry(1,new char[] {'P','Q','R','S'}));
		act.put("8", new DictEntry(1,new char[] {'T','U','V'}));
		act.put("9", new DictEntry(1,new char[] {'W','X','Y','Z'}));	

		
		assertEquals(dictionary.getEncodeMap().keySet(), act.keySet());
		assertArrayEquals(dictionary.getEncodeMap().get("9").getEncoderChars(), act.get("9").getEncoderChars());
		assertArrayEquals(dictionary.getEncodeMap().get("5").getEncoderChars(), act.get("5").getEncoderChars());
		assertArrayEquals(dictionary.getEncodeMap().get("3").getEncoderChars(), act.get("3").getEncoderChars());
	}

}
