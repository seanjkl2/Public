package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Universe implements World{
	ArrayList<Player> players;
	ArrayList<String> allGuesses;
	ArrayList<View> views;
	String actualWord;
	String dashWord;
	
	public Universe(ArrayList<Player> p, ArrayList<View> views) throws IOException {
		this.players = p;
		this.views = views;
		this.allGuesses = new ArrayList<String>();
		this.actualWord = fetchRandomWord();
		for(Player x : players) {
			for(String s: x.getOwnGuesses()) {
				this.allGuesses.add(s);
			};
		}
		this.dashWord = buildString("-", this.actualWord.length());
	}
	
	
	public static String fetchRandomWord() throws IOException {
		File file = new File("hangmanWords.lol");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String curData = "";
		ArrayList<String> wordList = new ArrayList<String>();
		Random rand = new Random();
		String wordChoice = "hello";
		try {
			while((curData = in.readLine()) != null) {
					wordList.add(curData);
				}
			int indexChoice = rand.nextInt(wordList.size());
			wordChoice = wordList.get(indexChoice);
			in.close();
		} catch (IOException e) {
			in.close();
		}
		return wordChoice;
	}
	
	@Override
	public ArrayList<Player> getPlayers() {
		return this.players;
	}


	@Override
	public ArrayList<String> getAllGuesses() {
		return this.allGuesses;
	}

	@Override
	public void playTurn(String s, Player p, View view) {
		GameLogic game = new GameLogic(this.players, this.allGuesses, this.actualWord, this.dashWord, views);
		String gameResult = game.playTurn(s, p, this);
		if (gameResult.equals(this.dashWord)) {
			//System.out.println("no Change");
		} else {
			//System.out.println("change");
		}
		
		System.out.println(this.checkWinner());
		this.dashWord = gameResult;
		
		if(this.checkWinner()) {
			this.decideWinners(p);
		
		}
		this.repaintNoBadGuess();
		System.out.println(this.dashWord);
		//System.out.println("Dash Word Universe Recieved back: "+this.dashWord);
		
	}
	
	public String decideWinners(Player p) {
		String s = "You Win! The word was: " + this.getActualWord();
		
		this.setDashWord(s);
		return s;
	}
	
	public boolean noPlayerGuessed(String s) {
		for(Player p : players) {
			if(p.hasGuessed(s)) {
				return false;
			}
		}
		return true;
	}
	
	public static String buildString(String s, int count){
	    StringBuilder r = new StringBuilder();
	    for (int i = 0; i < count; i++) {
	        r.append(s);
	    }
	    return r.toString();
	}
	
	public String getActualWord() {
		return actualWord;
	}

	public void setActualWord(String actualWord) {
		this.actualWord = actualWord;
	}

	public String getDashWord() {
		return dashWord;
	}

	public void setDashWord(String dashWord) {
		this.dashWord = dashWord;
	}
	
	public void repaintNoBadGuess() {
		for(View v : views) {
			v.repaint();
		}
	}
	
	public boolean checkWinner() {
		if (this.actualWord.toLowerCase().equals(this.dashWord.toLowerCase())){
			return true;
		} else {
			return false;
		}
	}
	
}
