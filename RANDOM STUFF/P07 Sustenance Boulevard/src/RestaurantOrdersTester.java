//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    RestaurantOrdersTester - Tests all methods in LinkedOrder and RestaurantOrders classes
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

/**
* This class implements unit test methods to check the 
* correctness of LinkedOrders and RestaurantOrders
* classes defined in the CS300 Fall 2020 - P07 Restaurant Orders programming assignment.
*
*/
public class RestaurantOrdersTester 
{
  
  /**
  * This method should test and make use of at least the LinkedOrders constructor, an accessor
  * (getter) method, and a mutator (setter) method defined in the LinkedOrders class.
  * 
  * @return true when this test verifies a correct functionality, and false otherwise
  */
  public static boolean testLinkedOrders() 
  {
    // CONSTRUCTOR TEST BLOCK
    Order testOrder = new Order("Chicken", 0); // tests first constructor
    LinkedOrder test1 = new LinkedOrder(testOrder);
    Order testOrder2 = new Order("Not Chicken", -1);
    try
    {
      LinkedOrder test2 = new LinkedOrder(testOrder2);
    }
    catch(IllegalArgumentException e)
    {
      // to be expected when order has a negative time stamp
    }
    Order testOrder3 = new Order("Maybe Chicken", 2);
    Order testOrder4 = new Order("Likely Chicken", 3);
    LinkedOrder test2 = new LinkedOrder(testOrder3);
    LinkedOrder test3 = new LinkedOrder(testOrder4, test1, test2); // tests second constructor
    
    // ACCESSOR TEST BLOCK
    if(!test1.getOrder().equals(testOrder)) // tests getOrder()
    {
      return false;
    }
    if(!test3.getNext().equals(test2)) // tests getNext()
    {
      return false;
    }
    if(!test3.getPrevious().equals(test1)) // tests getPrevious()
    {
      return false;
    }
    
    // MUTATOR TEST BLOCK
    test3.setNext(test1);
    if(!test3.getNext().equals(test1)) // tests setNext()
    {
      return false;
    }
    test3.setPrevious(test2);
    if(!test3.getPrevious().equals(test2)) // tests setPrevious()
    {
      return false;
    }    
    
    return true;
  }
  
  /**
  * This method checks for the correctness of both RestaurantOrders constructors and the instance
  * method isEmpty() when called on an empty restaurant orders object.
  * 
  * @return true when this test verifies a correct functionality, and false otherwise
  */
  public static boolean testRestaurantOrdersConstructorIsEmpty()
  {
    RestaurantOrders test1 = new RestaurantOrders(); // tests null constructor
    RestaurantOrders test2 = new RestaurantOrders(10); // tests constructor with capacity input
    if(test1.isEmpty() != true) // tests isEmpty()
    {
      return false;
    }
    Order testOrder = new Order("food", 1); //adds to RestaurantOrders list
    LinkedOrder testLinkedOrder = new LinkedOrder(testOrder);
    test1.placeOrder(testOrder);
    if(test1.isEmpty() != false) // tests isEmpty() on a not empty array
    {
      return false;
    }
    return true;
  }
  
  /**
  * This method checks for the correctness of the RestaurantOrders(int) constructor when passed a
  * negative int value for the capacity.
  * 
  * @return true when this test verifies a correct functionality, and false otherwise
  */
  public static boolean testRestaurantOrdersConstructorBadInput()
  {
    try 
    {
      RestaurantOrders test1 = new RestaurantOrders(-1);
    }
    catch(IllegalArgumentException e)
    {
      // as to be expected from a bad input
    }
    return true;
  }
  
  
  /**
  * This method checks for the correctness of the RestaurantOrders.placeOrder()()
  * method when it is passed bad
  * inputs. This method must consider at least the test scenarios provided in the detailed
  * description of these javadocs. (1) Try adding a null to the list; (2) Try adding an order which
  * carries a negative timestamp. (3) Try adding an order with an existing timestamp to the list.
  * 
  * @return true when this test verifies a correct functionality, and false otherwise
  */
  public static boolean testRestaurantOrdersAddBadInput() 
  {
    RestaurantOrders testList = new RestaurantOrders();
    try
    {
      testList.placeOrder(null); // tests case 1
    }
    catch(IllegalArgumentException e)
    {
      // as to be expected from a null input
    }
    Order badInput = new Order("food", -1);
    try 
    {
      testList.placeOrder(badInput); // tests case 2
    }
    catch(IllegalArgumentException e)
    {
      // as to be expected from a negative timestamp input
    }
    Order test1 = new Order("food", 1);
    Order test2 = new Order("chimcken", 1);
    try
    {
      testList.placeOrder(test1);
      testList.placeOrder(test2); // tests case 3
    }
    catch(IllegalArgumentException e)
    {
      // as to be expected from a duplicate timestamp input
    }
    return true;
  }
  
  /**
  * This method checks for the correctness of the RestaurantOrders.placeOrder()() considering
  * at least the test
  * scenarios provided in the detailed description of these javadocs. (1) Try adding an order to an
  * empty list; (2) Try adding an order which is expected to be added at the head of a non-empty
  * restaurant list; (3) Try adding an order which is expected to be added at the end of a non-empty
  * restaurant list; (4) Try adding an order which is expected to be added at the middle of a 
  * non-empty
  * restaurant list. For each of those scenarios, make sure that the size of the list is
  * appropriately updated after a call without errors of the add() method, and that the contents of
  * the list is as expected whatever if list is read in the forward or backward directions. You can
  * also check the correctness of RestaurantOrders.get(int), RestaurantOrders.indexOf(Order), and
  * RestaurantOrders.size() in this test method.
  * 
  * @return true when this test verifies a correct functionality, and false otherwise
  */
  public static boolean testRestaurantOrdersAdd() 
  {
    RestaurantOrders testList = new RestaurantOrders();
    //each test case tests the size(), get(), readForward(), and readBackward() methods
    
    Order testOrder1 = new Order("Chikmcen Nungetts", 2);
    testList.placeOrder(testOrder1); // tests case 1
    if(testList.size() != 1)
    {
      return false;
    }
    if(!testList.get(0).equals(testOrder1))
    {
      return false;
    }
    if(!testList.readForward().equals("This list contains 1 order(s): [ Chikmcen Nungetts ]"))
    {
      return false;
    }
    if(!testList.readBackward().equals("This list contains 1 order(s): [ Chikmcen Nungetts ]"))
    {
      return false;
    }
    
    Order testOrder2 = new Order("cofe beens", 1);
    testList.placeOrder(testOrder2); // tests case 2
    if(testList.size() != 2)
    {
      return false;
    }
    if(!testList.get(0).equals(testOrder2))
    {
      return false;
    }
    if(!testList.readForward().equals("This list contains 2 order(s): [ cofe beens "
        + "Chikmcen Nungetts ]"))
    {
      return false;
    }
    if(!testList.readBackward().equals("This list contains 2 order(s): "
        + "[ Chikmcen Nungetts cofe beens ]"))
    {
      return false;
    }

    Order testOrder3 = new Order("samon chips", 4);
    testList.placeOrder(testOrder3); // tests case 3
    if(testList.size() != 3)
    {
      return false;
    }
    if(!testList.get(2).equals(testOrder3))
    {
      return false;
    }
    if(!testList.readForward().equals("This list contains 3 order(s): [ cofe beens Chikmcen "
        + "Nungetts samon chips ]"))
    {
      return false;
    }
    if(!testList.readBackward().equals("This list contains 3 order(s): [ samon chips Chikmcen"
        + " Nungetts cofe beens ]"))
    {
      return false;
    }

    Order testOrder4 = new Order("yumn", 3);
    testList.placeOrder(testOrder4); // tests case 4
    if(testList.size() != 4)
    {
      return false;
    }
    if(!testList.get(2).equals(testOrder4))
    {
      return false;
    }
    if(!testList.readForward().equals("This list contains 4 order(s): [ cofe beens Chikmcen"
        + " Nungetts yumn samon chips ]"))
    {
      return false;
    }
    if(!testList.readBackward().equals("This list contains 4 order(s): [ samon chips yumn Chik"
        + "mcen Nungetts cofe beens ]"))
    {
      return false;
    }
    
    // the final test here makes sure that the indexOf() function works
    if(testList.indexOf(testOrder2) != 0)
    {
      return false;
    }
    if(testList.indexOf(testOrder4) != 2)
    {
      return false;
    }
    
    return true;
  }
  
  /**
  * This method checks for the correctness of the RestaurantOrders.removeOrder() 
  * considering at least the
  * test scenarios provided in the detailed description of these javadocs. (1) Try removing an order
  * from an empty list or pass a negative index to RestaurantOrders.removeOrder() method; (2) Try 
  * removing an
  * order (at position index 0) from a list which contains only one order; (3) Try to remove 
  * an order
  * (position index 0) from a list which contains at least 2 orders; (4) Try to remove an order from
  * the middle of a non-empty list containing at least 3 orders; (5) Try to remove the order at the
  * end of a list containing at least two orders. For each of those scenarios, make sure that the 
  * size of the list is appropriately updated after a call without errors of the add() method, 
  * and that the contents of the list is as expected whatever if list is read in the forward or 
  * backward directions.
  * 
  * @return true when this test verifies a correct functionality, and false otherwise
  */
  public static boolean testRestaurantOrdersRemove()
  {
    RestaurantOrders testList = new RestaurantOrders();
    try
    {
      testList.removeOrder(0); // tests for am empty list (case 1.1)
    }
    catch(IndexOutOfBoundsException e)
    {
      // to be expected for an empty list
    }
    try
    {
      testList.removeOrder(-1); // tests for a negative index (case 1.2)
    }
    catch(IndexOutOfBoundsException e)
    {
      // to be expected for a negative index
    }
    Order testOrder1 = new Order("food", 1);
    testList.placeOrder(testOrder1);
    testList.removeOrder(0); // tests removing at index 0 for a one element list (case 2)
    if(!testList.readForward().equals("This list contains 0 order(s)"))
    {
      return false;
    }
    if(testList.size() != 0)
    {
      return false;
    }
    
    Order testOrder2 = new Order("foo2", 2);
    testList.placeOrder(testOrder2);
    testList.placeOrder(testOrder1);
    testList.removeOrder(0); // tests removing at index 0 for a two element list (case 3)
    if(!testList.readForward().equals("This list contains 1 order(s): [ foo2 ]"))
    {
      return false;
    }
    if(!testList.readBackward().equals("This list contains 1 order(s): [ foo2 ]"))
    {
      return false;
    }
    if(testList.size() != 1)
    {
      return false;
    }
    
    RestaurantOrders testList2 = new RestaurantOrders(); // tests removing a middle index (case 4)
    Order test1 = new Order("food 1", 1);
    Order test2 = new Order("food 2", 2);
    Order test3 = new Order("food 3", 3);
    testList2.placeOrder(test1);
    testList2.placeOrder(test2);
    testList2.placeOrder(test3);
    testList2.removeOrder(1);
    if(!testList2.readForward().equals("This list contains 2 order(s): [ food 1 food 3 ]"))
    {
      return false;
    }
    if(!testList2.readBackward().equals("This list contains 2 order(s): [ food 3 food 1 ]"))
    {
      return false;
    }
    if(testList2.size() != 2)
    {
      return false;
    }
    
    testList2.removeOrder(1); // tests removing the tail from a 2 element list (case 5)
    if(!testList2.readForward().equals("This list contains 1 order(s): [ food 1 ]"))
    {
      return false;
    }
    if(!testList2.readBackward().equals("This list contains 1 order(s): [ food 1 ]"))
    {
      return false;
    }
    if(testList2.size() != 1)
    {
      return false;
    }
    
    return true;
  }
  
  
  /**
  * This method calls all the test methods defined and implemented in your RestaurantOrdersTester
  * class.
  * 
  * @return true if all the test methods defined in this class pass, and false otherwise.
  */
  public static boolean runAllTests() 
  {
    if(testLinkedOrders() == true && testRestaurantOrdersConstructorIsEmpty() == true
        && testRestaurantOrdersConstructorBadInput() == true && testRestaurantOrdersAddBadInput()
        == true && testRestaurantOrdersAdd() == true && testRestaurantOrdersRemove() == true)
    {
      return true;
    }
    return false;
  }
  
  /**
  * Driver method defined in this RestaurantOrdersTester class
  * 
  * @param args input arguments if any.
  */
  public static void main(String[] args) 
  {
    System.out.println(runAllTests()); // the power of one true statement...
  }
}