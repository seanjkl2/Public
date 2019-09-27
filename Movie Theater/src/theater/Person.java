package theater;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Person {

	private String firstName;
	private String lastName;
	private int age;
	private boolean minor;
	private boolean senior;
	private ArrayList<MovieTicket> ticketList; //stores the tickets for whatever movies their seeing
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	LocalDateTime now = LocalDateTime.now();
	String dayOfWeek = now.getDayOfWeek().toString();
	
	//person constructor, based off age determines if senior/child.
	public Person(String fn, String ln, int age) {
		this.firstName = fn;
		this.lastName = ln;
		this.age = age;
		this.ticketList = new ArrayList<MovieTicket>();
		if(age >= 18) {
			this.minor = false;
		}
		if(age >= 65) {
			this.senior = true;
		}
		if(age < 65 && age > 18) {
			this.senior = false;
			this.minor = false;
		}
		
	}
	
	//testing constructor sets age to -1;
	public Person(String fn, String ln) {
		this.firstName = fn;
		this.lastName = ln;
		this.age = -1;
		this.minor = true;
	}
	
	//blank for testing purposes
	public Person() {};
	
	//adds a ticket to the person ticketList
	public void buyTicket(MovieTicket t) {
		ticketList.add(t);
	}
	
	//prints out the tickets in the customers cart.
	public String getReceipt() {
		String s = "Your Purchase: \n";
		double sum = 0;
		for(MovieTicket mt : ticketList) {
			sum += mt.getPrice();
			s += mt.getMovie().getTitle() + " at " + mt.getShowTimeAsString() + " price: $" + mt.getPrice() + "\n";
		}
		s += "Total: $" + sum;
		return s;
	}
	
	//looks through the ticketList and apply deals that are applicable to them.
	public Person applyDeals() {
		this.now = LocalDateTime.now();
		String Day = this.now.getDayOfWeek().toString();
		MovieTicket tick = new MovieTicket();
		
		//apply free senior wednesday
		if(Day.equals("WEDNESDAY")) {
			for(int i = 0; i < ticketList.size(); i++) {
				MovieTicket mt = ticketList.get(i);
				if(mt.getType().equals("senior")) {
					mt.setPrice(0);
					System.out.println("Wednesday Senior Deal Applied.");
				}
			}
		}
		
		//apply bogo for kid tickets
		if(Day.equals("SATURDAY")) {
			int count = 0;
			for(int i = 0; i < ticketList.size(); i++) {
				MovieTicket mt = ticketList.get(i);
				if(mt.getType().equals("child") && count % 2 == 0) {
					mt.setPrice(0);
					count++;
				} else if (mt.getType().equals("child")) {
					count++;
				}
			}
			System.out.println("Saturday Child Deals Applied.");
		}
		
		//apply half off child with adult
		int adultCount = 0;
		for(int i = 0; i < ticketList.size(); i++) {
			MovieTicket mt = ticketList.get(i);
			if(mt.getType().equals("adult")) {
				adultCount++;
			}
		}
		if(adultCount >= 2) {
			for(int i = 0; i < ticketList.size(); i++) {
				MovieTicket mt = ticketList.get(i);
				if(mt.getType().equals("child")) {
					mt.setPrice(mt.getPrice()/2);
				}
			}
			System.out.println("2 Adults With Children Deal Applied.");
		}
		
		//apply matinee pricing
		for(int i = 0; i < ticketList.size(); i++) {
			MovieTicket mt = ticketList.get(i);
			if(mt.getShowTime() >= 1200 && mt.getShowTime() < 1655) {
				mt.setPrice(mt.getPrice()/2);
			}
			System.out.println("Matinee Pricing Applied.");
		}
		
		for(int i = 0; i < ticketList.size(); i++) {
			MovieTicket mt = ticketList.get(i);
			if(mt.getPrice() < 5.00) {
				mt.setPrice(5.00);
			}
		}
		
		//deal to charge people named adeel more.
		try{
			if(this.getFirstName().toLowerCase().equals("adeel")) {
				for(int i = 0; i < ticketList.size(); i++) {
					MovieTicket mt = ticketList.get(i);
					mt.setPrice(mt.getPrice() * 3);
				}
				System.out.println("Adeel Tax Applied");
			}
			
			return this;
		} catch (Exception e) {
			return this;
		}
	}
	
	//return true/false if child trying to buy rated R movie
	public boolean ageVerified(ArrayList<MovieTicket> lmt) {
		try {
			Boolean valid = true;
			for(MovieTicket mt : lmt) {
				if((mt.getMovie().getRating().toLowerCase().equals("r") && mt.getType().toLowerCase().equals("child")) || (mt.getMovie().getRating().toLowerCase().equals("r") && mt.getType().toLowerCase().equals("children")) ) {
					valid = false;
				}
			}
			return valid;
		} catch (Exception e) {
			System.out.println("This movie does not have a rating");
			return true;
		}
	}
	
	//prints out relevant information about what each ticket in the list is.
	public String viewTickets() {
		
		String s = "";
		for(MovieTicket mt : ticketList) {
			Movie m = mt.getMovie();
			s += m.getTitle() + " @ " + mt.getShowTime() + " $" + mt.getPrice() + " \n";
			
		}
		return s;
	}
	
	//write contents of all instance variables to a string, return that string
	public String saveData() {
		String s = "(";
		s += this.getFirstName() + ") (";
		s += this.getLastName() + ") (";
		s += this.getAge()+ ") (";
		
		if(this.isMinor()) {
			s += "true";
		} else {
			s += "false";
		}
		
		s += ") (";
		
		if(this.isSenior()) {
			s += "true";
		} else {
			s += "false";
		}
		
		s += ") (";
		
		for(MovieTicket mt: ticketList) {
			mt.saveData();
			s += " ";
		}
		s += ")";
		
		return s;
	}
	
	//getters and setters from here on out.
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMinor() {
		return minor;
	}

	public void setMinor(boolean minor) {
		this.minor = minor;
	}

	public ArrayList<MovieTicket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(ArrayList<MovieTicket> ticketList) {
		this.ticketList = ticketList;
	}
    
	public boolean isSenior() {
		return senior;
	}

	public void setSenior(boolean senior) {
		this.senior = senior;
	}

	@Override
	public String toString() {
		//debug only
		String s = "";
		s = this.getFirstName() + " " + this.getLastName() + " " + this.getAge();
		return s;
	}
	
	public static void main(String args[]) throws IOException {
		TheaterDriver testDriver = new TheaterDriver();
				
		Movie batman = new Movie("Batman Begins", "Super Hero", "Batman Movie", 13, 125, "G");
		Movie minecraft = new Movie("MineCraft legend of Steve", "Kids", "Kid Friendly chick flick", 14, 180, "R");
		Movie minecraft2 = new Movie("MineCraft steve dies", "Kids", "slightly less kid friendly chick flick", 20, 210, "PG13");
		Movie notMinecraft = new Movie("Words and Stuff", "Action", "i dont even know what the fuck to put here", 30, 60, "PG");
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
