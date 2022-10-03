//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    RestaurantOrders - Object class that manipulates the LinkedOrder's double linked list
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

public class RestaurantOrders implements SortedListADT<Order>
{
  private LinkedOrder head;
  private LinkedOrder tail;
  private int size;
  private final int CAPACITY;
  
  /**
   * This constructor creates a RestaurantOrders object with no arguments,
   * assigning the CAPACITY field to 20
   */
  public RestaurantOrders()
  {
    CAPACITY = 20;
  }
  
  /**
   * This constructor creates a RestaurantOrders object with one parameter (capacity)
   * @param capacity, int representing the maximum size of this faux-list
   * @throws IllegalArgumentException if the capacity input parameter is less than or equal to 0
   */
  public RestaurantOrders(int capacity) throws IllegalArgumentException
  {
    if(capacity <= 0)
    {
      throw new IllegalArgumentException("Capacity can not be a number less than or equal to zero");
    }
    CAPACITY = capacity;
  }
  
  /**
   * capacity returns the CAPACITY instance field of the RestaurantOrders object
   * @return int, representing the CAPACITY instance field
   */
  public int capacity()
  {
    return CAPACITY;
  }
  
  /**
   * readForward translates the faux list into a readable format for humans (front to back)
   * @return String representing the data typed out in a legible list
   */
  public String readForward()
  {
    String r = "";
    try // check case for no elements in the array
    {
      r += head.getOrder().toString();
      r += tail.getOrder().toString();
    }
    catch(NullPointerException e)
    {
      r = "This list contains 0 order(s)";
      return r;
    }

    r = "This list contains " + size + " order(s): [ ";
    LinkedOrder temp = head;
    if(head.equals(tail)) // check case for one element in the array
    {
      r += head.getOrder().toString();
      r += " ]";
      return r;
    }
    
    while(!temp.equals(tail))
    {
      r += temp.getOrder().toString() + " ";
      temp = temp.getNext();
    }
    r += tail.getOrder().toString();
    r += " ]";
    return r;
  }
  
  /**
   * readBackward is nearly identical to readForward, except the string is back to front
   * @return String representing the data typed out backwards in a legible list
   */
  public String readBackward()
  {
    String r = "";
    try // check case for no elements in the array
    {
      r += head.getOrder().toString();
      r += tail.getOrder().toString();
    }
    catch(NullPointerException e)
    {
      r = "This list contains 0 order(s)";
      return r;
    }

    r = "This list contains " + size + " order(s): [ ";
    LinkedOrder temp = tail;
    if(head.equals(tail)) // check case for one element in the array
    {
      r += head.getOrder().toString();
      r += " ]";
      return r;
    }
    
    while(!temp.equals(head))
    {
      r += temp.getOrder().toString() + " ";
      temp = temp.getPrevious();
    }
    r += head.getOrder().toString();
    r += " ]";
    return r;
  }
  
  /**
   * clear sets the head and tail to null, essentially wiping the list (also sets size to 0)
   */
  public void clear()
  {
    head = null;
    tail = null;
    size = 0;
  }
  
  /**
   * get accesses the Order object at input index parameter of the list and returns it
   * @param index, int representing index user wishes to search
   * @return Order, object at element index of the faux list
   */
  public Order get(int index)
  {
    LinkedOrder temp = head;
    for(int i = 0; i < index; i++)
    {
      temp = temp.getNext();
    }
    return temp.getOrder();
  }
  
  /**
   * indexOf finds the input Order in the faux list and returns where it is
   * @param findOrder, Order object which user wishes to find the index of
   * @return int, representing which element the 
   */
  public int indexOf(Order findOrder)
  {
    LinkedOrder temp = head;
    int index = 0;
    boolean isFound = false;
    while(!temp.getOrder().toString().equals(findOrder.toString()))
    {
      temp = temp.getNext();
      index++;
    }
    if(temp.getOrder().toString().equals(findOrder.toString()))
    {
      isFound = true;
    }
    if(isFound = false)
    {
      return -1;
    }
    return index;
  }
  
  /**
   * isEmpty checks to see if the faux list is empty by seeing if the head exists. If it doesn't,
   * the list can be assumed empty
   * @return boolean, true if the list is empty and false if it contains elements
   */
  public boolean isEmpty()  
  {
    if(head != null)
    {
      return false;
    }
    return true;
  }
  
  /**
   * placeOrder adds an Order object after wrapping it into a LinkedOrder container, then inserts
   * it where it fits in terms of timestamp comparisons. The lower the timestamp, the earlier
   * in the list it will be placed
   * @param newOrder, Order object to be added to the RestaurantOrders list
   */
  public void placeOrder(Order newOrder)
  {
    if(size >= CAPACITY)
    {
      return;
    }
    Order testOrder = new Order("food", 0); //generic exception handling block
    try
    {
      if(newOrder.compareTo(testOrder) == -1)
      {
        throw new IllegalArgumentException("The new order's timestamp can't be less than 0");
      }
    }
    catch(NullPointerException e)
    {
      throw new IllegalArgumentException("The new order's timestamp can't be null");
    }
    
    try // base check for an empty array, if empty, sets newOrder as head and tail
    {
      head.getOrder();
    }
    catch(NullPointerException e)
    {
      LinkedOrder newHead = new LinkedOrder(newOrder);
      head = newHead;
      tail = newHead;
      size++;
      return;
    }
    
    if(head.equals(tail)) // base check for one element array, sets new element as tail
    {
      if(head.getOrder().compareTo(newOrder) == 1) //if head is greater than newOrder
      {
        LinkedOrder newHead = new LinkedOrder(newOrder, null, head);
        head.setPrevious(newHead);
        tail = head;
        head = newHead;
        size++;
        return;
      }
      if(head.getOrder().compareTo(newOrder) == -1) //if head is less than newOrder
      {
        LinkedOrder newTail = new LinkedOrder(newOrder, head, null);
        head.setNext(newTail);
        tail = newTail;
        size++;
        return;
      }
      if(head.getOrder().compareTo(newOrder) == 0) //if head is equal to newOrder
      {
        throw new IllegalArgumentException("The new order's timestamp is the same as another's");
      }
    }
    
    LinkedOrder temp = head;
    while(!temp.equals(tail)) //while loop to check if element needs to be inserted not at the end
    {
      if(newOrder.compareTo(temp.getOrder()) == 0)
      {
        throw new IllegalArgumentException("The new order's timestamp is the same as another's");
      }
      if(newOrder.compareTo(temp.getOrder()) == 1) //greater than, shift temp over
      {
        temp = temp.getNext();
      }
      if(newOrder.compareTo(temp.getOrder()) == -1) //less than, insert element
      {
        LinkedOrder newElement = new LinkedOrder(newOrder, temp.getPrevious(), temp);
        temp.getPrevious().setNext(newElement);
        temp.setPrevious(newElement);
        size++;
        return;
      }
    }
    if(newOrder.compareTo(tail.getOrder()) == 1)
    {
      LinkedOrder newTail = new LinkedOrder(newOrder, tail, null);
      tail.setNext(newTail);
      tail = newTail;
      size++;
      return;
    }   
  }
  
  /**
   * removeOrder takes an input index and removes that element from the list, adjusting the
   * pointers of the LinkedOrder objects around it
   * @param index, int representing which element to remove
   * @return Order representing which element was removed
   */
  public Order removeOrder(int index)
  {
    if(size == 1 && index == 0) // in case there is only one element to remove
    {
      LinkedOrder tempReturn = head;
      head = null;
      tail = null;
      size--;
      return tempReturn.getOrder();
    } 

    if(index > size || size == 0 || index < 0)
    {
      throw new IndexOutOfBoundsException("The given index is out of bounds for the list");
    }
    LinkedOrder temp = head;
    for(int i = 0; i < index; i++) //maybe not <=!!!!!!!!!!
    {
      temp = temp.getNext();
    }
    if(temp.equals(tail))
    {
      tail.getPrevious().setNext(null);
      tail = tail.getPrevious();
      size--;
      return temp.getOrder();
    }
    if(temp.equals(head))
    {
      head.getNext().setPrevious(null);
      head = head.getNext();
      size--;
      return temp.getOrder();
    }
    else
    {
      temp.getNext().setPrevious(temp.getPrevious());
      temp.getPrevious().setNext(temp.getNext());
      size--;
    }
    return temp.getOrder();
  }
  
  /**
   * size returns the size field of the RestaurantOrders object
   * @return int representing the numer of elements/size of the list
   */
  public int size()
  {
    return size;
  }
  
}
