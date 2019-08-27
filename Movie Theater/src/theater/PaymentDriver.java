package theater;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class PaymentDriver {
	private Person customer;
	private String paymentType;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	LocalDateTime now = LocalDateTime.now();
	String dayOfWeek = now.getDayOfWeek().toString();
	
	public PaymentDriver(Person p, MovieTicket mt) {
		this.customer = p;
		this.resetTime();
	}
	public PaymentDriver() {};
	
	//main purchase function
	public static void purchase(Person p, TheaterDriver td) {
		Scanner in = new Scanner(System.in);
		ArrayList<MovieTicket> cart = new ArrayList<MovieTicket>();
		ArrayList<MovieTicket> availableTickets = td.getTicketList();
		ArrayList<String> timeList = new ArrayList<String>();
		Person temp = new Person();
		MovieTicket selectedTicket = new MovieTicket();
		ScreenController controller = new ScreenController();
		
		System.out.println();
		System.out.println("Available Movies: ");
		System.out.println(td.viewMovies());
		System.out.println();
		System.out.println("Please enter title of movie you wish to purchase a ticket for, or enter back: ");
		String title = in.nextLine();
		System.out.println(title);
		if(title.toLowerCase().equals("back")) {
			controller.custFunctionController(p, td);
		}
		Boolean found = false;
		//check to make sure movie exists
		for(MovieTicket mt : availableTickets) {
			if(mt.getMovie().getTitle().toLowerCase().equals(title.toLowerCase())) {
				found = true;
			}
		}
		if(!found) {
			System.out.println("No Showings for the Movie found.");
			purchase(p, td);
		}
		
		//add all times for selected movie and show them
		System.out.println("Available times for selected Movie:");
		for (MovieTicket mt : availableTickets) {
			if(mt.getMovie().getTitle().toLowerCase().equals(title.toLowerCase()) && (!timeList.contains(mt.getShowTimeAsString()))) {
				timeList.add(mt.getShowTimeAsString());
				System.out.println(mt.getShowTimeAsString());
			}
		}
		
		System.out.println("Please enter time you wish to see the movie:");
		String time = in.nextLine();
		
		//check to make sure time is valid
		if(!timeList.contains(time)) {
			System.out.println("Invalid time.");
			purchase(p, td);
		}
		
		//fetch ticket with coressponding movie and time
		for(MovieTicket mt : availableTickets) {
			if(mt.getMovie().getTitle().toLowerCase().equals(title.toLowerCase()) && mt.getShowTimeAsString().toLowerCase().equals(time.toLowerCase())) {
				selectedTicket = mt;
				break;
			} 
		}
		
		//Child/adult/senior tickets?
		System.out.println("Are these for children adult or seniors?");
		String type = in.nextLine();
		selectedTicket.setType(type.toLowerCase());
		
		//apply matinee pricing if applicable
		selectedTicket.applyMatineePrice();
		
		//fetch quantity
		System.out.print("Please Enter Quantity to Purchase: ");
		int quantity = in.nextInt();
		System.out.println();
		
		//add quantity of tickets to shopping cart
		for(int i = 0; i < quantity; i++) {
			cart.add(selectedTicket);
		}
		
		//make sure no minors are viewing R movies
		boolean childrenViewingR = temp.ageVerified(cart);
		//System.out.println(childrenViewingR);
		if(childrenViewingR == false) {
			System.out.println("Minors cannot see a rated R Movie");
			System.out.println("Cart Cleared Please try Again");
			purchase(p, td);
		}
		
		//confirm or deny purchase
		String confirmationMessage = "Confirm purchase of " + cart.size() + " " + cart.get(0).getType() + " tickets for the " + 
								cart.get(0).getShowTimeAsString() + " showing of " + cart.get(0).getMovie().getTitle() + "? (y/n)";
		System.out.println(confirmationMessage);
		String confirmation = in.next();
		
		if(confirmation.toLowerCase().equals("y")) {
			//card or cash
			System.out.println("Please Enter Payment Method (Cash or Card): ");
			in = new Scanner(System.in);
			String cardOrCash = in.nextLine();
			if(cardOrCash.toLowerCase().equals("card")) {
				System.out.println("Please Enter Card Number: ");
				String cardNum = in.nextLine();
				System.out.println("Please Enter CVC code");
				String cvc = in.next();
				System.out.println("Please Enter EXP date");
				String expDate = in.next();
			} else {
				System.out.println("Cash payment");
			}
		} else {
			System.out.println("Cart Cleared");
			purchase(p,td);
		}
		
		//temp person to store our stuff
		temp.setTicketList(cart);
		
		
		//apply deals for seniors/children/day specific to all applicable tickets
		temp.applyDeals();
		//print out confirmed purchase
		System.out.println("Thank you for your purchase");
		System.out.println(temp.getReceipt());
		
		//add all tickets in temp to the persons ticketList
		for(MovieTicket mt : temp.getTicketList()) {
			ArrayList<MovieTicket> tempTickList = p.getTicketList();
			tempTickList.add(mt);
			p.setTicketList(tempTickList);
		}
		
		System.out.println("Would you like to make another purchase? (y/n)");
		String anotherPurchase = in.next();
		if(anotherPurchase.toLowerCase().equals("y")) {
			System.out.println(td.viewMovies());
			purchase(p,td);
		} else {
			System.out.println("Enjoy your movie!");
			td.startScreen();
		}
		
		
		
	}
	
	//getters and setters
	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public void resetTime() {
		this.now = LocalDateTime.now();
	}
		
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public static void main(String[] args) throws IOException {
		PaymentDriver test = new PaymentDriver();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		System.out.println(test.getDayOfWeek());
		TheaterDriver testDriver = new TheaterDriver();
		
		//this will create duplicates of movies because they are imported from the txt files.
		Movie batman = new Movie("Batman Begins", "Super Hero");
		Movie minecraft = new Movie("MineCraft legend of Steve", "Kids");
		Movie minecraft2 = new Movie("MineCraft steve dies", "Kids");
		Movie notMinecraft = new Movie("Words and Stuff", "Action");
		Movie batman2 = new Movie("Batman", "Super Hero", "A testing movie about batman and shit", 12, 125, "R");
		
		testDriver.setAuthenticated(true);
		testDriver.addMovie(batman2);
		testDriver.addMovie(batman);
		testDriver.addMovie(minecraft);
		testDriver.addMovie(minecraft2);
		testDriver.addMovie(notMinecraft);
		
		testDriver.addPersonToList(new Person("test", "test", 19));
		
		testDriver.addTicketToList(new MovieTicket(12.00, batman2, 800));
		testDriver.addTicketToList(new MovieTicket(10.00, batman2, 1200));
		testDriver.addTicketToList(new MovieTicket(10.00, batman, 800));
		testDriver.addTicketToList(new MovieTicket(15.00, batman, 800));
		testDriver.addTicketToList(new MovieTicket(20.00, minecraft, 2200));
		testDriver.addTicketToList(new MovieTicket(14.99, minecraft2, 2300));
		
		testDriver.startScreen();
		
	}
	
}
