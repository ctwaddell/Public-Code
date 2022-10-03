//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    ComparisonMethods - two basic algorithms to be tested by another program
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

public class ComparisonMethods
{
  /**
   * Complexity: O(N)
   * bruteForce adds all the values from 0 to a given number by looping through all numbers between
   * the two
   * @param n, long representing the upper limit which user wants the sum to count to
   * @return long representing the total sum between 0 and input upper bound
   */
  public static long bruteForce(long n)
  {
    long r = 0;
    if(n < 0) return r;
    for(int i = 0; i <= n; i++)
    {
      r = r + i;
    }
    return r;
  }
  /**
   * Complexity: O(1)
   * constantTime adds all the values between 0 and the input number by using triangles to calculate
   * the sum of all numbers in the range
   * @param n, long representing the upper limit which user wants the sum to count to
   * @return long representing the total sum between 0 and input upper bound
   */
  public static long constantTime(long n)
  {
     long r = 0;
     long base = n + 1;
     long height = n;
     r = (base * height) / 2;
     return r;
  }
  
  public static int add(String[] data, int size, String newElement, int index) {
    for(int i = size; i > index; i--) {
      data[i] = data[i-1];
    }
    data[index] = newElement;
    size++;
    return size;
  }
}
