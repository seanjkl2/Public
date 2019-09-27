package theater;

import java.io.IOException;
import java.util.Scanner;

public class Movie {
	private String title;
	private String genre;
	private String description;
	private double ticketPrice;
	private int runTime;
	private String rating;
	
	//constructor for all data, used when importing and adding in manager mode
	public Movie(String title, String genre, String description, double price, int runtime, String rating) {
		this.title = title;
		this.genre = genre;
		this.description = description;
		this.ticketPrice = price;
		this.runTime = runtime;
		this.rating = rating;
	}
	
	//constructor used for testing
	public Movie(String title, String genre) {
		this.title = title;
		this.genre = genre;
	}
	
	//constructor used for testing
	public Movie(String title) {
		this.title = title;
		this.genre = "NaN";
	}
	
	//constructor used for testing
	public Movie() {
		//blank for testing purposes
	}
	
	//writes all instance variables to a string and return it.
	public String saveData() {
		String s = "(" + this.getTitle()+ ")" + " (" + this.getGenre()+ ") " + "(" + this.getDescription() + ") (" + this.getTicketPrice() + ") (" + this.getRunTime() + ") (" + this.getRating()+")";
		return s;
	}
	
	//edit movie function that is called in manager mode
	public Movie editMovie(TheaterDriver td) {
		System.out.println("Currently Editing Movies: ");
		System.out.println("Pick option to edit: title, description, ticket price, runtime, rating, showtimes or return");
		Scanner in = new Scanner(System.in);
		String command = in.next();
		
		////EDIT TITLE
		if(command.toLowerCase().equals("title")) {
			in = new Scanner(System.in);
			System.out.println("Enter new title: ");
			String title = in.nextLine();
			this.setTitle(title);
		
		//EDIT DESCRIPTION	
		} else if (command.toLowerCase().equals("description")) {
			
			in = new Scanner(System.in);
			System.out.println("Enter the new description");
			String description = "";
			description += in.nextLine()+"\n";
			this.setDescription(description);
			System.out.println(this.getDescription());
		
		//EDIT PRICE
		} else if (command.toLowerCase().equals("ticket price") || command.toLowerCase().equals("ticketprice")) {
			
			in = new Scanner(System.in);
			System.out.println("Enter the new ticket price: ");
			double ticketPrice = in.nextDouble();
			this.ticketPrice = ticketPrice;
			System.out.println(this.toString());
			
		//EDIT RUNTIME
		} else if (command.toLowerCase().equals("runtime")) {
			
			in = new Scanner(System.in);
			System.out.println("Enter the new run time in minutes: ");
			int runTime = in.nextInt();
			this.setRunTime(runTime);
			System.out.println(this.toString());
		
		//EDIT RATING
		} else if (command.toLowerCase().equals("rating")) {
			
			in = new Scanner(System.in);
			System.out.println("Enter the new Rating(g, pg, pg13, r)");
			String rating = in.next();
			this.setRating(rating);
			System.out.println(this.toString());
			
		//EDIT TICKET SHOWTIMES
		} else if (command.toLowerCase().equals("showtimes") || command.toLowerCase().equals("show times")) {
			
			in = new Scanner(System.in);
			System.out.println("Enter Existing Time (24-hour): ");
			String existingTime = in.next(); 
			System.out.println("Enter New Time (24-hour): ");
			String newTime = in.next();
			int ticketsChanged = 0;
			String newTimeSI = newTime.substring(0,2) + newTime.substring(3,5);
			int newTimeInt = Integer.parseInt(newTimeSI);
			
			for (MovieTicket t: td.getTicketList()) {
				if (t.getShowTimeAsString().equals(existingTime)) {
					t.setShowTime(newTimeInt);
					ticketsChanged++;
				}
			}
			String s = ticketsChanged + " Tickets Updated";
			System.out.println(s);
		
		//RETURN TO MANAGER SCREEN
		} else if (command.toLowerCase().equals("return")) {
			//start the screen again get screen from td
			ScreenController screen = td.getScreens();
			screen.managerFunctionController(td);
		} else {
			System.out.println("Invalid Command");
			this.editMovie(td);
		}
		System.out.println(this.toString());
		return this.editMovie(td);
	}
	
		
	//GETTERS AND SETTERS
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRunTime() {
		return runTime;
	}

	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		String s = "Title: " + this.getTitle() + ", Price: " + this.getTicketPrice() + ", Run Time: " + this.getRunTime() +", Rating: " + this.getRating();
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		TheaterDriver start = new TheaterDriver();
		start.startScreen();
	}
	
}
