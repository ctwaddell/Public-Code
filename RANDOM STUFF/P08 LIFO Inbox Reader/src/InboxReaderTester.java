//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    InboxReaderTester - runs through all the methods in related files and tests if
//                               they work or not as expected
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

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of the implementation of the
 * MessageStack, Inbox, and MessageStackIterator classes defined in the CS300 Fall 2020 - P08 LIFO
 * Inbox Reader programming assignment.
 *
 */
public class InboxReaderTester 
{

  /**
   * Calls your tester methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args)
  {
    // NOTE THAT THE TESTING OF getStatistics() IS SELF EVIDENT THROUGH ALL OTHER TESTS
    System.out.println(runInboxReaderTestSuite());
  }

  /**
   * Checks for the correctness of the constructor of the MessageStack, MessageStack.push(),
   * MessageStack.peek(), MessageStack.isEmpty(), and MessageStack.size() methods. This method must
   * consider at least the test scenarios provided in the detailed description of these javadocs.
   * (1) Create a new MessageStack object. The new created stack must be empty and its size must be
   * zero. (2) You can consider calling peek method on the empty stack. An EmptyStackException is
   * expected to be thrown by the peek method call. (3) Then, push a Message onto the stack and then
   * use peek to verify that the correct item is at the top of the stack. Make sure also to check
   * that isEmpty() must return false and the size of the stack is one. The peek() method call
   * should not make any change to the contents of the stack. (4) You can further consider pushing
   * other elements onto the stack. Then, each call of peek() method should return the correct
   * Message object that should be at the top of the stack. After pushing a new message to the stack
   * double check that the size of the stack was incremented by one.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackConstructorIsEmptyPushPeek()
  {
    // scenario 1
    Message testOne = new Message("test one subject", "test one text");
    Message testTwo = new Message("test two subject", "test two text");
    MessageStack testList = new MessageStack();
    if(testList.isEmpty() == false)
    {
      return false;
    }
    if(testList.size() != 0)
    {
      return false;
    }
    
    // scenario 2
    try 
    {
      testList.peek();
    }
    catch(EmptyStackException e)
    {
      // to be expected
    }
    
    // scenario 3
    testList.push(testOne);
    if(testList.peek() != testOne)
    {
      return false;
    }
    if(testList.isEmpty() == true)
    {
      return false;
    }
    if(testList.size() != 1)
    {
      return false;
    }
    
    // scenario 4
    testList.push(testTwo);
    if(testList.peek() != testTwo)
    {
      return false;
    }
    if(testList.isEmpty() == true)
    {
      return false;
    }
    if(testList.size() != 2)
    {
      return false;
    }
    return true;
  }


  /**
   * Checks for the correctness of MessageStack.pop(). It calls MessageStack.push() and
   * MessageStack.peek() methods. This method must consider at least the test scenarios provided in
   * the detailed description of these javadocs. (1) Try to create a new empty MessageStack. Then,
   * try calling pop method on the empty stack. An EmptyStackException is expected to be thrown as a
   * result. (2) Try to push a message to the stack. Then, try to call the pop method on the stack
   * which contains only one element. Make sure that the pop() operation returned the expected
   * message, and that the stack is empty and its size is zero. (3) Then, try to push at least three
   * messages, then call pop method on the stack which contains 3 elements, the element at the top
   * of the stack must be returned and removed from the stack and its size must be decremented by
   * one. You can further keep popping the elements of the stack one by one until it becomes empty
   * and check each time that the pop operation performs appropriately.This test method must return
   * false if it detects at least one defect.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackPop()
  {
    Message testOne = new Message("test one subject", "test one text");
    Message testTwo = new Message("test two subject", "test two text");
    Message testThree = new Message("test three subject", "test three text");
    
    // scenario 1
    MessageStack testList = new MessageStack();
    try
    {
      testList.pop();
    }
    catch(EmptyStackException e)
    {
      // to be expected
    }
    
    // scenario 2
    testList.push(testOne);
    Message testMessage = testList.pop();
    if(!testMessage.equals(testOne))
    {
      return false;
    }
    if(testList.isEmpty() == false)
    {
      return false;
    }
    if(testList.size() != 0)
    {
      return false;
    }
    
    // scenario 3
    MessageStack testListTwo = new MessageStack();
    testListTwo.push(testOne);
    testListTwo.push(testTwo);
    testListTwo.push(testThree);
    if(!testListTwo.peek().equals(testThree)) //check top before popping
    {
      return false;
    }
    if(testListTwo.size() != 3) // check size before popping
    {
      return false;
    }
    Message comparator = testListTwo.pop();
    if(!comparator.equals(testThree))
    {
      return false;
    }
    if(!testListTwo.peek().equals(testTwo))
    {
      return false;
    }
    if(testListTwo.size() != 2)
    {
      return false;
    }
    comparator = testListTwo.pop();
    if(!comparator.equals(testTwo))
    {
      return false;
    }
    if(!testListTwo.peek().equals(testOne))
    {
      return false;
    }
    if(testListTwo.size() != 1)
    {
      return false;
    }
    comparator = testListTwo.pop();
    if(!comparator.equals(testOne))
    {
      return false;
    }
    try
    {
      if(!testListTwo.peek().equals(testOne))
      {
        return false;
      }
    }
    catch(EmptyStackException e)
    {
      // to be expected
    }
    if(testListTwo.size() != 0)
    {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of the Inbox.ReadMessage() method.
   * Scenario 1 when unreadMessageBox is empty
   * Scenario 2 when unreadMessageBox is not empty
   * Scenario 3 emptying the unreadMessageBox to size 0
   * Scenario 4 when unreadMessageBox is just turned empty after having messages
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReadMessage()
  {
    Inbox testInbox = new Inbox();
    Message testOne = new Message("test one subject", "test one text");
    Message testTwo = new Message("test two subject", "test two text");
    Message testThree = new Message("test three subject", "test three text");
    
    // scenario 1
    if(!testInbox.readMessage().equals("Nothing in Unread"))
    {
      return false;
    }
    
    // scenario 2
    testInbox.receiveMessage(testOne);
    testInbox.receiveMessage(testTwo);
    testInbox.receiveMessage(testThree);
    if(!testInbox.readMessage().equals("test three subject: test three text"))
    {
      return false;
    }
    if(!testInbox.getStatistics().equals("Unread 2\nRead 1"))
    {
      return false;
    }    
    
    // scenario 3
    testInbox.readMessage();
    testInbox.readMessage();
    if(!testInbox.getStatistics().equals("Unread 0\nRead 3"))
    {
      return false;
    }
    
    // scenario 4
    try
    {
      testInbox.readMessage();
    }
    catch(EmptyStackException e)
    {
      // to be expected
    }
    
    return true;
  }


  /**
   * Checks for the correctness of the Inbox.ReceiveMessage() method
   * Scenario 1 - receiving one message
   * Scenario 2 - receiving a null message
   * Scenario 3 - add four messages total (including null message) and check size and stuff
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReceiveMessage() 
  {
    Inbox testInbox = new Inbox();
    Message testOne = new Message("test one subject", "test one text");
    Message testTwo = new Message("test two subject", "test two text");
    Message testThree = new Message("test three subject", "test three text");
    Message fakeMessage = null;
    
    // scenario 1
    testInbox.receiveMessage(testOne);
    if(!testInbox.peekReadMessage().equals("test one subject: test one text"))
    {
      return false;
    }
    
    // scenario 2
    testInbox.receiveMessage(fakeMessage);
    try 
    {
      if(!testInbox.peekReadMessage().equals("aaa"))
      {
        return false;
      }
    }
    catch(NullPointerException e)
    {
      // to be expected
    }
    
    // scenario 3
    testInbox.receiveMessage(testTwo);
    testInbox.receiveMessage(testThree);
    if(!testInbox.getStatistics().equals("Unread 4\nRead 0"))
    {
      return false;
    }
    
    return true;
  }

  /**
   * Checks for the correctness of the Inbox.markAllMessagesAsRead() method
   * Scenario 1 - empty inboxes
   * Scenario 2 - full inbox
   * Scenario 3 - add more messages to an already used inbox
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxMarkAllMessagesAsRead() 
  {
    Inbox testList = new Inbox();
    Message testOne = new Message("test one subject", "test one text");
    Message testTwo = new Message("test two subject", "test two text");
    Message testThree = new Message("test three subject", "test three text");
    Message testFour = new Message("test four subject", "test four text");
    Message testFive = new Message("test five subject", "test five text");
    
    // scenario 1
    if(testList.markAllMessagesAsRead() != 0)
    {
      return false;
    }
    
    // scenario 2
    testList.receiveMessage(testOne);
    testList.receiveMessage(testTwo);
    testList.receiveMessage(testThree);
    if(!testList.getStatistics().equals("Unread 3\nRead 0"))
    {
      return false;
    }
    if(testList.markAllMessagesAsRead() != 3)
    {
      return false;
    }
    if(testList.markAllMessagesAsRead() != 0)
    {
      return false;
    }
    
    // scenario 3
    testList.receiveMessage(testFour);
    testList.receiveMessage(testFive);
    if(!testList.getStatistics().equals("Unread 2\nRead 3"))
    {
      return false;
    }
    if(testList.markAllMessagesAsRead() != 2)
    {
      return false;
    }
    if(!testList.getStatistics().equals("Unread 0\nRead 5"))
    {
      return false;
    }
    
    return true;
  }

  /**
   * Checks for the correctness of the Inbox.peekReadMessage() method
   * Scenario 1 - empty inbox
   * Scenario 2 - inbox contains a message
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testPeekReadMessage()
  {
    Message testOne = new Message("test one subject", "test one text");
    Inbox testList = new Inbox();
    
    // scenario 1
    try
    {
      testList.peekReadMessage();
    }
    catch(EmptyStackException e)
    {
      // to be expected 
    }
    
    //scenario 2
    testList.receiveMessage(testOne);
    if(!testList.peekReadMessage().equals("test one subject: test one text"))
    {
      return false;
    }
    if(!testList.getStatistics().equals("Unread 1\nRead 0"))
    {
      return false;
    }
    
    return true;
  }
  
  /**
   * Checks for the correctness of the Inbox.emptyReadMessageBox() method
   * Scenario 1 - empty an empty mailbox
   * Scenario 2 - read mailbox containing two read messages
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testEmptyReadMessageBox()
  {
    Message testOne = new Message("test one subject", "test one text");
    Message testTwo = new Message("test two subject", "test two text");
    Inbox testList = new Inbox();
    
    // scenario 1
    if(testList.emptyReadMessageBox() != 0)
    {
      return false;
    }
    
    // scenario 2
    testList.receiveMessage(testOne);
    testList.receiveMessage(testTwo);
    if(!testList.getStatistics().equals("Unread 2\nRead 0"))
    {
      return false;
    }
    testList.readMessage();
    testList.readMessage();
    if(!testList.getStatistics().equals("Unread 0\nRead 2"))
    {
      return false;
    }
    if(testList.emptyReadMessageBox() != 2)
    {
      return false;
    }
    if(!testList.getStatistics().equals("Unread 0\nRead 0"))
    {
      return false;
    }
    return true;
  }
  
  /**
   * Checks for the correctness of MessageStackIterator.hasNext() and MessageStackIterator.next()
   * methods. You can rely on the constructor of the LinkedNode class which takes two input
   * parameters (setting both data and next instance fields) to create a chain of linked nodes (at
   * least 3 linked nodes) which carry messages as data fields. Then, create a new
   * MessageStackIterator() and pass it the head of the chain of linked nodes that you created. We
   * recommend that you consider at least the following test scenarios in this tester method. (1)
   * Try to call next() on the iterator. The first call of next() must return the message at the
   * head of your chain of linked nodes. (2) Try to call hasNext() on your iterator, it must return
   * true. (3) The second call of next() must return the message which has been initially at index 1
   * of your chain of linked nodes. (4) The third call of next() on your iterator must return the
   * message initially at index 2 of your chain of linked nodes. (4) If you defined a chain of 3
   * linked nodes in this scenario, hasNext() should return false, and the fourth call of next() on
   * the iterator must throw a NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testMessageStackIterator() 
  {
    Message testOne = new Message("test one subject", "test one text");
    Message testTwo = new Message("test two subject", "test two text");
    Message testThree = new Message("test three subject", "test three text");
    LinkedNode<Message> elementOne = new LinkedNode<Message>(testOne);
    LinkedNode<Message> elementTwo = new LinkedNode<Message>(testTwo, elementOne);
    LinkedNode<Message> elementThree = new LinkedNode<Message>(testThree, elementTwo);
    MessageStackIterator messageIterator = new MessageStackIterator(elementThree);

    // scenario 1
    if(!messageIterator.next().getTEXT().equals("test three text"))
    {
      return false;
    }
    
    // scenario 2
    if(messageIterator.hasNext() != true)
    {
      return false;
    }
    
    // scenario 3
    if(!messageIterator.next().getTEXT().equals("test two text"))
    {
      return false;
    }
    
    // scenario 4
    if(!messageIterator.next().getTEXT().equals("test one text"))
    {
      return false;
    }
    if(messageIterator.hasNext() == true)
    {
      return false;
    }
    try
    {
      messageIterator.next();
    }
    catch(NoSuchElementException e)
    {
      // to be expected
    }
    return true;
  }
  
  /**
   * runInboxReaderTestSuite makes sure all the other tests run correctly, basically just a 
   * compressor method
   * 
   * @return true if all the other tests work, false if one or more fails
   */
  public static boolean runInboxReaderTestSuite() 
  {
    if(testStackConstructorIsEmptyPushPeek() == false)
    {
      return false;
    }
    if(testStackPop() == false)
    {
      return false;
    }
    if(testInboxReadMessage() == false)
    {
      return false;
    }
    if(testInboxReceiveMessage() == false)
    {
      return false;
    }
    if(testInboxMarkAllMessagesAsRead() == false)
    {
      return false;
    }
    if(testPeekReadMessage() == false)
    {
      return false;
    }
    if(testEmptyReadMessageBox() == false)
    {
      return false;
    }
    if(testMessageStackIterator() == false)
    {
      return false;
    }
    return true;
  }

}