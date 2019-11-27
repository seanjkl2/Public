package hangman;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Painter extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Player player;
	Universe uni;
	View view;
	
	public Painter(Universe u, Player p, View v) {
		this.uni = u;
		this.player = p;
	}
	
	public void makePanelMan() {
		JTextField text = new JTextField(4);
		JButton submit = new JButton("Submit");
		this.add(text);
		this.add(submit);
		this.setVisible(true);
		
		ActionListener submitter = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = text.getText();
				s = s.substring(0,1);
				text.setText("");
				//System.out.println(uni.toString());
				uni.playTurn(s, player, view);
				
				
			}
		};
		submit.addActionListener(submitter);
		text.addActionListener(submitter);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		ArrayList<String> guesses = uni.getAllGuesses();
		
		for(int i = 0; i < guesses.size(); i++) {
			g.drawString(guesses.get(i), 300, (i + 1)* 20);
		}
		Graphics2D g2 = (Graphics2D) g;
		int badGuesses = player.getNumBadGuesses();
		super.paintComponent(g);

		
		//Show guesses
		String dashWord = uni.getDashWord();
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial Black", Font.PLAIN, 12));
		g.drawString("Guesses: ", 215, 40);
		for(int i = 0; i < guesses.size(); i++) {
			g.drawString(guesses.get(i), 215, 40+((i+1)*10));
		}
		
		
		//Draw turn notification
		if(player.getCanGuess()) {
			String string = player.getName() + " it is your turn!";
			g.setFont(new Font("Arial Black", Font.ITALIC, 8));
			g.drawString(string, 150, 200);
		}
		
		//Draw Dash Word
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.drawString(dashWord, 10, 250);

		//draw hangman
		if (badGuesses == 1 ) {
			//1 left side
			g.fillRect(20, 60, 5, 160);
		
		} else if (badGuesses == 2) {
			//1 left side
			g.fillRect(20, 60, 5, 160);
			//2 bottom
			g.fillRect(20, 220, 160, 5);
		
		} else if (badGuesses == 3) {
			//1 left side
			g.fillRect(20, 60, 5, 160);
			//2 bottom
			g.fillRect(20, 220, 160, 5);
			//3 top
			g.fillRect(20, 60, 80, 5);
			
		} else if (badGuesses == 4) {
			//1 left side
			g.fillRect(20, 60, 5, 160);
			//2 bottom
			g.fillRect(20, 220, 160, 5);
			//3 top
			g.fillRect(20, 60, 80, 5);
			//4
			g.fillRect(100,60,5,25);
			
		} else if (badGuesses == 5) {
			//1 left side
			g.fillRect(20, 60, 5, 160);
			//2 bottom
			g.fillRect(20, 220, 160, 5);
			//3 top
			g.fillRect(20, 60, 80, 5);
			//4
			g.fillRect(100,60,5,25);
			
			//5 reset stroke so buttons dont get fucked
			g2.setStroke(new BasicStroke(5));
			g.drawOval(87, 85, 30, 30);
			g2.setStroke(new BasicStroke());
			
		} else if (badGuesses == 6) {
			//1 left side
			g.fillRect(20, 60, 5, 160);
			//2 bottom
			g.fillRect(20, 220, 160, 5);
			//3 top
			g.fillRect(20, 60, 80, 5);
			//4
			g.fillRect(100,60,5,25);
			
			//5 reset stroke so buttons dont get fucked
			g2.setStroke(new BasicStroke(5));
			g.drawOval(87, 85, 30, 30);
			g2.setStroke(new BasicStroke());
			
			//6 draw body
			g.fillRect(100, 115, 5, 55);
			
		} else if (badGuesses == 7) {
			//1 left side
			g.fillRect(20, 60, 5, 160);
			//2 bottom
			g.fillRect(20, 220, 160, 5);
			//3 top
			g.fillRect(20, 60, 80, 5);
			//4
			g.fillRect(100,60,5,25);
			
			//5 reset stroke so buttons dont get fucked
			g2.setStroke(new BasicStroke(5));
			g.drawOval(87, 85, 30, 30);
			g2.setStroke(new BasicStroke());
			
			//6 draw body
			g.fillRect(100, 115, 5, 55);
			
			//7 Left arm
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(100, 130, 88, 145);
			g2.setStroke(new BasicStroke());
			
		} else if (badGuesses == 8) {
			//1 left side
			g.fillRect(20, 60, 5, 160);
			//2 bottom
			g.fillRect(20, 220, 160, 5);
			//3 top
			g.fillRect(20, 60, 80, 5);
			//4
			g.fillRect(100,60,5,25);
			
			//5 reset stroke so buttons dont get fucked
			g2.setStroke(new BasicStroke(5));
			g.drawOval(87, 85, 30, 30);
			g2.setStroke(new BasicStroke());
			
			//6 draw body
			g.fillRect(100, 115, 5, 55);
			
			//7 Left arm
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(100, 130, 88, 145);
			g2.setStroke(new BasicStroke());
			
			//8 Right arm
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(105, 130, 115, 145);
			g2.setStroke(new BasicStroke());
			
		} else if (badGuesses == 9) {
			//1 left side
			g.fillRect(20, 60, 5, 160);
			//2 bottom
			g.fillRect(20, 220, 160, 5);
			//3 top
			g.fillRect(20, 60, 80, 5);
			//4
			g.fillRect(100,60,5,25);
			
			//5 reset stroke so buttons dont get fucked
			g2.setStroke(new BasicStroke(5));
			g.drawOval(87, 85, 30, 30);
			g2.setStroke(new BasicStroke());
			
			//6 draw body
			g.fillRect(100, 115, 5, 55);
			
			//7 Left arm
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(100, 130, 88, 145);
			g2.setStroke(new BasicStroke());
			
			//8 Right arm
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(105, 130, 115, 145);
			g2.setStroke(new BasicStroke());
			
			//9 Left Leg
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(100, 170, 85, 195);
			g2.setStroke(new BasicStroke());
			
		} else if (badGuesses == 10) {
			//1 left side
			g.fillRect(20, 60, 5, 160);
			//2 bottom
			g.fillRect(20, 220, 160, 5);
			//3 top
			g.fillRect(20, 60, 80, 5);
			//4
			g.fillRect(100,60,5,25);
			
			//5 reset stroke so buttons dont get fucked
			g2.setStroke(new BasicStroke(5));
			g.drawOval(87, 85, 30, 30);
			g2.setStroke(new BasicStroke());
			
			//6 draw body
			g.fillRect(100, 115, 5, 55);
			
			//7 Left arm
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(100, 130, 88, 145);
			g2.setStroke(new BasicStroke());
			
			//8 Right arm
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(105, 130, 115, 145);
			g2.setStroke(new BasicStroke());
			
			//9 Left Leg
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(100, 170, 85, 195);
			g2.setStroke(new BasicStroke());
			
			//10 Right leg
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(103, 170, 117, 195);
			g2.setStroke(new BasicStroke());
			
			g.setColor(Color.red);
			g.drawString("YOU LOSE!!!", 100, 150);
		}
	}
}
