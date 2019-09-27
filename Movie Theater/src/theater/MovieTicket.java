package theater;

import java.io.IOException;

public class MovieTicket {
	private double price;
	private Movie movie;
	private int showTime;
	private String type;
	
	//main constructor used, type is applied later.
	public MovieTicket(double price, Movie movie, int time) {
		this.price = price;
		this.movie = movie;
		this.showTime = time;
		
	}
	
	//blank constructor for testing
	public MovieTicket() {
		// TODO Auto-generated constructor stub
	}
	
	//returns a string with all instance variables
	public String saveData() {
		String s = "(";
		s += this.getPrice();
		s += ") ";
		s += this.getMovie().saveData();
		s += " (";
		s += this.getShowTime();
		s += ")";
		return s;
	
	}
	
	//gives half off if its a matinee
	public void applyMatineePrice() {
		if(this.showTime >= 1200 && this.showTime <= 1600) {
			this.setPrice(this.getPrice()*.5);
		}
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getShowTime() {
		return showTime;
	}

	public void setShowTime(int showTime) {
		this.showTime = showTime;
	}
	
	//returns the showtime int but as a properly formatted string for time purposes
	public String getShowTimeAsString() {
		String time = Integer.toString(this.getShowTime());
		String s = "";
		if(this.getShowTime() < 1000) {
			s = "0" + time.substring(0,1) + ":" + time.substring(1) + " AM";
		} else if (this.getShowTime() < 1200) {
			s = time.substring(0,2) + ":" + time.substring(2) + " AM";
		} else if (this.getShowTime() > 1200 && this.getShowTime() < 2400) {
			s = time.substring(0,2) + ":" + time.substring(2) + " PM";
		} else {
			s = "12:00 PM";
		}
		return s;
	}
	
	//gets child/adult/senior
	public String getType() {
		return type;
	}
	
	//sets child/adult/senior
	public void setType(String type) {		
		this.type = type;
		if (this.type.equals("child") || this.type.equals("senior")){
			this.setPrice(.75 * this.getPrice());
		}
	}
	
	@Override
	public String toString() {
		String s = this.getPrice() + " " + this.getMovie().toString() + " " + this.getShowTime();
		return s;
	}
	
	//starts program
	public static void main(String[] args) throws IOException  {
		TheaterDriver testDriver = new TheaterDriver();
		testDriver.startScreen();
	}
	
}
