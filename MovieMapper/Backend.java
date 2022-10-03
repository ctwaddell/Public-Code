// --== CS400 File Header Information ==--
// Name: Huong Nguyen, Elliott Weinshenker
// Email: htnguyen@wisc.edu, eweinshenker@wisc.edu
// Team: KB Purple
// Group: Backend Developer
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.DataFormatException;

public class Backend implements BackendInterface {
  private static MovieDataReader reader; 
  private static FileReader fr;
  //private static StringReader strReader;

  private MovieHashMap3<String, Integer> genreHM;
  private MovieHashMap3<String, Integer> ratingHM;


  private List<MovieInterface> movieList;
  private MovieInterface[] allMovies; // Array storing all Movie objects


  private ArrayList<String> ratingSelect;
  private ArrayList<String> genreSelect;

  public Backend(String fileName) throws IOException, DataFormatException {
    fr = new FileReader(fileName);
    reader = new MovieDataReader();
    movieList = reader.readDataSet(fr);
    ratingHM = new MovieHashMap3<String, Integer>();
    genreHM = new MovieHashMap3<String, Integer>();
    allMovies = new MovieInterface[movieList.size()];
    
    // adds extraneous amount of movies?
    for (int i = 0; i < movieList.size(); i++) {
      for (MovieInterface movie : movieList) { //iterating through the whole movieList for every i (2 for loops when there should be 1?)
        movie = movieList.get(i);
        allMovies[i] = movie;
        String rating = String.valueOf((int) Math.floor(movie.getAvgVote()));
        //System.out.println("RATING " + i);
        ratingHM.put(rating, i);
        for (String genre : movie.getGenres()) {
          //System.out.println("GENRE " + i);
          genreHM.put(genre, i);
        }
      }
    }
    
    genreSelect = new ArrayList<String>();
    ratingSelect = new ArrayList<String>();

  }

  //Constructor for StringReader
  public Backend(StringReader strReader) throws IOException, DataFormatException {
    reader = new MovieDataReader();
    movieList = reader.readDataSet(strReader);
    ratingHM = new MovieHashMap3<String, Integer>();
    genreHM = new MovieHashMap3<String, Integer>();
    allMovies = new MovieInterface[movieList.size()];
    
//    for (int i = 0; i < movieList.size(); i++) {
//      for (MovieInterface movie : movieList) {
//        allMovies[i] = movie;
//        String rating = String.valueOf((int) Math.floor(movie.getAvgVote()));
//        ratingHM.put(rating, i);
//        for (String genre : movie.getGenres()) {
//          genreHM.put(genre, i);
//        }
//      }
//    }
    
    MovieInterface movie;
    for (int i = 0; i < movieList.size(); i++) {
    movie = movieList.get(i);
    allMovies[i] = movie;
    String rating = String.valueOf((int) Math.floor(movie.getAvgVote()));
    ratingHM.put(rating, i);
       for (String genre : movie.getGenres()) {
         genreHM.put(genre, i);
       }
    }

//    int y = 0;
//    for (MovieInterface currMovie : movieList) {
//      System.out.println("movieList[" + y + "]: " + currMovie.getTitle());
//      y += 1;
//    }
//    int x = 0;
//    for (MovieInterface currMovie : allMovies) {
//      System.out.println("allMovies[" + x + "]: " + currMovie.getTitle());
//      x += 1;
//    }
    
    genreSelect = new ArrayList<String>();
    ratingSelect = new ArrayList<String>();

  }

  /**
   * Add genre to the list of filters
   */
  @Override
  public void addGenre(String genre) {
    if (genreHM.containsKey(genre) && !genreSelect.contains(genre)) {
      genreSelect.add(genre);
    }
  }

  /**
   * Add AvgRating to rating filters
   */
  public void addAvgRating(String rating) {
    if (ratingHM.containsKey(rating) && !ratingSelect.contains(rating)) {
      ratingSelect.add(rating);
    }
  }

  @Override
  public void removeGenre(String genre) {
    if (genreSelect.contains(genre))
      genreSelect.remove(genre);
  }

  @Override
  public void removeAvgRating(String rating) {
    if (ratingSelect.contains(rating))
      ratingSelect.remove(rating);
  }

  /**
   * The methods getGenres returns lists of the genres currently set.
   */
  @Override
  public List<String> getGenres() {
    return genreSelect;
  }

  /**
   * The methods getAvgRatings returns lists of the average ratings currently set.
   */
  @Override
  public List<String> getAvgRatings() {
    return ratingSelect;
  }

  /**
   * The method getNumberOfMovies returns the number of movies in the resulting set.
   */
  @Override
  public int getNumberOfMovies() {
//    if(genreSelect.size() == 0){  //Why is this necessary?
//      return 0; 
//    }
    return helperGetMovies().size();
  }

  /**
   * This method returns a Set of indices corresponding to the Movies that match all the genre and
   * rating selections.
   */
  private Set<Integer> helperGetMovies() {
    Set<Integer> ratingResult = new HashSet<Integer>();;
    Set<Integer> genreResult = new HashSet<Integer>();
    Set<Integer> uniqueMovies = new HashSet<Integer>();

    for (String genre : genreSelect) {
      genreResult.addAll(genreHM.getAll(genre));
      //System.out.println("do i reach here");
    }
    //System.out.print("genreResult: ");
    //System.out.println(genreResult.toString());

    for (String rating : ratingSelect) {
      ratingResult.addAll(ratingHM.getAll(rating));
      //System.out.println(rating + " aha");
    }
    //System.out.print("ratingResult: ");
    //System.out.println(ratingResult.toString());
    
    for (Integer index : genreResult) {
      if (ratingResult.contains(index))
        uniqueMovies.add(index);
    }

    // check that all movies in uniqueMovies satisfies genreSelect - something like this
    MovieInterface movie;
    boolean containsAll = true;
    List<Integer> toRemove = new ArrayList<Integer>();
    Iterator<Integer> index = uniqueMovies.iterator();
    Integer i = 0;
    while(index.hasNext()) {
      i = index.next();
      containsAll = true;
      movie = allMovies[i];
      for (String genre : genreSelect) {
        if (!movie.getGenres().contains(genre))
          containsAll = false;
      }
      if (!containsAll) {
        toRemove.add(i);
      }
    }
    for (Integer x : toRemove) {
      uniqueMovies.remove(x);
    }
    uniqueMovies.remove(null); // since a set can not contain the same value twice,
    // set.gremove(null) would be sufficient?
    return uniqueMovies;
  }

  
  /**
   * The method getAllGenres returns a list of all the genres from the data set.
   */
  @Override
  public List<String> getAllGenres() { //we still can fix this
    Set<String> allKeys = genreHM.getAllKeys();
    Iterator<String> iterator = allKeys.iterator();
    List<String> returnList = new ArrayList<String>();
    while(iterator.hasNext())
    {
      returnList.add(iterator.next());
    }
    /*
    for (String genre : genreHM.keySet()) {
      allKeys.add(genre);
    }
    //System.out.println("WOW!");
    //System.out.println(allKeys.toString()); */
    return returnList;
  }
  
  /**
   * The method getThreeMovies returns a list of three movies starting at (and including) the movie
   * at the startingIndex of the resulting set ordered by average movie rating in descending order.
   * If there are less than three movies left at the startingIndex, the resulting list contains all
   * those movies (the list may be empty).
   * 
   * @param startingIndex index in resulting set to return list of three movies with
   */
  // TODO: Look at use of ArrayList for average rating
  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    List<MovieInterface> movieSelect = new ArrayList<MovieInterface>();
    
//    for (String genre : genreSelect) {
//      System.out.println("Selected genre: " + genre);
//    }
//    for (String rating : ratingSelect) {
//      System.out.println("Selected rating: " + rating);
//    }
    
    if(genreSelect.size() == 0 && ratingSelect.size() == 0)
      return movieSelect;
    
    List<Integer> movieIndex = new ArrayList<Integer>();
    
    movieIndex.addAll(helperGetMovies()); //!HERE
    // System.out.println("movieIndex size: " + movieIndex.size());
    for (int i = 0; i < movieIndex.size(); i++) {
      movieSelect.add(allMovies[movieIndex.get(i)]);
    }
    Collections.sort(movieSelect); //, Collections.reverseOrder() - Ashkat's Movie.compareTo is sorting it in descending order
    // System.out.println("movieSelect size: " + movieSelect.size());    
    if(movieSelect.size() < 3) {
      return movieSelect;
    } 
    else if(startingIndex + 3 > movieSelect.size()) {
      return movieSelect.subList(startingIndex, movieSelect.size() - 1);
    }
    else
      return movieSelect.subList(startingIndex, startingIndex + 3);
  }
}
