// --== CS400 File Header Information ==--
// Name: Xiaohan Zhu, Huong Nguyen
// Email: xzhu274@wisc.edu, htnguyen23@wisc.edu
// Team: KB Blue
// Role: Frontend Developer
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Frontend {
  private boolean[] isGenreSelected; //track which genres are selected/deselected
  private boolean[] isRatingSelected; //track which ratings are selected/deselected
  private int firstTimeEnterBasemode; //track whether stay in basemode or return from other modes
  private List<String> allGenres;
  private Backend impl;
  
  //constructor 
  public Frontend(Backend impl) {
    // init class fields
    this.impl = impl;
    this.allGenres = impl.getAllGenres();
    this.isGenreSelected = new boolean[allGenres.size()];
    this.isRatingSelected = new boolean[11];
    
    for (int i = 0; i < isRatingSelected.length; ++i) {
    isRatingSelected[i] = true; //all ratings are selected initially
    impl.addAvgRating(String.valueOf(i));
    
  }
    firstTimeEnterBasemode = 0;
  }
  
  public void run() {
    int state = 0;
    Scanner scan = new Scanner(System.in);
    // original main()
    /* state 0 --- base mode
     * state 1 --- genre mode
     * state 2 --- rating mode
     */   
    while (true) {
      if (state == 0) {
        System.out.println("Welcome to the base mode\n------------------------\nPress 'g' for genre select\nPress 'r' for rating select\nPress 'x' to quit the program\n------------------------");
        
        
        //display top three movies by rating
        //not display repeatedly after the user types in number to scroll
        if (firstTimeEnterBasemode == 0) { 
          
//          //System.out.println("firstTimeEnterBasemode");
//          //select all genres so that the firstTimeEnterBasemode shows top 3 movies by rating
//          for (int i = 0; i < isGenreSelected.length; ++i) {
//            isGenreSelected[i] = true; 
//            impl.addGenre(this.allGenres.get(i));
//          }
          List<MovieInterface> topThreeMovies = impl.getThreeMovies(0);
//          System.out.println("topThreeMovies: " + topThreeMovies.size());
          for(int i = 0; i < topThreeMovies.size(); i++)
          {
            System.out.println("[" +"Title: " + topThreeMovies.get(i).getTitle()); 
            System.out.print("  Genre(s):");
            for (int y = 0; y < topThreeMovies.get(i).getGenres().size(); y++) {
              if (y == topThreeMovies.get(i).getGenres().size() - 1) {
                System.out.println(" " + topThreeMovies.get(i).getGenres().get(y));
              } else {
                System.out.print(" " + topThreeMovies.get(i).getGenres().get(y) + ",");
              }
            }
            System.out.println("  Rating: " + topThreeMovies.get(i).getAvgVote());
            System.out.println("  Director: " + topThreeMovies.get(i).getDirector());
            System.out.println("  Description: " + topThreeMovies.get(i).getDescription() + " ]");
            System.out.println("");
          }
//        
//          //deselect all genres as we exit firstTimeEnterBasemode
//          for (int i = 0; i < isGenreSelected.length; ++i) {
//            isGenreSelected[i] = false;
//            impl.removeGenre(String.valueOf(i));
//           // System.out.println("deselecting all genres");
//          }
          
        }  
        System.out.println("Enter a number to scroll through the list of movies starting from that rank\n------------------------");
        
        String input = scan.nextLine();
        System.out.println(""); //print a line just for appearance purposes
        
        if (input.equals("g")) {
          state = 1;
        } else if (input.equals("r")){
          state = 2;
        } else if (input.equals("x")){
          return;
        } else {
          try {
            int input_int = Integer.parseInt(input);
            basemode(input_int);
          }
          catch (NumberFormatException e){
            System.out.println("Please enter either g or r or x or a valid number"); //check whether input is a valid integer
          }
        }
      }
        
      
      if (state == 1) {
        System.out.println("Below is the list of all genres and each genre is assigned a number."
            + "\nEnter the number to select/deselect genre");
        for (int i = 0; i < this.allGenres.size(); i++) {
          if ((i % 2 == 0)) {
            System.out.print("  " + i + "  " + this.allGenres.get(i).trim() + "\t\t\t");
          } else {
          System.out.println("" + i + "  " + this.allGenres.get(i).trim()); //display all genres and each genre is assigned a number
          }
        }
        System.out.println("Currently selected:");
        if (impl.getGenres().size() == 0) 
          System.out.println("  None");
        for (String genre : impl.getGenres()) {
          System.out.println("  " + genre);
          }
        System.out.println("\nEnter x to return to the base mode\n------------------------");
  
        String input = scan.nextLine();
        System.out.println(""); //print a line just for appearance purposes
        
        if (input.equals("r")) {
          state = 2;
        } else if (input.equals("x")){
          state = 0;
        } else {
          try {
            int input_int = Integer.parseInt(input);
            
            genremode(input_int);
          }
          catch (NumberFormatException e){
            System.out.println("Please enter either r or x or a valid number"); //check whether input is a valid integer
          }
          
        }
        
      }
        
      
      if (state == 2){
        System.out.println("Below is a list of ratings."
            + "\nSelect/deselect ratings by typing in integer number between 0 and 10");
        
        
        for (int i = 0; i <= 10; i++) {
          System.out.println(i); //display all ratings
        }   
        
        String input = scan.nextLine();
        System.out.println(""); //print a line just for appearance purposes

        if (input.equals("g")) {
          state = 1;
        } else if (input.equals("x")){
          state = 0;
        } else {
          try {
            int input_int = Integer.parseInt(input);
            
            ratingmode(input_int);
          }
          catch (NumberFormatException e){
            System.out.println("Please enter either g or x or a valid number"); //check whether input is a valid integer
          }
          
        }
      }

    
    }
    
    
  }
  
  //base mode
    private void basemode(int input) {

      // check that the input starting index is at least 0
      if(input < 0) {
        System.out.println("Please enter a number bigger than equal to 0");
        return;
      }
      List<MovieInterface> toPrint = impl.getThreeMovies(input);
    
      //display movie information
      if(toPrint.size() == 0)
      {
        System.out.println("No films matched your search query.");
      }
      for(int i = 0; i < toPrint.size(); i++)
      {
        System.out.println("[" +"Title: " + toPrint.get(i).getTitle()); 
        System.out.print("  Genre(s):");
        for (int y = 0; y < toPrint.get(i).getGenres().size(); y++) {
          if (y == toPrint.get(i).getGenres().size() - 1) {
            System.out.println(" " + toPrint.get(i).getGenres().get(y));
          } else {
          System.out.print(" " + toPrint.get(i).getGenres().get(y) + ",");
          }
        }
        System.out.println("  Rating: " + toPrint.get(i).getAvgVote());
        System.out.println("  Director: " + toPrint.get(i).getDirector());
        System.out.println("  Description: " + toPrint.get(i).getDescription() + " ]");
        System.out.println("");
      }
      
      firstTimeEnterBasemode = 1;
      
    }
    
    
    //genre mode
    private void genremode(int input) {
      
      //check whether input is in the range
      if(input >= this.allGenres.size() || input < 0) {
        System.out.println("Please enter a number in the range!");
        return;
      }
      
      // select/deselect and display
      isGenreSelected[input] = !isGenreSelected[input];
      String genre = this.allGenres.get(input);
      
      if (isGenreSelected[input] == true) {
        impl.addGenre(genre); //add genre if selected
      } else {
        impl.removeGenre(genre); //remove genre if deselected
      }
      
      List<String> selectedGenres = impl.getGenres();
      
      
      for (int i = 0; i < selectedGenres.size(); i++) {
        System.out.println(selectedGenres.get(i) + " has been selected.");
      }
      System.out.println("\n");
      
    
      firstTimeEnterBasemode = 0;
      
    }
    
    
    //rating mode
    private void ratingmode(int input) {
      
      //check whether input is in the range
      if(input > 10 || input < 0)
      {
        System.out.println("Please enter a value between 1-10.");
        return;
      }
      
      isRatingSelected[input] = !isRatingSelected[input]; //inverts value
      
      if (isRatingSelected[input] == true) {
        impl.addAvgRating(String.valueOf(input));//add rating if selected
        } else {
          impl.removeAvgRating(String.valueOf(input));//remove rating if deselected
        }
      
      
      //select/deselect and display 
          
          for (int i = 0; i < isRatingSelected.length; i++) {
            if(!isRatingSelected[i])
            {
              System.out.println(i + " has been deselected.");
            }else {
            System.out.println(i + " has been selected.");
            }
            
          }
          System.out.println("\n");
          
          firstTimeEnterBasemode = 0;
    }
    
  
  
 }
