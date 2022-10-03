package TicTacToe;
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Fall 2020
//
// Author:   Casey Waddell
// Email:    ctwaddell@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

public class COVIDTrackerTester
{
  
  // toString reads two arrays and converts them into a readable string separated by a '|'
  // @param a, String array 1 to add to the output
  // @param b, String array 2 to add to the output
  // @return r, String representing the arrays as a String separated by a '|'
  private static String toString(String[] a, String[] b)
  {
    String r = "";
    for(int j = 0; j < a.length; j++) //these two for loops print what the array looks like
    {                                   //for visual verification of test
      r += a[j] + ", ";
    }
    r += "| ";
    for(int j = 0; j < b.length; j++)
    {
      r += b[j] + ", ";
    }
    return r;
  }
  
  // testAddTest checks to see if the addTest() method works as expected
  // @return true if the addTest() works as expected and false if the addTest() doesn't work
  public static boolean testAddTest()
  {
    // two empty arrays -> true; also checking that arrays were updated properly
    String[] pos = new String[2];
    String[] neg = new String[2];
    if (!COVIDTracker.addTest(pos, neg, "AB1234", false) || neg[0] == null)
      return false;
    if (!COVIDTracker.addTest(pos, neg, "CD2345", true) || pos[0] == null)
      return false;
    // two arrays with space -> true
    if (!COVIDTracker.addTest(pos, neg, "CD2345", false) || neg[1] == null)
      return false;
    // one full array but adding to one with space -> true
    if (!COVIDTracker.addTest(pos, neg, "EF3456", true) || pos[1] == null)
      return false;
    // one array with space but adding to full one -> false
    String[] pos2 = new String[2];
    if (COVIDTracker.addTest(pos2, neg, "EF3456", false))
      return false;
    // two full arrays -> false
    if (COVIDTracker.addTest(pos, neg, "EF3456", true))
    return false;
    return true;
  }
  /*
   * testRemoveIndividual tests different instances in which an error might occur
   * Block 1 searches for ID that doesn't exist in the array
   * Block 2 searches for ID when all instances are said ID
   * Block 3 searches for ID when ID is only in one array
   * Block 4 does the same as Block 3, but for the other array
   * Block 5 searches for ID when array is only null values
   * Block 6 searches for ID in a practical mix of null, variable names, etc.
   * @return boolean true if all 6 blocks succeeded, and false if otherwise
   */
  public static boolean testRemoveIndividual()
  {
    int successCounter = 0;
    String resultString = "";
    String[] pos = new String[2];
    String[] neg = new String[2];
    
    //Block 1
    pos[0] = "J";
    pos[1] = "O";
    neg[0] = "K";
    neg[1] = "E";
    System.out.println("Test 1");
    resultString = toString(pos, neg);
    System.out.println(resultString);
    if(!COVIDTracker.removeIndividual(pos, neg, "R")) //if true, test succeeds
    { 
      System.out.println("Test 1 success");
      successCounter++;
    }
    resultString = toString(pos, neg);
    System.out.println(resultString + "\n");
    
    //Block 2
    pos[0] = "J";
    pos[1] = "J";
    neg[0] = "J";
    neg[1] = "J";
    System.out.println("Test 2");
    resultString = toString(pos, neg);
    System.out.println(resultString);
    if(COVIDTracker.removeIndividual(pos, neg, "J"))
    {
      System.out.println("Test 2 success");
      successCounter++;
    }
    resultString = toString(pos, neg);
    System.out.println(resultString + "\n");
    
    //Block 3
    pos[0] = "J";
    pos[1] = "O";
    neg[0] = "K";
    neg[1] = "E";
    System.out.println("Test 3");
    resultString = toString(pos, neg);
    System.out.println(resultString);
    if(COVIDTracker.removeIndividual(pos, neg, "J"))
    {
      System.out.println("Test 3 success");
      successCounter++;
    }
    resultString = toString(pos, neg);
    System.out.println(resultString + "\n");
    
    //Block 4
    pos[0] = "J";
    pos[1] = "O";
    neg[0] = "K";
    neg[1] = "E";
    System.out.println("Test 4");
    resultString = toString(pos, neg);
    System.out.println(resultString);
    if(COVIDTracker.removeIndividual(pos, neg, "K"))
    {
      System.out.println("Test 4 success");
      successCounter++;
    }
    resultString = toString(pos, neg);
    System.out.println(resultString + "\n");
    
    //Block 5
    pos[0] = null;
    pos[1] = null;
    neg[0] = null;
    neg[1] = null;
    System.out.println("Test 5");
    resultString = toString(pos, neg);
    System.out.println(resultString);
    if(!COVIDTracker.removeIndividual(pos, neg, "J")) //should return false but revert true
    {
      System.out.println("Test 5 success");
      successCounter++;
    }
    resultString = toString(pos, neg);
    System.out.println(resultString + "\n");
    
    //Block 6
    pos[0] = "J";
    pos[1] = null;
    neg[0] = null;
    neg[1] = "O";
    System.out.println("Test 6");
    resultString = toString(pos, neg);
    System.out.println(resultString);
    if(COVIDTracker.removeIndividual(pos, neg, "J"))
    {
      System.out.println("Test 6 success");
      successCounter++;
    }
    resultString = toString(pos, neg);
    System.out.println(resultString + "\n");
    
    System.out.println(successCounter);
    if(successCounter >= 6) return true;
    else return false;
  }
  
  /*
   * testGetPopStats tests to see if the getPopStats() method in COVIDTracker works as it should
   * this method runs through various situations which may be problematic for the code and makes
   * sure it functions given difficult or unexpected datasets, or at all of that matter
   * Block 1 gives arrays with all the same IDs
   * Block 2 gives arrays with different values between the two, but the same in itself
   * Block 3 gives a split null / variable array for each
   * Block 4 gives an all null array
   * Block 5 gives unequal array lengths
   * Block 6 gives a random, believable, practical dataset
   * @return String showing the results of each test, since it would take a human to verify,
   * or a very long, complex .equals comparison
   */
  public static String testGetPopStats()
  {
    String r = "";
    String results = "";
    
    // Block 1 - All same ID - expected 3, 3, .5, 1, 1
    {
    String[] pos = {"x", "x", "x"};
    String[] neg = {"x", "x", "x"};
    results = COVIDTracker.getPopStats(pos, neg);
    }
    r += "Block 1\n" + results + "\n";
    
    // Block 2 - Polarized arrays - expected 3, 3, .5, 2, .5
    {
      String[] pos = {"x", "x", "x"};
      String[] neg = {"y", "y", "y"};
      results = COVIDTracker.getPopStats(pos, neg);
    }
    r += "Block 2\n" + results + "\n";
    
    //Block 3 - Split null - expected 2, 2, .5, 2, .5
    {
      String[] pos = {"x", null, "x"};
      String[] neg = {"y", null, "y"};
      results = COVIDTracker.getPopStats(pos, neg);
    }
    r += "Block 3\n" + results + "\n";
    
    // Block 4 - All null - expected 0, 0, undefined, 0, undefined
    {
      String[] pos = {null, null, null};
      String[] neg = {null, null, null};
      results = COVIDTracker.getPopStats(pos, neg);
    }
    r += "Block 4\n" + results + "\n";
    
    // Block 5 - Unequal arrays - expected 5, 3, .625, 2, .5
    {
      String[] pos = {"x", "x", "x", "x", "x"};
      String[] neg = {"y", "y", "y"};
      results = COVIDTracker.getPopStats(pos, neg);
    }
    r += "Block 5\n" + results + "\n";
    
    // Block 6 - Randomonium - expected 4, 4, .5, 6, .666
    {
      String[] pos = {"x", "y", "u", null, "24"};
      String[] neg = {"y", null, null, null, "COMPSCI300", "r", "x"};
      results = COVIDTracker.getPopStats(pos, neg);
    }
    r += "Block 6\n" + results + "\n";
    
    return r;
  }

  /*
   * testGetIndividualStats checks if getIndividualStats works as expected through various and
   * potentially problematic array inputs
   * @return String representing the data written by getIndividualStats requires human verification
   * or a super complex .equals comparison
   * Block 1 tests for an individual with all positive no negative
   * Block 2 tests for an individual with all negative no positive
   * Block 3 tests for an individual with both positive and negative 
   * Block 4 tests for an empty array
   * Block 5 tests for an array not containing the id
   */
  public static String testGetIndividualStats()
  {
    String r = "";
    String results = "";
    
    // Block 1 - all positive no negative - expected 0, 3, 3
    {
      String[] pos = {"x", "x", "x"};
      String[] neg = {"y", "y", "y"};
      results = COVIDTracker.getIndividualStats(pos, neg, "x");
    }
    r += "Block 1\n" + results + "\n";
    
    // Block 2 - all negative no positive - expected 3, 0, 3
    {
      String[] pos = {"y", "y", "y"};
      String[] neg = {"x", "x", "x"};
      results = COVIDTracker.getIndividualStats(pos, neg, "x");
    }
    r += "Block 2\n" + results + "\n";
    
    // Block 3 - all positive all negative - expected 3, 3, 6
    {
      String[] pos = {"x", "x", "x"};
      String[] neg = {"x", "x", "x"};
      results = COVIDTracker.getIndividualStats(pos, neg, "x");
    }
    r += "Block 3\n" + results + "\n";
    
    // Block 4 - empty array - expected 0, 0, 0
    {
      String[] pos = new String[10];
      String[] neg = new String[10];
      results = COVIDTracker.getIndividualStats(pos, neg, "x");
    }
    r += "Block 4\n" + results + "\n";
    
    // Block 5 - non-inclusive ID array - 0, 0, 0
    {
      String[] pos = {"x", "x", "x"};
      String[] neg = {"y", "y", "y"};
      results = COVIDTracker.getIndividualStats(pos, neg, "z");
    }
    r += "Block 5\n" + results + "\n";
    
    return r;
  }
  public static void main(String[] args)
  {
    
    System.out.println(testGetIndividualStats());

    System.out.println(testAddTest());
    System.out.println(testRemoveIndividual());
    System.out.println(testGetPopStats());
    String[] pos = {"a", "b", "c", "d", "e"};
    String[] neg = {"f", "g", "h", "i", "j"};
    System.out.println(COVIDTracker.getPopStats(pos, neg));
  }
}
