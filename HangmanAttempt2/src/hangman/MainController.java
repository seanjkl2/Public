package hangman;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
	
	
	public static void main(String[] args) throws IOException {
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<View> views = new ArrayList<View>();
		String[] names = {"Sean", "Roach"};
		
		for (int i = 0; i < 2; i++) {
			if(i == 1) {
				players.add(new Player(names[i], false));
			} else {
				players.add(new Player(names[i], true));
			}
		}
		
		Universe uni = new Universe(players,  views);
		
		for (int i = 0; i < 2; i++) {
			views.add(new View(players.get(i), 20, 20, uni));
			views.get(i).setVisible(true);
			views.get(i).setSize(300,300);
		}
		
		//System.out.println(uni.toString());
		for(View v : views) {
			v.setAllPlayers(players);
		}
	}
}
