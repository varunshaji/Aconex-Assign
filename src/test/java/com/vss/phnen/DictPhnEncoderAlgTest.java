package com.vss.phnen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.vss.phnen.dictionary.DictEntry;

public class DictPhnEncoderAlgTest {
	
	private static Logger logger = Logger.getLogger(DictPhnEncoderAlgTest.class.getName());
	
	Map<String,DictEntry> dictMap = null;
	
	String phnNum = "";

	@Test
	public void testEncodePhoneAlg() {
		

		phnNum = "29.3.982.8";
		
		List<DictEntry> dictionary = new ArrayList();
		dictMap = new HashMap();
		
		int charT = 65;
		for (int i =0; i<10;i++){
			charT = (charT>90)?65:charT;
			DictEntry dictEntry = new DictEntry(i,new char[]{(char)charT++,(char)charT++,(char)charT++});
			dictionary.add(dictEntry);
			logger.log(Level.INFO, "Number"+i+" chars="+dictEntry.getEncoderChars()[0]+","+dictEntry.getEncoderChars()[1]+","+dictEntry.getEncoderChars()[2]);
			dictMap.put(Integer.toString(dictEntry.getPhnNumDig()), dictEntry);
		}
				
		//initializing flag
		char[] phnNumChars = phnNum.toCharArray();
		

		
		int[] flagCounts = new int[phnNumChars.length];
		
		for(int i=0;i<phnNumChars.length;i++){
			if(phnNumChars[i]!='.')
			flagCounts[i]=dictMap.get(String.valueOf(phnNumChars[i])).getEncoderChars().length;
			logger.log(Level.INFO,"flagcount -"+i+" "+flagCounts[i]);
		}
		
		//permutation logic
		
		PermutStat permutStat = new PermutStat();
		
		permutStat.setPermutStringCurState(new String(phnNum));
		
		
		computePermut(flagCounts,permutStat, flagCounts.length-1);
				
	}
	
	private void computePermut(int[] flagCounts,PermutStat permutStat,int order){
		int interCount = flagCounts[(flagCounts.length-1)-order];
		if(interCount==0)computePermut(flagCounts,permutStat,--order);
		while(interCount>=0){
			int backOrder = order;
			//setting flag
			// permutStat.getStatFlags()[(flagCounts.length-1)-order]=interCount;
			if(order!=0)computePermut(flagCounts,permutStat,--order);
			order = backOrder;
			
			
			//setting flag hack
			//statFlags[(flagCounts.length-1)-order]=(interCount==0)?interCount:(interCount-1);
			//constructing the string
			permutStat = constructNumCharEnc(flagCounts,permutStat,order,interCount);
			if(interCount>0)
			System.out.println("flagcount -"+order+" Counting :"+interCount);
			interCount--;
			
		}		
	}
	
	private PermutStat constructNumCharEnc(int flagCounts[],PermutStat permutStat,int order,int interCount){
		
		
		String toConvert = permutStat.getPermutStringCurState();
				
		
		
		
		char[] toConvertArray = toConvert.toCharArray();
		int pos = (toConvertArray.length-1)-order;
		
     	String dictKeyChar = String.valueOf(phnNum.toCharArray()[pos]);		
		
		 if(order==(toConvertArray.length-1)&&!permutStat.isLastOrder()){
			 permutStat.setLastOrder(true);
			 
		 }
		
		
		if(interCount>0){
			toConvertArray[pos]=dictMap.get(dictKeyChar).getEncoderChars()[interCount-1];
			if(permutStat.isLastOrder())
			System.out.println(new String(toConvertArray).replace(".", "-"));	
		}else{
			System.out.println("------------");
		}

		permutStat.setPermutStringCurState( new String(toConvertArray));
		
		return permutStat;
		
	}
	
	private class PermutStat{		

		String permutStringCurState;		
		
		boolean lastOrder;
		
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

		

	}
}
