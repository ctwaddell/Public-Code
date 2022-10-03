//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BattleSystemTester - This class verifies that the other classes within this
//                                project are working as expected
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

import java.util.NoSuchElementException;    

public class BattleSystemTester 
{
  /**
   * testEnqueueMoveQueue() verifies that the methods in MoveQueue enqueue() and in a roundabout
   * way percolateUp() and size() function as expected. The various test scenarios this method runs
   * through are enqueueing and testing the size of the queue updates accordingly, making sure
   * an array cannot be overfilled, and making sure you can't enqueue a null element
   * @return boolean true if all tests pass and false if any test fails.
   */
  public static boolean testEnqueueMoveQueue()
  {
    int[] jakeStats = {5,5,5,5,3};
    BattleCharacter jakeyPaul = new BattleCharacter("Jake Paul", jakeStats);
    int[] lagoonStats = {1,1,1,1,1};
    BattleCharacter lagoon = new BattleCharacter("Lagoon", lagoonStats);
    int[] brohStats = {2,2,2,2,2};
    BattleCharacter broh = new BattleCharacter("Broh", brohStats);
    int[] tomStats = {3,3,3,3,5};
    BattleCharacter tom = new BattleCharacter("Tom", tomStats);
    int[] inkBoyStats = {4,4,4,4,4};
    BattleCharacter inkBoy = new BattleCharacter("InkBoy", inkBoyStats);
    int[] natPagelStats = {9,9,9,9,9};
    BattleCharacter natPagel = new BattleCharacter("Nat Pagel", natPagelStats);
    
    
    MoveQueue testQueue = new MoveQueue(5);
    //by proxy, all tests here also confirm the use of the helper percolateUp() method
    //validate the size of the array is updating correctly
    testQueue.enqueue(jakeyPaul);
    if(testQueue.size() != 1)
    {
      return false;
    }
    if(!testQueue.toString().equals("[ Jake Paul(1, 3) | ]"))
    {
      return false;
    }
    testQueue.enqueue(lagoon);
    if(!testQueue.toString().equals("[ Jake Paul(1, 3) | Lagoon(2, 1) | ]"))
    {
      return false;
    }
    testQueue.enqueue(broh);
    if(!testQueue.toString().equals("[ Jake Paul(1, 3) | Lagoon(2, 1) | Broh(3, 2) | ]"))
    {
      return false;
    }
    testQueue.enqueue(tom); //enqueue a new top speed and see if the array adjusts
    if(!testQueue.toString().equals("[ Tom(4, 5) | Jake Paul(1, 3) | Broh(3, 2) |"
        + " Lagoon(2, 1) | ]"))
    {
      return false;
    }
    testQueue.enqueue(inkBoy);
    if(!testQueue.toString().equals("[ Tom(4, 5) | InkBoy(5, 4) | Broh(3, 2) | Lagoon(2, 1) "
        + "| Jake Paul(1, 3) | ]"))
    {
      return false;
    }
    if(testQueue.size() != 5 )
    {
      return false;
    }
    
    //validate you can't overfill an array
    try
    {
      testQueue.enqueue(natPagel);
    }
    catch(IllegalArgumentException e)
    {
      // to be expected, array is full
    }
   
    //validate you can't enqueue a null element
    MoveQueue testQueueTwo = new MoveQueue();
    try
    {
      testQueueTwo.enqueue(null);
    }
    catch(NullPointerException e)
    {
      // to be expected, can't enqueue null
    }
    return true;
  }
  
  /**
   * testPercolateUp() verifies the functionality of percolateUp() as expected. Essentially,
   * this method tests to see an element with high speed rises to the top of the heap after being
   * enqueued.
   * @return true if all the tests pass and false if any test fails
   */
  public static boolean testPercolateUp()
  {
    MoveQueue testQueue = new MoveQueue();
    int[] jakeStats = {5,5,5,5,5};
    BattleCharacter jakeyPaul = new BattleCharacter("Jake Paul", jakeStats);
    int[] lagoonStats = {1,1,1,1,1};
    BattleCharacter lagoon = new BattleCharacter("Lagoon", lagoonStats);
    int[] brohStats = {2,2,2,2,2};
    BattleCharacter broh = new BattleCharacter("Broh", brohStats);
    int[] tomStats = {3,3,3,3,3};
    BattleCharacter tom = new BattleCharacter("Tom", tomStats);
    int[] inkBoyStats = {4,4,4,4,4};
    BattleCharacter inkBoy = new BattleCharacter("InkBoy", inkBoyStats);
    int[] natPagelStats = {9,9,9,9,9};
    BattleCharacter natPagel = new BattleCharacter("Nat Pagel", natPagelStats);
    
    //establish 3 note binary tree...
    testQueue.enqueue(lagoon);
    testQueue.enqueue(broh);
    testQueue.enqueue(tom);
    
    //because ink boy has higher speed than all above it, it should percolateUp to the top
    testQueue.enqueue(inkBoy);
    if(!testQueue.toString().equals("[ InkBoy(17, 4) | Tom(16, 3) | Broh(15, 2) "
        + "| Lagoon(14, 1) | ]"))
    {
      return false;
    }
    
    //add one further just to prove the concept
    testQueue.enqueue(natPagel);
    if(!testQueue.toString().equals("[ Nat Pagel(18, 9) | InkBoy(17, 4) | Broh(15, 2) "
        + "| Lagoon(14, 1) | Tom(16, 3) | ]"))
    {
      return false;
    }
    return true;
  }
  
  /**
   * testPercolateDown() verifies the functionality of percolateDown() as expected. This method
   * dequeues the top element and makes sure the substitute top node sinks to it's correct place
   * in the heap.
   * @return true if all tests pass and false if any test fails.
   */
  public static boolean testPercolateDown()
  {
    MoveQueue testQueue = new MoveQueue();
    int[] jakeStats = {5,5,5,5,5};
    BattleCharacter jakeyPaul = new BattleCharacter("Jake Paul", jakeStats);
    int[] lagoonStats = {1,1,1,1,1};
    BattleCharacter lagoon = new BattleCharacter("Lagoon", lagoonStats);
    int[] brohStats = {2,2,2,2,2};
    BattleCharacter broh = new BattleCharacter("Broh", brohStats);
    int[] tomStats = {3,3,3,3,3};
    BattleCharacter tom = new BattleCharacter("Tom", tomStats);
    int[] inkBoyStats = {4,4,4,4,4};
    BattleCharacter inkBoy = new BattleCharacter("InkBoy", inkBoyStats);
    int[] natPagelStats = {9,9,9,9,9};
    BattleCharacter natPagel = new BattleCharacter("Nat Pagel", natPagelStats);
    
    testQueue.enqueue(jakeyPaul);
    testQueue.enqueue(lagoon);
    testQueue.enqueue(broh);
    testQueue.enqueue(tom);
    testQueue.enqueue(natPagel);
    
    //test before
    if(!testQueue.toString().equals("[ Nat Pagel(24, 9) | Jake Paul(19, 5) | Broh(21, 2)"
        + " | Lagoon(20, 1) | Tom(22, 3) | ]"))
    {
      return false;
    }
    testQueue.dequeue();
    //test after. Tom node should be moved to the top then percolate down below jakey paul
    if(!testQueue.toString().equals("[ Jake Paul(19, 5) | Tom(22, 3) | Broh(21, 2)"
        + " | Lagoon(20, 1) | ]"))
    {
      return false;
    }
    
    return true;
  }
  
  /**
   * testDequeueMoveQueue() verifies the functionality of the dequeue() method as well as the
   * percolateDown() method in a roundabout way. Another test in this method is that you shouldn't
   * be able to dequeue on an empty MoveQueue.
   * @return true if all tests pass and false if any test fails.
   */
  public static boolean testDequeueMoveQueue()
  {
    MoveQueue testQueue = new MoveQueue();
    int[] jakeStats = {5,5,5,5,5};
    BattleCharacter jakeyPaul = new BattleCharacter("Jake Paul", jakeStats);
    int[] lagoonStats = {1,1,1,1,1};
    BattleCharacter lagoon = new BattleCharacter("Lagoon", lagoonStats);
    int[] brohStats = {2,2,2,2,2};
    BattleCharacter broh = new BattleCharacter("Broh", brohStats);
    int[] tomStats = {3,3,3,3,3};
    BattleCharacter tom = new BattleCharacter("Tom", tomStats);
    int[] inkBoyStats = {4,4,4,4,4};
    BattleCharacter inkBoy = new BattleCharacter("InkBoy", inkBoyStats);
    int[] natPagelStats = {9,9,9,9,9};
    BattleCharacter natPagel = new BattleCharacter("Nat Pagel", natPagelStats);
    
    testQueue.enqueue(jakeyPaul);
    testQueue.enqueue(lagoon);
    testQueue.enqueue(broh);
    testQueue.enqueue(tom);
    testQueue.enqueue(natPagel);
    
    //test before dequeue
    if(!testQueue.toString().equals("[ Nat Pagel(12, 9) | Jake Paul(7, 5) | Broh(9, 2)"
        + " | Lagoon(8, 1) | Tom(10, 3) | ]"))
    {
      return false;
    }
    if(testQueue.size() != 5)
    {
      return false;
    }
    
    testQueue.dequeue();
    
    //test after dequeue
    if(testQueue.toString().equals("[ Jake Paul(7, 5) | Tom(10, 3) | Broh(9, 2)"
        + " | Lagoon(8, 1) | ]"))
    {
      
    }
    if(testQueue.size() != 4)
    {
      return false;
    }

      
    //test dequeue on an empty MoveQueue
    MoveQueue testQueueTwo = new MoveQueue();
    try
    {
      testQueueTwo.dequeue();
    }
    catch(NoSuchElementException e)
    {
      // to be expected
    }
      
    return true;
  }
  
  /**
   * testIsEmptySizeClear() tests the smaller and less intensive methods of the MoveQueue class.
   * size is verified by enqueuing objects and making sure the correct size corresponds to the 
   * number of elements added to the moveQueue. IsEmpty() is verified by checking if a null array
   * is said to return true. The opposite is also tested in that it should return false after
   * adding elements to the array. Clear is tested by trying to compare in the array, throwing
   * a nullPointer if the array is empty as speculated. Finally, isEmpty() is called one last time.
   * @return true if all tests pass and false if any test fails.
   */
  public static boolean testIsEmptySizeClear()
  {
    MoveQueue testQueue = new MoveQueue();
    
    //test isEmpty() when it is empty
    if(testQueue.isEmpty() == false)
    {
      return false;
    }
    
    int[] jakeStats = {5,5,5,5,5};
    BattleCharacter jakeyPaul = new BattleCharacter("Jake Paul", jakeStats);
    int[] lagoonStats = {1,1,1,1,1};
    BattleCharacter lagoon = new BattleCharacter("Lagoon", lagoonStats);
    int[] brohStats = {2,2,2,2,2};
    BattleCharacter broh = new BattleCharacter("Broh", brohStats);
    int[] tomStats = {3,3,3,3,3};
    BattleCharacter tom = new BattleCharacter("Tom", tomStats);
    int[] inkBoyStats = {4,4,4,4,4};
    BattleCharacter inkBoy = new BattleCharacter("InkBoy", inkBoyStats);
    int[] natPagelStats = {9,9,9,9,9};
    BattleCharacter natPagel = new BattleCharacter("Nat Pagel", natPagelStats);
    
    testQueue.enqueue(jakeyPaul);
    testQueue.enqueue(lagoon);
    testQueue.enqueue(broh);
    testQueue.enqueue(tom);
    testQueue.enqueue(natPagel);
    
    
    //test isEmpty() when it isn't empty
    if(testQueue.isEmpty() == true)
    {
      return false;
    }
    //test size()
    if(testQueue.size() != 5)
    {
      return false;
    }
    
    //test clear()
    testQueue.clear();
    try
    {
      if(testQueue.toString().equals("this is unreachable"))
      {
        return false;
      }
    }
    catch(NullPointerException e)
    {
      // to be expected
    }
    if(testQueue.isEmpty() == false)
    {
      return false;
    }
    return true;
  }
  
  /**
   * testPeekBest() verifies that the MoveQueue's peekBest() method functions as expected. 
   * The test methods are calling it on a null array resulting in a noSuchElementException and then
   * adding various BattleCharacters and making sure the one with the highest speed stat is returned
   * @return true if all tests pass and false if any test fails
   */
  public static boolean testPeekBest()
  {
    MoveQueue testQueue = new MoveQueue();
    
    //test on a null array
    try
    {
      testQueue.peekBest();
    }
    catch(NoSuchElementException e)
    {
      // to be expected
    }
    int[] jakeStats = {5,5,5,5,5};
    BattleCharacter jakeyPaul = new BattleCharacter("Jake Paul", jakeStats);
    testQueue.enqueue(jakeyPaul);
    //test on single element array
    if(!testQueue.peekBest().equals(jakeyPaul))
    {
      return false;
    }
    
    //add new element to array, and make sure it peeks the new dominant term
    int[] natPagelStats = {9,9,9,9,9};
    BattleCharacter natPagel = new BattleCharacter("Nat Pagel", natPagelStats);
    testQueue.enqueue(natPagel);
    if(!testQueue.peekBest().equals(natPagel))
    {
      return false;
    }
    
    return true;
  }

  
  public static void main(String[] args)
  {
    System.out.println(testEnqueueMoveQueue());
    System.out.println(testDequeueMoveQueue());
    System.out.println(testPercolateUp());
    System.out.println(testPercolateDown());
    System.out.println(testIsEmptySizeClear());
    System.out.println(testPeekBest());
  }
}
