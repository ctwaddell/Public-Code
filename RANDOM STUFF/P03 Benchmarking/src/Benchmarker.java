//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Benchmarker - A program that compares the efficiency of two methods
// Course:   CS 300 Fall 2020
//
// Author:   Casey Waddell
// Email:    ctwaddell@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.FileWriter;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.IOException;

public class Benchmarker
{
  /**
   * compare
   * @param n, long representing which number the two methods should count to and find the sum of
   * @return String representing the input number followed by the two times it took to calculate,
   * bruteForce first and them formula second
   * @throws NoSuchElementException if the two tests give different number results, it is inferred
   * that the two methods aren't equal in process, so can't be accurately compared
   */
  public static String compare(long n) throws NoSuchElementException
  {
    long inputN = n;
    long bruteForceResult = 0;
    long formulaResult = 0;
    long timeOne = System.currentTimeMillis();
    bruteForceResult = ComparisonMethods.bruteForce(n);
    long timeTwo = System.currentTimeMillis();
    long bruteForceTime = timeTwo - timeOne;
    timeOne = System.currentTimeMillis();
    formulaResult = ComparisonMethods.constantTime(n);
    timeTwo = System.currentTimeMillis();
    if(bruteForceResult != formulaResult) throw new NoSuchElementException(
        "The two comparison methods yielded different results.");     
    long formulaTime = timeTwo - timeOne;
    String r = inputN + "\t" + bruteForceTime + "\t" + formulaTime + "\n";
    return r;
  }
  /**
   * createResultsFile creates a file to display the results of an input array of values to compare
   * each methods efficiency
   * @param f, file to write on
   * @param queryNs, long array of values user wants to test
   */
  public static void createResultsFile(File f, long[] queryNs)
  {
    try 
    {
      FileWriter comparisons = new FileWriter(f);
      for(int i = 0; i < queryNs.length; i++)
      {
        try
        {
          comparisons.write(compare(queryNs[i]));
        }
        catch(NoSuchElementException e)
        {
          System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
          System.out.println("Exception encountered while writing for value N = " + i);
        }
      }
      try
      {
        comparisons.close();
      }
      catch(IOException e)
      {
        System.out.println("Exception encountered while closing file.");
        return;
      }
    }
    catch(IOException e) 
    {
      System.out.println("Exception encountered, unable to complete method");
      return;
    }
  }

  public static void main(String[] args)
  {
    String[] input = {"a", "b", "c", "d", "e", "f"};
    long[] swag = {1000, 2000000000, 30000000};
    File f = new File("computerMustFindThis");
    createResultsFile(f, swag);
    long timeOne = System.currentTimeMillis();
    timeOne = timeOne * 1000;
    ComparisonMethods.add(input, 5, "g", 0);
    long timeTwo = System.currentTimeMillis();
    timeTwo = timeTwo * 1000;
    long result = timeOne - timeTwo;
    System.out.println(result);
  }
}
