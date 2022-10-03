//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    MessageStackIterator - An interator object that cycles through a linked list of
//                                  Message objects
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MessageStackIterator is a class that combs through linked lists of Messages
 * @author casey
 *
 */
public class MessageStackIterator implements Iterator<Message>
{
  private LinkedNode<Message> next;
  
  /**
   * This constructor creates a new MessageStackIterator object with one input parameter
   * @param top, LinkedNode<Message> to be added to the linked list MSI object.
   */
  public MessageStackIterator(LinkedNode<Message> top)
  {
    next = top;
  }
  
  /**
   * hasNext sees if the current linked node has a next reference field
   * 
   * @return boolean, true if there is a pointer for the current LinkedNode<Message>
   *                  false if there is no pointer/the list ends
   */
  @Override
  public boolean hasNext() 
  {
    try
    {
      if(next == null)
      {
        return false;
      }
      else
      {
        return true;
      }
    }
    catch(NullPointerException e)
    {
      return false;
    }
  }

  /**
   * next returns the object currently at the top of the stack and assigns a new top reference point
   * 
   * @return Message representing the current object the iterator is on
   * @throws NoSuchElementException if the stack has no further elements to iterate through
   */
  @Override
  public Message next()
  {
    if(next == null)
    {
      throw new NoSuchElementException("The current stack has no more elements after this");
    }
    else
    {
      LinkedNode<Message> temp = next;
      next = next.getNext();
      return temp.getData();
    }
  }

}
