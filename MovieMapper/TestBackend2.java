// --== CS400 File Header Information ==--
// Name: Huong Nguyen
// Email: htnguyen23@wisc.edu
// Team: KB Purple
// Group: Backend Developer
// TA: Keren Chen
// Lecturer: Gary Dahl, Florian
// Notes to Grader: <optional extra notes>


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * This class contains a set of tests for the back end of the Movie Mapper project.
 */
public class TestBackend2 {
  public static void main(String[] args) {
    (new TestBackend2()).runTests();
    System.out.println("DONE");
  }

  public void runTests() {
    // add all tests to this method
    if (this.testInitialNumberOfMovies()) {
      System.out.println("Test initial number of movies: PASSED\n-----");
    } else {
      System.out.println("Test initial number of movies: FAILED\n-----");
    }
    if (this.testGetAllGenres()) {
      System.out.println("Test get all genres: PASSED\n-----");
    } else {
      System.out.println("Test get all genres: FAILED\n-----");
    }
    if (this.testGetThreeMoviesInitial()) {
      System.out.println("Test get three movies (initial): PASSED\n-----");
    } else {
      System.out.println("Test get three movies (initial): FAILED\n-----");
    }
    if (this.testGetThreeMovies()) {
      System.out.println("Test get three movies (with rating and genre selected): PASSED\n-----");
    } else {
      System.out.println("Test get three movies (with rating and genre selected): FAILED\n-----");
    }
    if (this.testGetNumberOfMovies()) {
      System.out.println("Test get number of movies: PASSED\n-----");
    } else {
      System.out.println("Test get number of movies: FAILED\n-----");
    }
//    try {
//      if (this.testAddGenre()) {
//        System.out.println("Test add genre: PASSED\n-----");
//      } else {
//        System.out.println("Test add genre: FAILED\n-----");
//      }
//    } catch (IOException e) {
//      System.out.println("Test add genre: FAILED\n-----");
//      e.printStackTrace();
//    } catch (DataFormatException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
  }

  /**
   * This test instantiates the back end with three movies and tests whether the initial selection
   * is empty (getNumberOfMovies() returns 0). It passes when 0 is returned and fails in all other
   * cases, including when an exception is thrown.
   * 
   * @return true if the test passed, false if it failed
   */
  public boolean testInitialNumberOfMovies() {
    try {
      BackendInterface backendToTest = new Backend("moviesTest.csv");
      if (backendToTest.getNumberOfMovies() == 0) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the back end with three movies and tests whether the getAllGenres method
   * return the correct set of genres for those three movies.
   * 
   * @return true if the test passed, false if it failed
   */
  public boolean testGetAllGenres() {
    try {
      // instantiate once BackendInterface is implemented
      BackendInterface backendToTest = new Backend("moviesTest.csv");
      if (backendToTest.getAllGenres().size() == 7
        && backendToTest.getAllGenres().contains("Action")
        && backendToTest.getAllGenres().contains("Drama")
        && backendToTest.getAllGenres().contains("War")
        && backendToTest.getAllGenres().contains("Biography")
        && backendToTest.getAllGenres().contains("Comedy")
        && backendToTest.getAllGenres().contains("Crime")
        && backendToTest.getAllGenres().contains("Romance")) {
        // test passed
        return true;
      } else {
        // test failed
        System.out.println("\t" + backendToTest.getAllGenres().toString() + "\n\tsize: "
          + backendToTest.getAllGenres().size());
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the back end with three movies and tests whether the initial list
   * returned by getThreeMovies starting with the first movie (0) is empty. It passes when 0 is
   * returned and fails in all other cases, including when an exception is thrown.
   * 
   * @return true if the test passed, false if its failed
   */
  public boolean testGetThreeMoviesInitial() {
    try {
      BackendInterface backendToTest = new Backend("moviesTest.csv");
      if (backendToTest.getThreeMovies(0).size() == 0) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the back end with 3 movies, and tests if getNumberOfMoves() returns the
   * correct number of movies based on the selected set. It passes when 2 is returned and fails in
   * all other cases, including when an exception is thrown.
   * 
   * @return true if the test passed, false if otherwise
   */
  public boolean testGetNumberOfMovies() {
    try {
      BackendInterface backendToTest = new Backend("moviesTest.csv");
      backendToTest.addAvgRating("6");
      backendToTest.addAvgRating("7");
      backendToTest.addGenre("Drama");
      backendToTest.addGenre("War");
      int numMovies = backendToTest.getNumberOfMovies();
      if (numMovies == 2) {
        // test passed
        return true;
      } else {
        // test failed
        System.out.println("numMovies: " + numMovies);
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the back end with 3 movies, and tests if the list returned by
   * getThreeMovies() correctly contains the movies from the startingIndex. It passes when the size
   * of the list is 3 and the list contains the right movie title and is in descending order based
   * on ratings. It fails in all other cases, including when an exception is thrown.
   * 
   * @return true if the test passed, false if otherwise
   */
  public boolean testGetThreeMovies() {
    try {
      BackendInterface backendToTest = new Backend("moviesTest.csv");
      backendToTest.addAvgRating("5");
      backendToTest.addAvgRating("7");
      backendToTest.addGenre("Drama");
      backendToTest.addGenre("Crime");
      List<MovieInterface> resultList = backendToTest.getThreeMovies(0);
      if (resultList.size() == 1 && resultList.get(0).getTitle().equals("Dirt Music")) {
        // test passed
        return true;
      } else {
        // test failed
        System.out.println("\t" + resultList.get(0).getTitle() + "\n\tsize: " + resultList.size());
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  public boolean testAddGenre() throws IOException, DataFormatException {
    BackendInterface backendToTest = new Backend("moviesTest.csv");
    backendToTest.addGenre("Biography");
    backendToTest.addGenre("Crime");
    List<MovieInterface> resultList = backendToTest.getThreeMovies(0);
    if (resultList.size() == 2 && resultList.get(0).getTitle().equals("The Dirt") && resultList.get(1).getTitle().equals("Dirt Music")) {
      return true;
    } else {
 //      System.out.println("\t" + resultList.get(0).getTitle() + " , " + resultList.get(1).getTitle() + "\n\tsize: " + resultList.size());
      return false;
    }
  }
  
}
