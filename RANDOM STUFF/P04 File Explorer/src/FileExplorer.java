//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    FileExplorer - Acts as a file explorer through java
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

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FileExplorer 
{
  /**
   * listContents gives the user a list of names of files/folders within a folder
   * @param currentFolder, File representing the current folder opened
   * @return ArrayList<String>  a list of the current folders within a directory
   * @throws NotDirectoryException if the currentFolder doesn't exist, throws this with message
   */
  public static ArrayList<String> listContents(File currentFolder)
      throws NotDirectoryException
  {
    ArrayList<String> returnList = new ArrayList<String>(0);
    String[] temp = currentFolder.list();
    try 
    {
      for(int i = 0; i < temp.length; i++)
      {
        returnList.add(temp[i]);
      }
    }
    catch(NullPointerException e)
    {
      throw new NotDirectoryException("The input directory is not a directory/doesn't exist");
    }
    return returnList;
  }
  
  /**
   * deepListContents lists ALL names of files in the given folder and subfolders
   * @param currentFolder, File representing the current folder opened
   * @return ArrayList<String> a list of ALL the files contained within a superdirectory
   * @throws NotDirectoryException if the currentFolder doesn't exist, throws this with message
   */
  public static ArrayList<String> deepListContents(File currentFolder)
      throws NotDirectoryException
  {
    if(currentFolder == null) throw new NotDirectoryException("The input directory is null");
    ArrayList<String> returnList = new ArrayList<String>(0);
    File[] temp = currentFolder.listFiles();
    String[] toSearch = null;
    try
    {
      for(int i = 0; i < temp.length; i++)
      {
        toSearch = temp[i].list();
        try 
        {
          if(toSearch == null) //adds the filename if it cannot be expanded like a directory
          {
            returnList.add(temp[i].getName());
          }
          else
          {
            returnList.addAll(deepListContents(temp[i])); //if it can be expanded, recursion is called
          }
        }
        catch(NullPointerException e)
        {
          throw new NotDirectoryException("The input directory is not a directory/doesn't exist");
        }
      }   
    }
    catch(NullPointerException e)
    {
      throw new NotDirectoryException("Input file was not a directory");
    }
    return returnList;
  }
  
  /**
   * searchByNameHelper is basically deepListContents but configured to return a File ArrayList as
   * opposed to a String ArrayList
   * @param currentFolder, File representing the current folder opened
   * @return ArrayList<File> a list of ALL the files contained within a superdirectory
   * @throws NotDirectoryException if the currentFolder doesn't exist, throws this with message
   */
  private static ArrayList<File> searchByNameHelper(File currentFolder)
      throws NotDirectoryException
  {
    ArrayList<File> returnList = new ArrayList<File>(0);
    File[] temp = currentFolder.listFiles();
    String[] toSearch = null;
    for(int i = 0; i < temp.length; i++)
    {
      toSearch = temp[i].list();
      try 
      {
        if(toSearch == null)
        {
          returnList.add(temp[i]);
        }
        else
        {
          returnList.addAll(searchByNameHelper(temp[i]));
        }
      }
      catch(NullPointerException e)
      {
        
      }
    }   
    return returnList;
  }
  /**
   * searchByName looks for an exact match of an input string throughout an entire directory
   * @param currentFolder, File representing which folder is open
   * @param fileName, File user is searching for
   * @return String indicating the path to the searched for file
   * @throws NoSuchElementException if the search finds nothing
   */
  public static String searchByName(File currentFolder, String fileName) 
  throws NoSuchElementException
  {
    //the following few lines handle the expected throws from various errors
    if(fileName == null) throw new NoSuchElementException("File name is null");
    if(currentFolder == null) throw new NoSuchElementException("Directory is null");
    try
    {
      if(currentFolder.listFiles().length == 0) throw new NoSuchElementException("Search Query is "
          + "not a directory");
    }
    catch(NullPointerException e)
    {
      throw new NoSuchElementException("Search Query is not of a directory");
    }
    String r = "error";
    try
    {
      ArrayList<File> finalAccessorArray = searchByNameHelper(currentFolder);
      ArrayList<String> searchArrayTemp = deepListContents(currentFolder);
      for(int i = 0; i < searchArrayTemp.size(); i++)
      {
        if(searchArrayTemp.get(i).equals(fileName))
          {
          File toConvert = finalAccessorArray.get(i);
          r = toConvert.getPath();
          break;
          }
      }
    }
    catch(NotDirectoryException e)
    {
      
    }
    if(r.equals("error")) throw new NoSuchElementException("File not found");
    return r;
 // Searches the given folder and all of its subfolders for an exact match
 // to the provided fileName. This method must be recursive or must use a
 // recursive private helper method to operate.
 // This method returns a path to the file, if it exists.
 // Throws NoSuchElementException with a descriptive error message if the
 // search operation returns with no results found (including the case if
 // fileName is null or currentFolder does not exist, or was not a directory)
  }
  
  /**
   * searchByKey looks for a input String contained in all the files in a directory
   * @param currentFolder, File representing which folder is open
   * @param key, String representing what part user is looking for in a file
   * @return ArrayList<String> with names of files which contained the input key
   */
  public static ArrayList<String> searchByKey(File currentFolder, String key)
  {
    ArrayList<String> returnList = new ArrayList<String>(0);
    try 
    {
      ArrayList<String> searchArrayList= deepListContents(currentFolder);
      for(int i = 0; i < searchArrayList.size(); i++)
      {
        if(searchArrayList.get(i).contains(key)) 
        {
          returnList.add(searchArrayList.get(i));
        }
      }
    }
    catch(NotDirectoryException e)
    {
      
    }
    catch(NullPointerException e)
    {
      
    }
    return returnList;
  }

  /**
   * searchBySize looks for files within a bounding of data sizes
   * @param currentFolder, File representing which folder is open
   * @param sizeMin, long representing upper bound of data storage
   * @param sizeMax, long representing lower bound of data storage
   * @return ArrayList<String> of files that fell within the data bounds
   */
  public static ArrayList<String> searchBySize(File currentFolder, long sizeMin,
      long sizeMax)
  {
    long checkFor = 0;
    ArrayList<String> returnList = new ArrayList<String>(0);
    try 
    {
      ArrayList<File> searchArray = searchByNameHelper(currentFolder);
      for(int i = 0; i < searchArray.size(); i++)
      {
        checkFor = searchArray.get(i).length();
        if(checkFor >= sizeMin && checkFor <= sizeMax)
        {
          returnList.add((searchArray.get(i).getName()));
        }
      }
    }
    catch(NotDirectoryException e)
    {
      
    }
    catch(NullPointerException e)
    {
      
    }
    return returnList;
      // Recursive method that searches the given folder and its subfolders for
      // ALL files whose size is within the given max and min values, inclusive.
      // Returns an array list of the names of all files whose size are within
      // the boundaries and an empty arraylist if the search operation returns
      // with no results found (including the case where currentFolder is not a directory).
  }

}
