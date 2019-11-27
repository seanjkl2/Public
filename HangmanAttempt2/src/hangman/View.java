package hangman;

import java.util.ArrayList;

import javax.swing.JFrame;

public class View extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	Player player;
	ArrayList<Player> allPlayers;
	Universe universe;
	
	public View(Player p, int x, int y, Universe u) {
		this.player = p;
		this.x = x;
		this.y = y;
		this.universe = u;
		this.makeHangman();
	}
	

	public void makeHangman() {
		Painter pan = new Painter(universe, player, this);
		pan.makePanelMan();
		this.add(pan);		
		this.setTitle(player.getName() + " Playing Hangman");
		this.setVisible(true);
		this.pack();
		
	}
	public Player getPlayer() {
		return this.player;
	}
	public void setUniverse(Universe u) {
		this.universe = u;
	}
	public void setAllPlayers(ArrayList<Player> allPlayers) {
		this.allPlayers = allPlayers;
	}

	
}
