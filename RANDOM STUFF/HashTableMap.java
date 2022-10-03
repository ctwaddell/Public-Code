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

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>
{
  //INSTANCE VARIABLES
  private int size; //keeps track of elements in the LinkedList array //get all genres method //key set
  private LinkedList<hashTableData>[] keyValuePairsArray; //LinkedList array to store hashTableData objects
  
  //CONSTRUCTORS 
  /**
   * no argument constructor with default capacity of 10
   */
  public HashTableMap()
  {
    keyValuePairsArray = new LinkedList[10];
    for(int i = 0; i < keyValuePairsArray.length; i++)
    {
      keyValuePairsArray[i] = new LinkedList<hashTableData>();
    }
    size = 0;
  }
  
  /**
   * one argument constructor with capacity flexibility
   * @param capacity, int representing the length of the array created
   */
  public HashTableMap(int capacity)
  {
    keyValuePairsArray = new LinkedList[capacity];
    for(int i = 0; i < keyValuePairsArray.length; i++)
    {
      keyValuePairsArray[i] = new LinkedList<hashTableData>();
    }
    size = 0;
  }

  //ACTUAL METHODS
  /**
   * put creates a hashTableData object with param as constructors and then adds it to its 
   * accordant space in the array/hash table
   * @param key, key of any data type for the given object
   * @param value, object data of the object
   * @return boolean true if the data was added without problem and false if errors occurred (already in the array, key == null, etc)
   */
  public boolean put(KeyType key, ValueType value) //rewrite to allow collisions
  {
    if(key == null)
    {
      return false;
    }
    hashTableData toAdd = new hashTableData(key, value);
    int arrayPosition = Math.abs(key.hashCode()) % keyValuePairsArray.length;
    //code to store items in KeyValuePair array
    for(int i = 0; i < keyValuePairsArray[arrayPosition].size(); i++)
    {
      if(keyValuePairsArray[arrayPosition].get(i).getKey().equals(toAdd.getKey().hashCode()))
      {
        return false;
      }
    }
    keyValuePairsArray[arrayPosition].add(toAdd);
    size++;
    growTableHelper();
    return true;
  }

  /**
   * get finds the value of a given key and returns it
   * @param key, key of any data type for the given object
   * @return ValueType denoting the value of the given object found in the table
   * @throws NoSuchElementException if the key isn't found in the hash table
   */
  public ValueType get(KeyType key) throws NoSuchElementException
  {
    int searchQuery = Math.abs(key.hashCode()); 
    int arrayPosition = searchQuery % keyValuePairsArray.length;
    for(int i = 0; i < keyValuePairsArray[arrayPosition].size(); i++)
    {
      if(keyValuePairsArray[arrayPosition].get(i).getKey().equals(key))
      {
        return (ValueType)keyValuePairsArray[arrayPosition].get(i).getValue();
      }
    }
    throw new NoSuchElementException("The key was not found in the hash table");
  }
  
  /**
   * remove is like get(), but it also removes the element from the hash table
   * @param key, key of any data type for the given object
   * @return ValueType denoting the value of the given object removed from the table, also returns
   *         null if the key is not found
   */
  public ValueType remove(KeyType key)
  {
    int searchQuery = Math.abs(key.hashCode()); 
    int arrayPosition = searchQuery % keyValuePairsArray.length;
    for(int i = 0; i < keyValuePairsArray[arrayPosition].size(); i++)
    {
      if(keyValuePairsArray[arrayPosition].get(i).getKey().equals(key))
      {
        size--;
        return (ValueType)keyValuePairsArray[arrayPosition].remove(i).getValue();
      }
    }
    return null;
  }
 
  /**
   * containsKey tells the user if the hash table has an element with the key specified
   * @param key, key of any data type for the given object
   * @return boolean true if the key is found and false if it's not in the hash table
   */
  public boolean containsKey(KeyType key)
  {
    int searchQuery = Math.abs(key.hashCode()); 
    int arrayPosition = searchQuery % keyValuePairsArray.length;
    for(int i = 0; i < keyValuePairsArray[arrayPosition].size(); i++)
    {
      if(keyValuePairsArray[arrayPosition].get(i).getKey().equals(key))
      {
        return true;
      }
    }
    return false;
  }
  
  /**
   * growTableHelper grows the table size/capacity is greater than/equal to .85
   * if this is the case, a temporary array is created, filled will all elements currently in the hash table,
   * then the temp array replaces the original array
   */
  private void growTableHelper()
  {
    if((((double)size)/((double)keyValuePairsArray.length)) >= .85)
    {
      //code to expand array goes here
      HashTableMap toReplaceArray = new HashTableMap(2*keyValuePairsArray.length);
      for(int i = 0; i < keyValuePairsArray.length; i++) //loops through each head of linked list in array
      {
        for(int j = 0; j < keyValuePairsArray[i].size(); j++) //loops through the branches
        {
          toReplaceArray.put(keyValuePairsArray[i].get(j).getKey(), keyValuePairsArray[i].get(j).getValue());
        }
      }
      keyValuePairsArray = toReplaceArray.getArrayHelper();
    }
  }
  
  /**
   * clear removes all and sets size to 0... it's probably not considerate of memory but it works
   */
  public void clear() 
  {
    for(int i = 0; i < keyValuePairsArray.length; i++)
    {
      keyValuePairsArray[i] = new LinkedList<hashTableData>();
    }
    size = 0;
  }
  
  /**
   * size returns the size field of the hash table (actual occupancy, not capacity)
   * @return int representing the size/number of elements in the hash table
   */
  public int size()
  {
    return size;
  }

  /**
   * getArrayHelper is a simple helper method so that growTableHelper() works when copying in the new array
   * this is also used to make sure the capacity is updated correctly in my test method as
   * permitted by the one piazza post where Professor Dahl says "Yes, that will be fine."
   * @return LinkedList<HashTableData>[], the data field of a HashTableMap object
   */
  public LinkedList<hashTableData>[] getArrayHelper()
  {
    return keyValuePairsArray;
  }


  /**
   * hashTableData is a helper class which wraps key and value data into one object to ensure
   * the program runs with LinkedList. Once again, only public so the test method works
   * @author casey waddell
   *
   * @param <KeyType>, key to be stored in the hashTableData object
   * @param <ValueType>, value to be stored in the hashTableData object
   */
  public class hashTableData<KeyType, ValueType> //helper class for the linked list to work well
  {
    //INSTANCE VARIABLES
    private KeyType key;
    private ValueType value;
    
    //CONSTRUCTORS
    /**
     * hashTableData is the only constructor for this class
     * @param key, key to be stored in the hashTableData object
     * @param value, value to be stored in the hashTableData object
     */
    public hashTableData(KeyType key, ValueType value)
    {
      this.key = key;
      this.value = value;
    }
    
    //ACTUAL METHODS
    /**
     * getKey returns the key data field
     * @return KeyType, key data field
     */
    public KeyType getKey()
    {
      return key;
    }
    
    /**
     * getValue returns the value data field
     * @return ValueType, value data field
     */
    public ValueType getValue()
    {
      return value;
    }
  }
  
  /*public static void main(String[] args)
  {
    HashTableMap swag = new HashTableMap<Integer, String>(12);
    String s = "swagman"; 
    String a = "ricardo";
    swag.put(a, a);
    int hashslinger = Math.abs(s.hashCode());
    System.out.println(hashslinger);
    System.out.println(hashslinger % swag.keyValuePairsArray.length);
    int daKey = hashslinger % swag.keyValuePairsArray.length;
    swag.put(daKey, s);
    System.out.println(swag.get(a).toString());
    //WE DONT NEED TO STORE BOTH KEY AND VALUE, KEY CAN BE CALCULATED WHENEVER WE NEED
  }*/
  
}
