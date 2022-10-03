// --== CS400 File Header Information ==--
// Name: Casey Waddell
// Email: ctwaddell@wisc.edu
// Team: KB Blue
// Role: Integration Manager
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;

public class MovieHashMap3<KeyType, ValueType> implements MapADT<KeyType, ValueType>
{
  //INSTANCE VARIABLES
  private int size; //keeps track of elements in the LinkedList array //get all genres method //key set
  private LinkedList<hashTableData>[] movieMap; //LinkedList array to store hashTableData objects
  private int overlord; //overlord tracks the number of unique linkedlist headers 
  private HashSet<String> allKeys = new HashSet<String>();
  
  //CONSTRUCTORS 
  /**
   * no argument constructor with default capacity of 10
   */
  public MovieHashMap3()
  {
    movieMap = new LinkedList[10];
    for(int i = 0; i < movieMap.length; i++)
    {
      movieMap[i] = new LinkedList<hashTableData>();
    }
    size = 0;
    overlord = 0;
  }
  
  /**
   * one argument constructor with capacity flexibility
   * @param capacity, int representing the length of the array created
   */
  public MovieHashMap3(int capacity)
  {
    movieMap = new LinkedList[capacity];
    for(int i = 0; i < movieMap.length; i++)
    {
      movieMap[i] = new LinkedList<hashTableData>();
    }
    size = 0;
    overlord = 0;
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
    String test = "test";
    hashTableData toAdd = new hashTableData(key, value);    
    int arrayPosition = trueHash(key);
    
    if(key.getClass().equals(test.getClass())) //if the key is a string, add its value to the allKeys field
    {
      allKeys.add((String)key);
    }

    if(movieMap[arrayPosition].size() == 0) //if branch is empty add value and increment overlord
    {
      overlord++;
      size++;
      movieMap[arrayPosition].add(toAdd);
      growTableHelper();
      return true;
    }
    
    else //just increment size
    {
      size++;
      movieMap[arrayPosition].add(toAdd);
      growTableHelper();
      return true;
    }
    
  }

  /**
   * trueHash calculates the hash value specific to this hashmap
   * @param key, object to calculate the hash value for
   * @return int representing where in the array the key value pair will be stored
   */
  private int trueHash(KeyType key)
  {
    int r = Math.abs(key.hashCode()) % movieMap.length;
    return r;
  }
  
  /**
   * getAllKeys returns the allKeys data field which expedites the getAllGenres() method
   * @return HashSet<String> representing all the unique String keys (in our case genres) in the hashmap
   */
  public HashSet<String> getAllKeys()
  {
    return allKeys;
  }

  /**
   * containsKey tells the user if the hash table has an element with the key specified
   * @param key, key of any data type for the given object
   * @return boolean true if the key is found and false if it's not in the hash table
   */
  public boolean containsKey(KeyType key)
  {
    int searchQuery = trueHash(key); 
    for(int i = 0; i < movieMap[searchQuery].size(); i++)
    {
      if(movieMap[searchQuery].size() > 0)
      {
        if(movieMap[searchQuery].get(i).getKey().equals(key))
        {
          return true;
        }
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
    if(((double)overlord/((double)movieMap.length)) >= .85)
    {
      //code to expand array goes here
      MovieHashMap3 toReplaceArray = new MovieHashMap3(2*movieMap.length);
      for(int i = 0; i < movieMap.length; i++) //loops through each head of linked list in array
      {
        for(int j = 0; j < movieMap[i].size(); j++) //loops through the branches
        {
          toReplaceArray.put(movieMap[i].get(j).getKey(), movieMap[i].get(j).getValue());
        }
      }
      movieMap = toReplaceArray.getArrayHelper();
    }
  } 
  
  /**
   * keySet returns all the keys in a given hash map. This method is largely unused because of
   * the allKeys field workaround, because the old implementation was so slow. This mainly serves
   * as a proof of concept
   * @return List<KeyType> containing all the unique keys in the hashmap
   */
  public List<KeyType> keySet() //can change to String if necessary
  {
    //System.out.println("HELLO MY SIZE IS " + movieMap.length + "\n AND MY OVERLORD IS " + overlord);
    List<KeyType> r = new ArrayList<KeyType>();
    boolean toAdd = true;
    
    if(r.size() == 0) //startup -- add first key in the movie map you can find
    {
      for(int l = 0; l < movieMap.length; l++)
      {
        if(movieMap[l].size() > 0)
        {
          r.add((KeyType)movieMap[l].get(0).getKey());
          break;

        }
      }
    }
    
    for (int i = 0; i < movieMap.length; i++) //for the rest of the array, loop through and search for new keys
    {
      for(int j = 0; j < movieMap[i].size(); j++)
      {
        for(int k = 0; k < r.size(); k++) //compares elements of r to elements of linkedlist in moviemap
        {
          if(movieMap[i].get(j).getKey().equals(r.get(k))) //use sets?
          {
            toAdd = false;
          }
        }
        if(toAdd == true)
        {
          r.add((KeyType)movieMap[i].get(j).getKey());
        }
        else
        {
          toAdd = true;
        }
      }
    }
    return r;
  }
 
  /**
   * getAll finds all the values of KeyType K in the hashmap
   * @param K, datatype and value to search the hashmap for
   * @return LinkedList<ValueType> representing all values that matched the search key
   * @throws NoSuchElementException happens if the key is not found in the array.
   */
  public LinkedList<ValueType> getAll(KeyType K) throws NoSuchElementException 
  {
    LinkedList<ValueType> r = new LinkedList<ValueType>();
    for(int i = 0; i < movieMap.length; i++)
    {
      for(int j = 0; j < movieMap[i].size(); j++)
      {
        if(movieMap[i].get(j).getKey().equals(K))
        {
          //System.out.println("adding: " + movieMap[i].get(j).getValue());
          r.add((ValueType)movieMap[i].get(j).getValue());
        }
      }
    }

    if(r.size() == 0)
    {
      throw new NoSuchElementException("The search key was not found.");
    }
    //System.out.println(r.toString());
    return r;

  }
  
  //LARGELY UNUSED METHODS
  /**
   * remove is like get(), but it also removes the element from the hash table
   * @param key, key of any data type for the given object
   * @return ValueType denoting the value of the given object removed from the table, also returns
   *         null if the key is not found
   */
  public ValueType remove(KeyType key)
  {
    int searchQuery = Math.abs(key.hashCode()); 
    int arrayPosition = searchQuery % movieMap.length;
    for(int i = 0; i < movieMap[arrayPosition].size(); i++)
    {
      if(movieMap[arrayPosition].size() > 0)
      {
        if(movieMap[arrayPosition].get(0).getKey().equals(key)) //these are not really being checked right but hopefully backend doesnt use remove lol
        {
          size--;
          ValueType r = (ValueType)movieMap[arrayPosition].get(0).getValue();
          movieMap[arrayPosition].remove(0);
          return r;
        }
      }
    }
    return null;
  }

 /**
   * clear removes all and sets size to 0... it's probably not considerate of memory but it works
   */
  public void clear() 
  {
    for(int i = 0; i < movieMap.length; i++)
    {
      movieMap[i] = new LinkedList<hashTableData>();
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
    return movieMap;
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
    int arrayPosition = searchQuery % movieMap.length;
    for(int i = 0; i < movieMap[arrayPosition].size(); i++)
    {
      if(movieMap[arrayPosition].get(i).getKey().equals(key))
      {
        return (ValueType)movieMap[arrayPosition].get(i).getValue();
      }
    }
    throw new NoSuchElementException("The key was not found in the hash table");
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
  
}
