package hangman;

import java.util.ArrayList;

public class Player {
	ArrayList<String> ownGuesses;
	String name;
	int numBadGuesses;  //stores number of bad guesses used to draw
	Boolean canGuess;
	View v;
	String dashWord;
	
	public Player(String name, Boolean canGuess) {
		this.name = name;
		ownGuesses = new ArrayList<String>();
		this.canGuess = canGuess;
		this.numBadGuesses = 0;
	}
	
	public String getGuesses() {
		String reply = "";
		
		for(String s : this.ownGuesses) {
			reply += s + " | ";
		}
		return reply;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String string = this.name;
		return string;
	}
	
	public void addGuess(String s) {
		this.ownGuesses.add(s);
	}
	
	public boolean hasGuessed(String s) {
		for(String x : ownGuesses) {
			if(s.toLowerCase().equals(x.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> getOwnGuesses(){
		return this.ownGuesses;
	}

	public Boolean getCanGuess() {
		return canGuess;
	}

	public void setCanGuess(Boolean canGuess) {
		this.canGuess = canGuess;
	}

	public int getNumBadGuesses() {
		return numBadGuesses;
	}

	public void setNumBadGuesses(int numBadGuesses) {
		this.numBadGuesses = numBadGuesses;
	}
	
	public void setDashWord(String word) {
		this.dashWord = word;
	}
	
	public String getDashWord() {
		return this.dashWord;
	}
	
}
