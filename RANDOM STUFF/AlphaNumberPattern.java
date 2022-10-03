///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Casey Waddell
// Campus ID: 9082630956
// WiscEmail: ctwaddell@wisc.edu
////////////////////////////////////////////////////////////////////////////////

/**
 * This class implements recursive methods to create palindromic (mirrored) 
 * numerical sequences.
 * 
 * TODO: Complete the implementation of the three methods below: 
 *       1. Write simpleNumPattern()
 *       2. Write numPattern()
 *       3. Write testnumPattern()
 *
 */
public class AlphaNumberPattern 
{

  /**
   * Creates a simple number pattern, with the numbers going backward to zero
   * and then forward to the provided start number, resulting into a 
   * mirrored/palindromic sequence of numbers.
   * 
   * You do NOT need to consider the case where the provided input number
   * is not positive
   * 
   * @param number a positive integer
   * @return a palindromic string of numbers beginning and ending with the 
   * provided start number, e.g., if number is 5, the returned string 
   * will be "5 4 3 2 1 0 1 2 3 4 5"
   * The different numbers in the returned string must be separated by 
   * only one space.
   */
  public static String simpleNumPattern(int number)
  {
    String result = "";
    // TODO: Base case
    if(number == 0) return "0";
    // TODO: recursive case: build recursively the simple number pattern 
    // related to the provided input by adding this number onto 
    // either end of a recursive string of the other numbers.
    else
    {
      result = number + " " + simpleNumPattern(number-1) + " " + number;
    }
    // Debugging suggestion: uncomment this statement to see what you're returning
    // System.out.println(result);
    return result;
  }

  /**
   * Recursive method to return the following number pattern as a string. 
   * Given a positive integer as input number, subtract another positive 
   * integer continually until 0 or negative value is reached, then
   * continually add the second integer until the first integer is again 
   * reached. If a negative value is reached, it must be excluded from the
   * returned number pattern. The latter must contain only a sequence of 
   * positive integers.
   * 
   * You do NOT need to consider the case where the provided input number is not positive
   * You do NOT need to consider the case where the provided input step is not positive
   * 
   * @param number a positive integer
   * @param step   a positive integer to subtract
   * @return a string representation of the number pattern according to 
   *         the above description. 
   *         E.g. (12, 3) => "12 9 6 3 0 3 6 9 12". 
   *         E.g. (11, 3) => "11 8 5 2 5 8 11". 
   *         The different numbers in the resulted string must be separated 
   *         by only one space.
   */
  public static String numPattern(int number, int step) 
  {
    String result = "";
    // TODO: Base case(s) 
    if(number == 0)
    {
      return "0";
    }
    if(number < 0)
    {
      int returnInt = number + step;
      return (returnInt + "");
    }
    
    // TODO: Recursive case: build recursively the stepped number pattern 
    // related to the provided input by adding this number onto 
    // either end of a recursive string of the other numbers, using the 
    // provided step.
    int tempNum = number;
    number = number - step;
    if(number >= 0)
    {
      result += tempNum + " " + numPattern(number, step) + " " + tempNum;
    }
    else
    {
      result += tempNum;
    }
    
    // Debugging suggestion: uncomment this statement to see what you're returning
    // System.out.println(result)
    return result; // output returned
  }
  
  /**
   * Checks the correctness of the implementation of the simpleNumPattern() method
   * @return true if the method passes this test and false otherwise
   */
  public static boolean testSimpleNumPattern() 
  {
    try
    {
      // test scenario 1
      String expectedOutput = "5 4 3 2 1 0 1 2 3 4 5";
      if(!simpleNumPattern(5).equals(expectedOutput))
        return false;
      // test scenario 2 (let's consider a larger number)
      int number = 20;
      // let's build the output
      String decreasingHalf = "20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1";
      String increasingHalf = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20";
      expectedOutput = decreasingHalf + " " + 0 + " " + increasingHalf;
      if(!simpleNumPattern(20).equals(expectedOutput))
        return false;
    
    }
    catch(StackOverflowError e1) 
    {
      System.out.println("Problem detected! StackOverflowError thrown from "
          + "your recursive method calls.");
      return false;     
    }
    catch(Exception e2)
    {
      return false; // no exception is expected to be thrown
    }
    return true; // this statement was added to make the code compile without errors
  }

  /**
   * Checks the correctness of the implementation of the numPattern() method
   * @return true if the method passes this test and false otherwise
   */
  public static boolean testNumPattern()
  {
    try
    {
      // Note that your test scenarios must use only positive integers 
      // for number and step values
      
      // TODO 
      // 1. Define a first test scenario where the resulting output of 
      //    numPattern(number, step) is mirrored with respect to 0.
      //    DO NOT consider the scenario (12, 3) provided in the method header 
      //    of the numPattern method. Define a different scenario.
      String expectedResult1 = "10 8 6 4 2 0 2 4 6 8 10";
      String result1 = numPattern(10, 2);
      
      // 2. Check that the output matches the expected one
      if(!expectedResult1.equals(result1))
      {
        return false;
      }
      
      // 3. Define a second test scenario where the resulting output of 
      //    numPattern(number, step) is mirrored with respect to a non-zero
      //    positive integer. 
      //    DO NOT consider the scenario (11, 3) provided in the method header 
      //    of the numPattern method. Define a different scenario.
      String expectedResult2 = "9 7 5 3 1 3 5 7 9";
      String result2 = numPattern(9, 2);
      
      // 4. Check that the output of this second scenario matches the 
      //    expected one
      if(!expectedResult2.equals(result2))
      {
        return false;
      }
      
      // 5. Make sure to return false if a StackOverflowError or any exception
      //    will be thrown by the numPattern method calls.
    }
    catch(Exception e)
    {
      return false;
    }
    return true;
  }
  
  public static void main(String[] args)
  {
    System.out.println("testSimpleNumPattern: " + testSimpleNumPattern());
    System.out.println("testNumPattern: " + testNumPattern());

  }

}