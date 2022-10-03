//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LinkedOrder - Object structures that act as a doubly linked list
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

import java.lang.IllegalArgumentException;

public class LinkedOrder
{
  private final Order ORDER;
  private LinkedOrder previous;
  private LinkedOrder next;
  
  /**
   * This constructor makes a LinkedOrder object with one parameter (order)
   * @param order, Order object representing the Order to be wrapped
   * @throws IllegalArgumentException if order's timestamp is negative
   */
  public LinkedOrder(Order order) 
      throws IllegalArgumentException
  {
    previous = null;
    next = null;
    Order compare = new Order("food", 0);
    if(order.compareTo(compare) == -1)
    {
      throw new IllegalArgumentException("Order's timestamp is negative");
    }
    ORDER = order;   
  }
  
  /**
   * This constructor makes a LinkedOrder object with three parameters (order, prev, next)
   * @param order, Order object representing the Order to be wrapped
   * @param prev, LinkedOrder object this LinkedOrder points to previously
   * @param next, LinkedOrder object this LinkedOrder points to next
   * @throws IllegalArgumentException if the order's timestamp is negative
   */
  public LinkedOrder(Order order, LinkedOrder prev, LinkedOrder next)
      throws IllegalArgumentException
  {
    previous = prev;
    this.next = next;
    Order compare = new Order("food", 0);
    if(order.compareTo(compare) == -1)
    {
      throw new IllegalArgumentException("Order's timestamp is negative");
    }
    ORDER = order;
  }
  
  /**
   * getOrder returns the ORDER instance field
   * @return ORDER, final Order representing data within this LinkedOrder object
   */
  public Order getOrder()
  {
    return ORDER;
  }
  
  /**
   * getPrevious returns the LinkedOrder's previous pointer
   * @return previous, pointer to a LinkedOrder object previously
   */
  public LinkedOrder getPrevious()
  {
    return previous;
  }
  
  /**
   * getNext returns the LinkedOrder's next pointer
   * @return next, pointer to a LinkedOrder object next
   */
  public LinkedOrder getNext()  
  {
    return next;
  }
  
  /**
   * setPrevious changes the LinkedOrder's previous pointer
   * @param previous, new pointer to replace the LinkedOrder's old previous pointer
   */
  public void setPrevious(LinkedOrder previous)
  {
    this.previous = previous;
  }
  
  /**
   * setNext changes the LinkedOrder's next pointer
   * @param next, new pointer to replace the LinkedOrder's old next pointer
   */
  public void setNext(LinkedOrder next)
  {
    this.next = next;
  } 
  
}
