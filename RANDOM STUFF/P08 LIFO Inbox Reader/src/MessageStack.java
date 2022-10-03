//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    MessageStack - creates a linked list of Message Objects with various methods 
//                      
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
import java.util.Iterator;

/**
 * MessageStack establishes an ADT emulating a stack with various methods to analyze and 
 * manipulate said stack
 * @author casey
 *
 */
public class MessageStack implements StackADT<Message>, Iterable<Message>
{
  private LinkedNode<Message> top;
  private int size;
  
  /**
   * push adds an element of type Message to the linked list Stack object
   * @param element, a Message object to add to the MessageStack object
   */
  @Override
  public void push(Message element)
  {
    if(size == 0)
    {
      LinkedNode<Message> newTop = new LinkedNode<Message>(element);
      top = newTop;
      size++;
    }
    else
    {
      LinkedNode<Message> newTop = new LinkedNode<Message>(element, top);
      top = newTop;
      size++;
    }
  }
  
  /**
   * pop removes the top element from the stack and returns it
   * 
   * @return Message, the element that was at the top of the stack
   * @throws EmptyStackException if there are no elements left in the stack to pop/return
   */
  @Override
  public Message pop() 
  {
    if(size == 0)
    {
      throw new EmptyStackException();
    }
    if(size == 1)
    {
      LinkedNode<Message> returnTop = top;
      top = null;
      size--;
      return returnTop.getData();
    }
    LinkedNode<Message> returnTop = top;
    top = top.getNext();
    size--;
    return returnTop.getData();
  }
  
  /**
   * peek returns the top element of the stack without actually removing it
   * 
   * @return Message, the element on the top of the stack
   * @throws EmptyStackException if there are no more elements to peek at/empty stack
   */
  @Override
  public Message peek() 
  {
    if(size == 0)
    {
      throw new EmptyStackException();
    }
    return top.getData();
  }
  
  /**
   * isEmpty checks if the MessageStack object contains any elements or not
   * 
   * @return boolean true if the stack is empty, false if it contains elements
   */
  @Override
  public boolean isEmpty() 
  {
    if(size == 0)
    {
      return true;
    }
    else
    {
    return false;
    }
  }
  
  /**
   * size returns the size of the stack
   * 
   * @return int representing the size of the stack
   */
  @Override
  public int size() 
  {
    return size;
  }

  /**
   * iterator creates a new Iterator of type message for this specific MessageStack object
   * 
   * @return Iterator<Message> set to this stack's linked list
   */
  @Override
  public Iterator<Message> iterator()
  {
    MessageStackIterator returnIterator = new MessageStackIterator(top);
    return returnIterator;
  }
}
