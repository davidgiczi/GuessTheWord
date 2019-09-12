package com.david.giczi.guesstheword.model;


import java.util.ArrayList;
import java.util.List;

import com.david.giczi.guesstheword.exception.GuessedTheWordException;

public class WordAdmin implements Words{

	private List<Character> letters;
	private List<Boolean> booleanList;
	private List<Character> addedCharList;
	private  String theWord;
	private String addedChars;
	
	
	public String getWord() {
		return theWord;
	}
	

	public List<Character> getLetters() {
		return letters;
	}


	public List<Boolean> getBooleanList() {
		return booleanList;
	}

	

	public String getAddedChars() {
		return addedChars;
	}


	public void addWord() {
		
		theWord=Words.words[(int)(Math.random()*words.length)];
			
	}
	
	
	public void initTheWordLists() {
		
		letters=new ArrayList<>();
		booleanList=new ArrayList<>();
		addedCharList=new ArrayList<>();
		
		for(int i=0; i<theWord.length(); i++) {
			
			letters.add(theWord.charAt(i));
			booleanList.add(false);
		}
	
	}
	
	
	
	public void processTheWord(String tipp) {
	
		
		if(tipp.equals(theWord)) {
			
			for(int i=0; i<theWord.length(); i++) {
				
				booleanList.set(i, true);
			}
			
			return;
		}
		
		
		for(int i=0; i<theWord.length(); i++) {
			
			if(tipp.charAt(0)==theWord.charAt(i)) {
				
				booleanList.set(i, true);
			}
			
		}
		
	}
	
	
	public void evaluateInputData(String tipp) throws  GuessedTheWordException  {
		
		
		if(tipp.equals(theWord) || !booleanList.contains(false)) {
			
			throw new GuessedTheWordException();
		}
		
	}
		
	public void addCharToAddedCharList(String tipp) {
		
		if(!addedCharList.contains(tipp.charAt(0))) {
			
				addedCharList.add(tipp.charAt(0));
			}
		
			StringBuilder build=new StringBuilder();
			
			for (Character character : addedCharList) {
				build.append(character+", ");
			}
		
			addedChars=build.substring(0, build.toString().length()-2);
		}
	
	
}
