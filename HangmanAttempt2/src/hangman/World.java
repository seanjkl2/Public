package hangman;

import java.util.ArrayList;

public interface World {
	public ArrayList<Player> getPlayers();
	public ArrayList<String> getAllGuesses();
	public void playTurn(String s, Player p, View view);
	
}
