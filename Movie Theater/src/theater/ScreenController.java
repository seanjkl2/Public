package theater;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScreenController {
	public ScreenController() {};
	
	//this function handles when customer mode is entered. Get input to figureout what command the customer is trying to execute and then called other funtions 
	//from there.
	public void custFunctionController(Person p, TheaterDriver td) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please Enter a Command (Purchase, ViewMovies, ViewTickets, EnterManagerMode, Restart, Close): ");
		String command = in.next();
		
		if(command.toLowerCase().equals("purchase")){
			PaymentDriver purchase = new PaymentDriver();
			PaymentDriver.purchase(p, td);
			
		} else if (command.toLowerCase().equals("viewmovies") || command.toLowerCase().equals("view movies")) {
			System.out.println();
			System.out.println(td.viewMovies());
			custFunctionController(p, td);
			
		} else if (command.toLowerCase().equals("viewtickets") || command.toLowerCase().equals("view tickets")) {
			System.out.println(p.viewTickets());
			custFunctionController(p, td);
			
			
		} else if (command.toLowerCase().equals("entermanagermode") || command.toLowerCase().equals("enter manager mode")) {
			td.engageManagerMode(0);
			
		} else if (command.toLowerCase().equals("restart")) {
			System.out.println("Logging out");
			td.startScreen();
			
		} else if (command.toLowerCase().equals("close")) {
			System.out.println("Goodbye");
			td.close();
			
		} else {
			System.out.println("invalid command try again");
			custFunctionController(p, td);
		}
	}
	
	//this function handles when the manager mode is entered. Gets input to figure out what command the manager would like to do and then calles
	//other functions based on that input.
	public void managerFunctionController(TheaterDriver td) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please Enter a Command(addMovie, editMovie, enterCustomerMode or close) ");
		String command = in.next();
		
		if(command.toLowerCase().equals("addmovie") || command.toLowerCase().equals("add movie")) {
			in = new Scanner(System.in);
			double price = 0;
			int runtime = 0;
			int showTime = 1200;
			System.out.println("Enter Movie Title");
			String title = in.nextLine();
			System.out.println("Enter Movie Genre");
			String genre = in.nextLine();
			System.out.println("Enter Description: ");
			String description = in.nextLine();
			try {
				System.out.println("Enter Price: ");
				price = in.nextDouble();
				System.out.println("Enter runtime in minutes: ");
				runtime = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter correct details.");
				managerFunctionController(td);
			}
			System.out.println("Enter Rating (G, PG, PG13, R)");
			String rating = in.next();
			try {
				System.out.println("Enter Showtime for movie (format: 1300 for 01:00 PM): ");
				showTime = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("invalid time option, try again");
				managerFunctionController(td);
			}
			Movie mov = new Movie(title, genre, description, price, runtime, rating);
			MovieTicket mt = new MovieTicket(price, mov, showTime);
			td.addMovie(mov);
			td.addTicketToList(mt);
			System.out.println(mov.getTitle());
			managerFunctionController(td);
			
		} else if(command.toLowerCase().equals("editmovie") || command.toLowerCase().equals("edit movie")) {
			td.editMovieGetter();
		
		} else if (command.toLowerCase().equals("entercustomermode") || command.toLowerCase().equals("enter customer mode")) {
			System.out.println("Manager Functions disabled");
			td.setAuthenticated(false);
			td.startScreen();
		
		} else if (command.toLowerCase().equals("close")) {
			td.close();
		} else {
			System.out.println("Invalid Command");
			managerFunctionController(td);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
	}

}
