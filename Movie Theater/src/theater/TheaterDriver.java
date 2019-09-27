package theater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class TheaterDriver {
	
	private ArrayList<Movie> movieList = new ArrayList<Movie>();
	private ArrayList<Person> personList = new ArrayList<Person>();
	private ArrayList<MovieTicket> ticketList = new ArrayList<MovieTicket>();
	private ScreenController screens = new ScreenController();
	private Boolean authenticated = false;
	
	//useless constructor
	public TheaterDriver() throws IOException {
		//System.out.println("Welcome to Joes Movie Emporium, buy some shit and get out");
		
		try {
			//this.importMovieData();
			//System.out.println("Movies Imported");
			this.importMovieData();
			this.importPersonData();
			this.importTicketData();
		} catch (FileNotFoundException e) {
			//empty on purpose, if file not found then its a new unsaved theater
			//data will need to be added manually
		}
	}
	
	//Screen that the user sees when first starting the program, looped back to multiple times throughout.
	public void startScreen() {
		Scanner in = new Scanner(System.in);
		System.out.println("New or Returning Customer: ");
		String answer = in.next();
		if(!(answer.toLowerCase().equals("new") || answer.toLowerCase().equals("returning") || answer.toLowerCase().equals("exist") || answer.toLowerCase().equals("existing"))){
			System.out.println("Invalid command");
			this.startScreen();
		}
		Person p = new Person();
		if(answer.toLowerCase().equals("new")) {
			p = this.createPerson();
			personList.add(p);
		} else if (answer.toLowerCase().equals("existing") || answer.toLowerCase().equals("exist") || answer.toLowerCase().equals("returning")){
			System.out.println("Please Enter First Name: ");
			String firstName = in.next();
			System.out.println("Please Enter Last Name: ");
			String lastName = in.next();
			System.out.println();
			int age = 0;
			if(this.personInList(firstName, lastName)) {
				p = this.getPersonFromList(firstName, lastName);
			} else {
				System.out.println("Person Not Found, You have been automatically registered");
				System.out.println("For verification purpose please enter your age (number): ");
				try{ 
					age = in.nextInt();
				} catch (Exception e) {
					System.out.println("Invalid input");
					this.startScreen();
				}
				p = new Person(firstName, lastName, age);
			}
		}
		screens.custFunctionController(p, this);
	}
	
	//Check login credentials
	//Username admin password password
	public void engageManagerMode(int count) {
		if(count == 3) {
			System.out.println("Max attempts reached, program terminated");
			this.close();
		}
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Username: ");
		String username = in.next();
		System.out.println("Enter Password: ");
		String password = in.next();
		if(username.equals("admin") && password.equals("password")) {
			authenticated = true;
			System.out.println("Welcome Manager, Manager Functions have been Enabled");
			screens.managerFunctionController(this);
		} else {
			System.out.println("Invalid login");
			engageManagerMode(count + 1);
		}
	}
	
	//manager close funciton, saves data to txt file by running for loop over each ArrayList and calling .saveData
	public void close() {
		try {
			
			PrintWriter movieWrite = new PrintWriter("save-movie-data.txt", "UTF-8");
			PrintWriter personWrite = new PrintWriter("save-person-data.txt", "UTF-8");
			PrintWriter ticketWrite = new PrintWriter("save-ticket-data.txt", "UTF-8");
			for(Movie m : movieList) {
				movieWrite.println(m.saveData());
			}
			
			for(Person p : personList) {
				personWrite.println(p.saveData());
			}
			
			for(MovieTicket t : ticketList) {
				ticketWrite.println(t.saveData());
			}
			movieWrite.close();
			personWrite.close();
			ticketWrite.close();
			System.out.println("State saved, goodbye");
			System.exit(0);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//import data, parses out the extra characters from each file and creates applicable objects
	public void importMovieData() throws IOException {
		File file = new File("save-movie-data.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String curData = "";
		ArrayList<ArrayList<String>> movieDataList = new ArrayList<ArrayList<String>>();

		
		try {
			while((curData = in.readLine()) != null) {
				
				//adding movie data to movieDataList
				ArrayList<String> singleMovie = new ArrayList<String>();
				int start = 0;
				int end = 0;
				String current = "";
				int dataCount = 0;
				while(dataCount < 6) {
					for(int i = 0; i < curData.length(); i++) {
						if(curData.charAt(i) == '(') {
							start = i + 1;
						}
						if(curData.charAt(i) == ')') {
							end = i;
							//System.out.println(curData.substring(start,end));
							current = curData.substring(start,end);
							singleMovie.add(current);
							dataCount += 1;
							//System.out.println(singleMovie.toString());
						}
					}
				}
				//System.out.println(singleMovie.toString());
				movieDataList.add(singleMovie);
			}
			
			for(ArrayList<String> cm : movieDataList) {
				//System.out.println("Test");
				String title = cm.get(0);
				String genre = cm.get(1);
				String description = cm.get(2);
				String stringPrice = cm.get(3);
				String stringRuntime = cm.get(4);
				String rating = cm.get(5);
				
				double price = Double.parseDouble(stringPrice);
				int runtime = Integer.parseInt(stringRuntime);
				this.addMovie(new Movie(title, genre, description, price, runtime, rating));
			}
			System.out.println("Movies Imported");
			in.close();
		} catch (IOException e) {
			//means that there is no data this is fine to do nothing
			in.close();
		}
	}
	
	//import data, parses out the extra characters from each file and creates applicable objects
	public void importPersonData() throws IOException {
		File file = new File("save-person-data.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String curData = "";
		ArrayList<ArrayList<String>> personDataList = new ArrayList<ArrayList<String>>();

		
		try {
			int count = 0;
			while((curData = in.readLine()) != null) {
				
				//adding movie data to movieDataList
				ArrayList<String> singlePerson = new ArrayList<String>();
				int start = 0;
				int end = 0;
				String current = "";
				int dataCount = 0;
				while(dataCount < 5) {
					for(int i = 0; i < curData.length(); i++) {
						if(curData.charAt(i) == '(') {
							start = i + 1;
						}
						if(curData.charAt(i) == ')') {
							end = i;
							//System.out.println(curData.substring(start,end));
							current = curData.substring(start,end);
							singlePerson.add(current);
							dataCount += 1;
							//System.out.println(singleMovie.toString());
						}
					}
				}
				//System.out.println(singlePerson.toString());
				personDataList.add(singlePerson);
			}
			
			for(ArrayList<String> cp : personDataList) {
				String firstName = cp.get(0);
				String lastName = cp.get(1);
				String ageString = cp.get(2);
				int age = Integer.parseInt(ageString);
				
				Person p = new Person(firstName, lastName, age);
				this.addPersonToList(p);
			}
			//System.out.println(this.getPersonList().toString());
			System.out.println("Persons imported");
			in.close();
		} catch (Exception e) {
			//means that there is no data this is fine to do nothing
			in.close();
		}
	}
	
	//import data, parses out the extra characters from each file and creates applicable objects
	public void importTicketData() throws IOException {
		File file = new File("save-ticket-data.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String curData = "";
		ArrayList<ArrayList<String>> ticketDataList = new ArrayList<ArrayList<String>>();

		
		try {
			int count = 0;
			while((curData = in.readLine()) != null) {
				
				//adding movie data to movieDataList
				ArrayList<String> singleTicket = new ArrayList<String>();
				int start = 0;
				int end = 0;
				String current = "";
				int dataCount = 0;
				while(dataCount < 5) {
					for(int i = 0; i < curData.length(); i++) {
						if(curData.charAt(i) == '(') {
							start = i + 1;
						}
						if(curData.charAt(i) == ')') {
							end = i;
							//System.out.println(curData.substring(start,end));
							current = curData.substring(start,end);
							singleTicket.add(current);
							dataCount += 1;
							//System.out.println(singleMovie.toString());
						}
					}
				}
				//System.out.println(singleTicket.toString());
				ticketDataList.add(singleTicket);
			}
			//System.out.println(ticketDataList.toString());
			for(ArrayList<String> ct : ticketDataList) {
				String ticketPriceString = ct.get(0);
				String movieTitle = ct.get(1);
				String movieGenre = ct.get(2);
				String movieDescription = ct.get(3);
				String moviePriceString = ct.get(4);
				String movieRunTimeString = ct.get(5);
				String movieRating = ct.get(6);
				String ticketShowTimeString = ct.get(7);
				
				double ticketPrice = Double.parseDouble(ticketPriceString);
				double moviePrice = Double.parseDouble(moviePriceString);
				int movieRunTime = Integer.parseInt(movieRunTimeString);
				int ticketShowTime = Integer.parseInt(ticketShowTimeString);
				
				this.addTicketToList(new MovieTicket(ticketPrice, new Movie(movieTitle, movieGenre, movieDescription, moviePrice, movieRunTime, movieRating), ticketShowTime));
			}
			
			//System.out.println(this.getTicketList().toString());
			System.out.println("Tickets Imported");
			in.close();
		} catch (Exception e) {
			//means that there is no data this is fine to do nothing
			in.close();
		}
	}
	
	//view all available tickets
	public String viewTicketList() {
		String s = "";
		for (MovieTicket t : ticketList) {
			s += t.toString() + " \n";
		}
		return s;
	}
	
	//View all moves in the theater
	public String viewMovies() {
		//for loop iterate over the movieList
		String s = "";
		for(Movie m : movieList) {
			s += m.getTitle() + " \n"; 
		}
		return s;
	}
	
	//Person related functions
	//View the ticket list of a person
	public String viewTickets(Person p) {
		return p.viewTickets();
	}
	
	//return modified person with new ticket
	public Person purchaseTicket(Person p, MovieTicket t) {
		p.buyTicket(t);
		return p;
	}
	
	//Add movie to the moveList in here
	public void addMovie(Movie m) {
		if(this.getAuthenticated()) {
			movieList.add(m);
			System.out.println("Movie added");
		} else {
			movieList.add(m);
		}
	}
	
	//assists in editing movie Titles
	public void editMovieGetter() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter title of Movie to edit: ");
		String title = in.nextLine();
		Movie edit = new Movie();
		for(int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getTitle().toLowerCase().equals(title.toLowerCase())) {
				edit = movieList.get(i);
				movieList.remove(i);
				break;
			}
		}
		Movie mov = edit.editMovie(this);
		movieList.add(mov);
		System.out.println();
		System.out.println("Updated Movie List: ");
		System.out.println(this.viewMovies());		
	}
	
	//sets authentication to false, not needed but keeps weird instances of code injection from happening
	public void enterCustomerMode() {
		this.setAuthenticated(false);
		screens.custFunctionController(new Person(), this);
		System.out.println("Manager Functions disabled");
	}
	//close also a function here
	
	//getters setters start here
	public ArrayList<Person> getPersonList() {
			return personList;
	}
	
	public void addTicketToList(MovieTicket t) {
		ticketList.add(t);
	}
	
	public void addPersonToList(Person p) {
		this.personList.add(p);
	}
	
	public void setPersonList(ArrayList<Person> personList) {
		this.personList = personList;
	}
	
	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}

	public ArrayList<MovieTicket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(ArrayList<MovieTicket> ticketList) {
		this.ticketList = ticketList;
	}
	
	public ScreenController getScreens() {
		return screens;
	}

	public void setScreens(ScreenController screens) {
		this.screens = screens;
	}
	
	//random ETC functions below here mostly data shuttles
	//returns if a person is in the list.
	public Boolean personInList(String firstName, String lastName) {
		for(Person p: personList){
			if(p.getFirstName().toLowerCase().equals(firstName.toLowerCase()) && p.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	//gets a specified person from the list.
	public Person getPersonFromList(String firstName, String lastName) {
		for(Person p: personList) {
			if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				return p;
			}
		}
		System.out.println("Person not found, please register below.");
		return this.createPerson();
	}
	
	//gets a specified movie object from the list by title
	public Movie getMovieFromList(String title) {
		Movie find = new Movie();
		for(Movie m: movieList) {
			if(m.getTitle().toLowerCase().equals(title)) {
				find = m;
			}
		}
		
		if (find.getTitle().equals("")) {
			System.out.print("no title error");
		}
		return find;
	}
	
	//makes a person gets user input.
	public Person createPerson() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("First Name: ");
		String firstName = in.next();
		
		System.out.println("Last Name: ");
		String lastName = in.next();
		
		System.out.println("Age: ");
		int age = 0;
		try {
			age = in.nextInt();
		} catch(Exception e) {
			System.out.println("Invalid input");
			this.createPerson();
		};
		
		return new Person(firstName, lastName, age);
	}

	//starts program
	public static void main(String[] args) throws IOException {
		TheaterDriver testDriver = new TheaterDriver();
		testDriver.startScreen();
		
		
	}
}
