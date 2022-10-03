// --== CS400 File Header Information ==--
// Name: Casey Waddell
// Email: ctwaddell@wisc.edu
// Team: Blue
// Group: KB
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class TestHashTable 
{
  /**
   * test1 checks the functionality of the constructors, including that their size is set to 0,
   * their capacity is set to the appropriate size, and that empty LinkedList objects are in them
   * @return boolean, true if the test works and false if something goes wrong
   */
  public static boolean test1() 
  { 
    int capacityCheck = 0;
    LinkedList testList = new LinkedList();
    HashTableMap test1 = new HashTableMap(); //checks if it can even be made
    HashTableMap test2 = new HashTableMap(5);
    if(test1.size() != 0 && test2.size() != 0) //checks if size is 0 as it should be
    {
      return false;
    }
    for(int i = 0; i < test1.getArrayHelper().length; i++) //makes sure the capacity was made right
    {
      capacityCheck++;
    }
    if(capacityCheck != 10) //as above
    {
      return false;
    }
    capacityCheck = 0;
    for(int i = 0; i < test2.getArrayHelper().length; i++) //as above
    {
      capacityCheck++;
    }
    if(capacityCheck != 5) //as above
    {
      return false;
    }
    return true;    
  }
  
  /**
   * test2 checks the put method (size and hashTableData class will also be tested inadvertently)
   * @return boolean, true if the test works and false if something goes wrong
   */
  public static boolean test2() 
  { 
    LinkedList testList = new LinkedList();
    HashTableMap test1 = new HashTableMap();
    test1.put(1, "testOne");
    if(test1.size() != 1)
    {
      return false;
    }
    if(test1.put(1, "testOne") != false) //duplicate element test
    {
      return false;
    }
    if(test1.put(1, "testTwo") != false) //duplicate key, different value
    {
      return false;
    }
    test1.put(2, "testThree"); //tests adding more elements
    test1.put(3, "testFour");
    if(test1.size() != 3)
    {
      return false;
    }
    if(test1.put(null, "testFive") != false) //tests null key
    {
      return false;
    }
    return true;    
  }
  
  /**
   * test3 makes sure the functions get and remove work as we wish
   * @return boolean, true if the test works and false if something goes wrong
   */
  public static boolean test3() 
  { 
    HashTableMap test1 = new HashTableMap();
    test1.put(1, "testOne");
    test1.put(2, "testThree");
    test1.put(3, "testFour");
    if(!test1.get(2).equals("testThree")) //checks if get returns the right value
    {
      return false;
    }
    if(!test1.get(3).equals("testFour")) //as above
    {
      return false;
    }
    try
    {
      if(!test1.get(4).equals("this will never be reached"))
      {
        //this is never going to be reached, it's to check for NSEE
      }
    }
    catch(NoSuchElementException e)
    {
      //as to be expected
    }
    if(!test1.remove(2).equals("testThree")) //checks for correct value return
    {
      return false;
    }
    if(test1.size() != 2) //checks to make sure the size was updated correctly
    {
      return false;
    }
    if(test1.remove(5) != null) //checks for if key does not exist in hash table
    {
      return false;
    }
    return true;    
  }
  
  /**
   * test4 makes sure the growTableHelper method(this will
   * also inadvertently test the getArrayHelper method)
   * @return boolean, true if the test works and false if something goes wrong
   */
  public static boolean test4()
  { 
    HashTableMap test1 = new HashTableMap(3);
    int capacityCheck = 0;
    test1.put(1, "testOne");
    test1.put(2, "testTwo");
    if(test1.size() != 2) //checking size one more time just in case cause it's critical to this
    {
      return false;
    }
    for(int i = 0; i < test1.getArrayHelper().length; i++)
    {
      capacityCheck++;
    }
    if(capacityCheck != 3) //checks to see capacity before the 'tipping point'
    {
      return false;
    }
    test1.put(3, "testThree");
    test1.put(4, "testFour");
    capacityCheck = 0;
    for(int i = 0; i < test1.getArrayHelper().length; i++)
    {
      capacityCheck++;
    }
    if(capacityCheck != 6) //checks to see capacity after the 'tipping point'
    {
      return false;
    }
    if(!test1.remove(4).equals("testFour")) //makes sure the elements are carried over when resize
    {
      return false;
    }
    return true;    
  }
  
  /**
   * test 5 will wrap up the final methods: clear and contains key
   * @return boolean, true if the test works and false if something goes wrong
   */
  public static boolean test5()
  { 
    HashTableMap test1 = new HashTableMap(3);
    test1.put(1, "testOne");
    test1.put(2, "testTwo");
    test1.put(3, "testThree");
    if(test1.size() != 3) //test size before
    {
      return false;
    }
    if(test1.containsKey(1) == false) //testing containsKey because the hashTable does have key 1 rn
    {
      return false;
    }
    test1.clear();
    if(test1.size() != 0) //test size after
    {
      return false;
    }
    if(test1.containsKey(1) == true) //testing containsKey after because the hashTable should be MT
    {
      return false;
    }
    return true;
  }
  
  public static void main(String[] args)
  {
    System.out.println(test1());
    System.out.println(test2());
    System.out.println(test3());
    System.out.println(test4());
    System.out.println(test5());
  }
}
