//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    MoveQueue - a class which structures BattleCharacter objects in a max-heap.
//                       various methods are included which can manipulate and analyze the heap.
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

public class MoveQueue implements PriorityQueueADT<BattleCharacter>
{
  private BattleCharacter[] data;
  private int size;

  /**
   * no argument constructor that initializes data field to array of length 10
   */
  public MoveQueue()
  {
    data = new BattleCharacter[10];
  }
  
  /**
   * one argument constructor that initializes data field to array of custom length
   * @param capacity, int representing how long the data field array will be
   */
  public MoveQueue(int capacity)
  {
    if(capacity <= 0)
    {
      throw new IllegalArgumentException("The capacity cannot be zero or negative");
    }
    data = new BattleCharacter[capacity];
  }
  
 /**
  * Returns a String representation of the current contents of the MoveQueue
  * in order from first to last.
  * @author Michelle
  * @return String representing what the MoveQueue looks like formatted
  */
  @Override
  public String toString()
  {
    String s = "[ ";
    for(int i =0; i< size; i++)
    {
      s += (data[i].toString() + " | ");
    }
    s += ("]");
    return s;
  }
  
  /**
   * isEmpty() checks if the MoveQueue's data field is empty or not
   * @return boolean, true if it is empty and false if it contains elements
   */
  @Override
  public boolean isEmpty() 
  {
    if(size == 0) return true;
    else return false;
  }
  
  /**
   * size() returns the MoveQueue's size field
   */
  @Override
  public int size() 
  {
    return size;
  }
  
  /**
   * enqueue() adds the import element to the end of the heap 
   * and percolates the element accordingly so that the result is a sorted heap
   * @param element, the object to add to the MoveQueue's data array
   * @throws IllegalArgumentException if the array is full and there's no room
   *         NullPointerException if import element is null
   */
  @Override
  public void enqueue(BattleCharacter element) 
  {
    //System.out.println(element.toString());
    //System.out.println(size);
    if(size == 0) //if the array is empty
    {
      //System.out.println(element.toString() + " is the root");
      data[0] = (BattleCharacter)element;
      size++;
      return;
    }
    if(data.length == size) //makes sure there is room in the array
    {
      throw new IllegalArgumentException("There is no room in the data array"); //possible error her
    }

    if(element == null)
    {
      throw new NullPointerException("The element cannot be null");
    }
    
    data[size] = (BattleCharacter)element;
    percolateUp(size);
    size++; //size adjusted before or after?
  }
  
  /**
   * percolateUp() compares a given element at an import index and compares it to its parents.
   * If it is greater, it swaps positions with its parents and continues recursively until
   * it meets its base condition where it is either the root or less than its parent
   * @param i, int representing the index where the percolating element is
   */
  protected void percolateUp(int i)
  {
    if(((i-1)/2) < 0) //if element is at the root
    {
      return;
    }
    if(data[i].compareTo(data[(i-1)/2]) == 1) //if element is larger
    {
      //System.out.println("percolateUp go -----");
      //System.out.println(data[i].toString() + " is the child node");
      //System.out.println(data[(i-1)/2].toString() + " is the parent node");
      BattleCharacter tempNode = data[i];
      //System.out.println(tempNode.toString() + " is the temporary node");
      data[i] = data[(i-1)/2];
      //System.out.println(data[i].toString() + " data[i] has been changed");
      data[(i-1)/2] = tempNode;
      //System.out.println(data[(i-1)/2].toString() + " is the new parent");
      //System.out.println(data[i].toString() + " is the new child\n-------");
      percolateUp((i-1)/2);
    }
    if(data[i].compareTo(data[(i-1)/2]) <= 0) //if element is smaller or equal
    {
      return;
    }
  }
   
  /**
   * dequeue() takes the element at the root of the heap and returns it to the user, then adjusts
   * the array using downward percolation, organizing the tree as it goes. It takes the last element
   * in the tree then adjusts it down by the way.
   * @return BattleCharacter, the element that was originally at the root.
   * @throws NoSuchElementException if the given MoveQueue data array is empty
   */
  @Override
  public BattleCharacter dequeue()
  {
    if(size == 0)
    {
      throw new NoSuchElementException("The array is empty, cannot dequeue from an empty array");
    }
    
    BattleCharacter returnNode = data[0];
    BattleCharacter newRoot = data[size-1];
    data[0] = newRoot;
    percolateDown(0);
    size--;
    return returnNode;
  }
  
  /**
   * percolateDown() compares a parent node with its children and swaps with the child that has
   * the greatest value until it meets its base condition of having no more children. This method
   * is recursively called until complete
   * @param i, int representing the index where the percolating element is
   */
  protected void percolateDown(int i)
  {
    
    if((i*2) + 1 > data.length) //if index does not exist, avoid IndexOutOfBounds
    {
      return;
    }
    if(data[(i*2) + 1] == null || data[(i*2) + 2] == null) //if left child is empty the right is also
    {
      return;
    }
    
    int chooseFactor = data[(i*2) + 1].compareTo(data[(i*2) + 2]);
    //if chooseFactor is 1 left child is greater than right child
    //if chooseFactor is 0 technically not possible
    //if chooseFactor is -1 right child is greater than left child
    
    if(chooseFactor == 1) //prioritize left first
    {
      //System.out.println("I do be percolatin' ------------------");
      if(data[i].compareTo(data[i*2 + 1]) == -1) //if parent is less than (left) child
      {
        BattleCharacter tempNode = data[i];
        data[i] = data[(i*2) + 1];
        data[(i*2) + 1] = tempNode;
        percolateDown((i*2) + 1);
      }
      if(data[i].compareTo(data[(i*2) + 2]) < 1) //if parent is less than (right) child
      {
        BattleCharacter tempNode = data[i];
        data[i] = data[(i*2) + 2];
        data[(i*2) + 1] = tempNode;
        percolateDown((i*2) + 2);
      }
    }
    
    if(chooseFactor == -1) //prioritize right first
    {
      if(data[i].compareTo(data[(i*2) + 2]) < 1) //if parent is less than (right) child
      {
        BattleCharacter tempNode = data[i];
        data[i] = data[(i*2) + 2];
        data[(i*2) + 2] = tempNode;
        percolateDown((i*2) + 2);
      }
      if(data[i].compareTo(data[i*2 + 1]) < 1) //if parent is less than (left) child
      {
        BattleCharacter tempNode = data[i];
        data[i] = data[(i*2) + 1];
        data[(i*2) + 1] = tempNode;
        percolateDown((i*2) + 1);
      }
    }
  }
  
  /**
   * peekBest() returns the element at the root of the MoveQueue's data array without actually
   * removing it from the data array
   * @return BattleCharacter that is at the root of the data array
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public BattleCharacter peekBest() 
  {
    if(size == 0)
    {
      throw new NoSuchElementException("The queue is empty");
    }
    return data[0];
  }
  
  /**
   * clear() empties the array of all its nodes and sets it to null
   */
  @Override
  public void clear() 
  {
    for(int i = 0; i < data.length; i++)
    {
      data[i] = null;
    }  
  }
  
  /**
   * heapify() organizes a given binary tree and turns it into a max heap. This method is recursive
   * and its base case is when no more adjustments or switches are detected.
   */
  protected void heapify()
  {
    int rightLeaf = size-1;
    int leftLeaf = size-2;
    int prioSwap = -1;
    int swapFactor = 0;
    BattleCharacter tempNode = null;
    while(leftLeaf >= 0)
    {
      if(data[((rightLeaf)-1)/2].compareTo(data[rightLeaf]) == -1 ||
         data[((leftLeaf)-1)/2].compareTo(data[leftLeaf]) == -1)
      {
        if(data[rightLeaf].compareTo(data[leftLeaf]) == 1) //if right > left
        {
          prioSwap = rightLeaf;
        }
        else
        {
          prioSwap = leftLeaf;
        }
        tempNode = data[prioSwap];
        data[prioSwap] = data[((rightLeaf)-1)/2];
        data[((rightLeaf)-1)/2] = tempNode;
        swapFactor++;
      }
      rightLeaf = rightLeaf - 2;
      leftLeaf = leftLeaf - 2;
      if(swapFactor > 0)
      {
        rightLeaf = size-1;
        leftLeaf = size-2;
        swapFactor = 0;
      }
    }

  }

  /**
   * updateCharacter takes an import parameter of the character to updated, finds that in the
   * MoveQueue array and updates the old character with the new import parameter character. If the
   * character is dead, they are removed from the array and the rest of the array is filled in.
   * @param updateChara, BattleCharacter representing the new character with new stats and such.
   */
  public void updateCharacter(BattleCharacter updateChara)
  {
    /*
    * TODO:
    * (1) Find matching character in the MoveQueue
    * (Rely on the BattleCharacter.equals() method to find the match with updated).
    * (2) Replace it with the updated version of the character.
    * If character is dead, remove it entirely.
    * (3) Make sure the structure is maintained.
    * Note: You can also use the code below to eliminate holes (null references)
    * in the array. Then, call heapify() method to eliminate all the ordering violations.
    */
    int searchKey = 0;
    for(int i = 0; i < data.length; i++)
    {
      if(data[i].equals(updateChara))
      {
        break;
      }
      searchKey++;
    }
    data[searchKey] = updateChara;
    if(data[searchKey].isAlive() == false)
    {
      for (int i = searchKey; i < size; i++) //does not reach this loop
      {
        data[i] = data[i + 1];
      }
      data[size-1] = null;
      size--;
      heapify();   
    }
  }
}
