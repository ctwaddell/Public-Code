// --== CS400 File Header Information ==--
// Name: Xiaohan Zhu
// Email: xzhu274@wisc.edu
// Team: KB Blue
// Role: Frontend Developer
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: N/A


import java.io.IOException;
import java.util.zip.DataFormatException;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.zip.DataFormatException;
//
public class Main {
	
	public static void main(String[] args) {
		Backend backend;
		try {
			backend = new Backend("movies.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("The file type is not supported");
			return;
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("The data format is not supported");
			return;
		}
		Frontend frontend = new Frontend(backend);
		frontend.run();
	}
	
//	private static boolean[] isGenreSelected; //track which genres are selected/deselected
//	private static boolean[] isRatingSelected; //track which ratings are selected/deselected
//	private static Backend impl;
//	private static int firstTimeEnterBasemode; //track whether stay in basemode or return from other modes
//	
//	public static void main(String[] args) {
//	
//		
//		
//		// take a CSV file 
//		try {
//			File file = new File("movies.csv");
//			Scanner data = new Scanner(file);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			System.out.println("File not found");
//			e1.printStackTrace();
//			return;
//		}
//		
//		int state = 0;
//		Scanner scan = new Scanner(System.in);
//		
//		
//		
//	// initialize class fields
//		try
//		{
//		  impl = new Backend("movies.csv"); //backend with Stringreader? how does it work?
//		}
//		catch(DataFormatException e)
//		{
//		  System.out.println("The data format is not supported");
//		}
//		catch(IOException e)
//		{
//		  System.out.println("The file type is not supported");
//		}
//		List<String> allGenres = impl.getAllGenres();
//		
//		
//		isGenreSelected = new boolean[allGenres.size()];
//	
//		isRatingSelected = new boolean[11];
//		for (int i = 0; i < isRatingSelected.length; ++i) {
//			isRatingSelected[i] = true; //all ratings are selected initially
//			impl.addAvgRating(String.valueOf(i));
//		}
//		
//		firstTimeEnterBasemode = 0;
//		
//		/* state 0 --- base mode
//		 * state 1 --- genre mode
//		 * state 2 --- rating mode
//		 */		
//		while (true) {
//			if (state == 0) {
//				System.out.println("Welcome to the base mode\nPress 'g' for genre select\nPress 'r' for rating select\nPress 'x' to quit the program");
//				
//				
//				//display top three movies by rating
//				//not display repeatedly after the user types in number to scroll
//				if (firstTimeEnterBasemode == 0) { 
//				List<MovieInterface> topThreeMovies = impl.getThreeMovies(0);
//				for(int i = 0; i < topThreeMovies.size(); i++)
//					{
//					  System.out.println("[");
//					  System.out.println("Title: " + topThreeMovies.get(i).getTitle());				  
//					  System.out.println("Director: " + topThreeMovies.get(i).getDirector());
//					  System.out.println("Description: " + topThreeMovies.get(i).getDescription());
//					  System.out.println("]");
//					  
//					}
//				
//				}  
//				System.out.println("Enter a number to scroll through the list of movies starting from that rank");
//				
//				String input = scan.nextLine();
//				if (input.equals("g")) {
//					state = 1;
//				} else if (input.equals("r")){
//					state = 2;
//				} else if (input.equals("x")){
//					return;
//				} else {
//					try {
//						int input_int = Integer.parseInt(input);
//						basemode(input_int);
//					}
//					catch (NumberFormatException e){
//						System.out.println("Please enter either g or r or x or a valid number"); //check whether input is a valid integer
//					}
//				}
//			}
//				
//			
//			if (state == 1) {
//				System.out.println("Below is the list of all genres and each genre is assigned a number."
//						+ "\nEnter the number to select/deselect genre");
//				for (int i = 0; i < impl.getAllGenres().size(); i++) {
//					System.out.println(impl.getAllGenres().get(i) + " " +i); //display all genres and each genre is assigned a number
//				}
//				
//			
//				System.out.println("Enter x to return to the base mode");
//	
//				String input = scan.nextLine();
//				if (input.equals("r")) {
//					state = 2;
//				} else if (input.equals("x")){
//					state = 0;
//				} else {
//					try {
//						int input_int = Integer.parseInt(input);
//						
//						genremode(input_int);
//					}
//					catch (NumberFormatException e){
//						System.out.println("Please enter either r or x or a valid number"); //check whether input is a valid integer
//					}
//					
//				}
//				
//			}
//				
//			
//			if (state == 2){
//				System.out.println("Below is a list of ratings."
//						+ "\nSelect/deselect ratings by typing in integer number between 0 and 10");
//				
//				
//				for (int i = 0; i <= 10; i++) {
//					System.out.println(i); //display all ratings
//				}		
//				
//				String input = scan.nextLine();
//
//				if (input.equals("g")) {
//					state = 1;
//				} else if (input.equals("x")){
//					state = 0;
//				} else {
//					try {
//						int input_int = Integer.parseInt(input);
//						
//						ratingmode(input_int);
//					}
//					catch (NumberFormatException e){
//						System.out.println("Please enter either g or x or a valid number"); //check whether input is a valid integer
//					}
//					
//				}
//			}
//
//		
//		}
//		
//		
//	}
//	
//	
//	//base mode
//	private static void basemode(int input) {
//
//		// check that the input starting index is at least 0
//		if(input < 0) {
//			System.out.println("Please enter a number bigger than equal to 0");
//			return;
//		}
//		List<MovieInterface> toPrint = impl.getThreeMovies(input);
//	
//		//display movie information
//		for(int i = 0; i < toPrint.size(); i++)
//		{
//		  System.out.println("[");
//		  System.out.println("Title: " + toPrint.get(i).getTitle());				  
//		  System.out.println("Director: " + toPrint.get(i).getDirector());
//		  System.out.println("Description: " + toPrint.get(i).getDescription());
//		  System.out.println("]");
//		}
//		
//		firstTimeEnterBasemode = 1;
//		
//	}
//	
//	
//	//genre mode
//	private static void genremode(int input) {
//		
//		//check whether input is in the range
//		if(input >= impl.getAllGenres().size() || input < 0) {
//			System.out.println("Please enter a number in the range!");
//			return;
//		}
//		
//		// select/deselect and display
//		isGenreSelected[input] = !isGenreSelected[input];
//		String genre = impl.getAllGenres().get(input);
//		
//		if (isGenreSelected[input] == true) {
//			impl.addGenre(genre); //add genre if selected
//		} else {
//			impl.removeGenre(genre); //remove genre if deselected
//		}
//		
//		List<String> selectedGenres = impl.getGenres();
//		
//		
//		for (int i = 0; i < selectedGenres.size(); i++) {
//			System.out.println(selectedGenres.get(i) + " has been selected.");
//		}
//		System.out.println("\n\n");
//		
//	
//		firstTimeEnterBasemode = 0;
//		
//	}
//	
//	
//	//rating mode
//	private static void ratingmode(int input) {
//		
//		//check whether input is in the range
//		if(input > 10 || input < 0)
//		{
//		  System.out.println("Please enter a value between 1-10.");
//		  return;
//		}
//		
//		isRatingSelected[input] = !isRatingSelected[input]; //inverts value
//		
//		if (isRatingSelected[input] == true) {
//			impl.addAvgRating(String.valueOf(input));//add rating if selected
//			} else {
//				impl.removeAvgRating(String.valueOf(input));//remove rating if deselected
//			}
//		
//		
//		//select/deselect and display 
//				
//				for (int i = 0; i < isRatingSelected.length; i++) {
//				  if(!isRatingSelected[i])
//				  {
//				    System.out.println(i + " has been deselected.");
//				  }else {
//					System.out.println(i + " has been selected.");
//				  }
//					
//				}
//				System.out.println("\n\n");
//				
//				firstTimeEnterBasemode = 0;
//	}
//	
//	
}
