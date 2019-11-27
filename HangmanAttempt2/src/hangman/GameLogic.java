package hangman;

import java.util.ArrayList;

public class GameLogic {
	ArrayList<Player> players;
	ArrayList<String> allGuesses;
	ArrayList<View> views;
	String actualWord;
	String dashWord;
	
	public GameLogic(ArrayList<Player> allPlayers, ArrayList<String> allGuesses, String word, String dashWord, ArrayList<View> views) {
		this.players = allPlayers;
		this.allGuesses = allGuesses;
		this.actualWord = word.toLowerCase();
		this.views = views;
		//System.out.println("DashWord Recieved by GameLogic: " + dashWord);
		this.dashWord = dashWord;
	}
		
	public void flipGuesses(Player player) {
		//find other player and swap guess state;
		for(Player p : players) {
			if(!p.equals(player)) {
				p.setCanGuess(true);
			}
		}
		player.setCanGuess(false);
	}
	
	public boolean wordNotGuessed(String guess) {
		System.out.println(allGuesses.toString());
		for(String s : allGuesses) {
			//System.out.println(allGuesses.toString());
			if(s.toLowerCase().equals(guess.toLowerCase())) {
				return false;
			}
		}
		return true;
	}
	
	public String playTurn(String guess, Player p, Universe u) {
		String newString = "";
		//System.out.println(u.toString());
		//System.out.println(this.dashWord);
		if(p.getCanGuess()) {
			if(this.wordNotGuessed(guess)) {
				p.addGuess(guess);
				this.allGuesses.add(guess);
				this.flipGuesses(p);
				//System.out.println("DashWord right before for Loop in GameLogic: " + this.dashWord);
				
				//make a new dashword with only the new guess not as dashes
				for(int i = 0; i < this.actualWord.length(); i++) {
					if(this.actualWord.charAt(i) == guess.charAt(0) && this.dashWord.charAt(i) == '-') {
						newString += guess;
					} else {
						newString += "-";
					}
				}
				
				//compare new dash word to old dash word and combine the two, all non dashes kept.
				char[] newWordArr = newString.toCharArray();
				for(int i = 0; i < newString.length(); i++) {
					if(this.dashWord.charAt(i) != '-') {
						newWordArr[i] = dashWord.charAt(i);
					}
				}
				
				//return undashed word 
				newString = new String(newWordArr);
				u.setDashWord(newString);
				//System.out.println(newString);
				
				//REPAINTING HAPPENS
				//HERE
				//BAD GUESS
				if(sameWords(this.dashWord, newString)){
					this.repaint();
				}
				
				this.dashWord = newString;
				return this.dashWord;			
			
			//Letter already guessed / Guess again nothing happens
			} else {
				//System.out.println("Shit already guessed");
				this.repaint();
				return this.dashWord;
			}
		
		} else {
			System.out.println("Not your turn");
			return this.dashWord;
		}
		//System.out.println(p.getGuesses());
		//System.out.println(p.toString());
		
	}
	
	public void repaint() {
		for (Player p : players) {
			p.setNumBadGuesses(p.getNumBadGuesses() + 1);
		}
		
		for(View v : views) {
			v.repaint();
		}
	}
	
	public static boolean sameWords(String newDash, String oldDash) {
		if(newDash.toLowerCase().equals(oldDash.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkWinner() {
		if(this.dashWord.equals(this.actualWord)) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<String> getAllGuesses() {
		return allGuesses;
	}

	public void setAllGuesses(ArrayList<String> allGuesses) {
		this.allGuesses = allGuesses;
	}

	public String getActualWord() {
		return actualWord;
	}

	public void setActualWord(String actualWord) {
		this.actualWord = actualWord;
	}
	
	public void setAllDashWord(String word) {
		for(Player p : players) {
			p.setDashWord(word);
		}
	}
}
