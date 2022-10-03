//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    FileExplorerTester - tests the FileExplorer class
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

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;
import java.nio.file.NotDirectoryException;
import java.util.NoSuchElementException;

public class FileExplorerTester
{
  /**
   * testListContents tests if the listContents method in FileExplorer works properly
   * @param folder, File representing majority directory to use for testing
   * @return boolean, true if the tested method works properly, false if an error occurred
   */
  public static boolean testListContents(File folder)
  {
    try 
    {
      // Scenario 1
      // list the basic contents of the cs300 folder
      ArrayList<String> listContent = FileExplorer.listContents(folder);
      // expected output content
      String[] contents = new String[] {"grades", "lecture notes", "programs",
      "quizzes preparation", "reading notes", "syllabus.txt", "todo.txt"};
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 7) 
      {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++)
      {
        if (!listContent.contains(expectedList.get(i))) 
        {
            System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
            return false;
        }
      }
      
      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FileExplorer.listContents(f);
      if (listContent.size() != 0) 
      {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      
      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs"
      + File.separator + "p02");
      listContent = FileExplorer.listContents(f);
      if (listContent.size() != 1 || !listContent.contains("WisconsinPrairie.java"))
      {
        System.out.println("Problem detected: p02 folder must contain only one file named "
            + "WisconsinPrairie.java.");
        return false;
      }
      
      // Scenario 4 - Try to list the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try 
      {
        listContent = FileExplorer.listContents(f);
        System.out.println("Problem detected: Your FileExplorer.listContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not"
            + "a directory.");
        return false;
      } 
      catch (NotDirectoryException e)
      { // catch only the expected exception
        // Expected behavior -- no problem detected
      }
      
      // Scenario 5 - Try to list the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try 
      {
        listContent = FileExplorer.listContents(f);
        System.out.println("Problem detected: Your FileExplorer.listContents() must "
          + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } 
      catch (NotDirectoryException e)
      {
      // catch only the expected exception to be thrown -- no problem detected
      }
    } 
    catch (Exception e) 
    {
      System.out.println("Problem detected: Your FileExplorer.listContents() has thrown"
          + " a non expected exception."); e.printStackTrace();
          return false;
    }
      return true;
    }
  
  /**
   * testDeepListContentsBaseCase tests if the deepListContents method in FileExplorer works 
   * properly for a non-recursive call
   * @param folder, File representing majority directory to use for testing
   * @return boolean, true if the tested method works properly, false if an error occurred
   */
  public static boolean testDeepListContentsBaseCase(File folder)
  {
    try
    {
      //Scenario 1
      //List the basic contents of p01 folder
      ArrayList<String> listContent = FileExplorer.deepListContents(folder);
      // expected output content, also checks size
      String[] contents = new String[] {"COVIDTracker.java", "COVIDTrackerTester.java"};
      List<String> expectedList = Arrays.asList(contents);
      if (listContent.size() != 2) 
      {
        System.out.println("Problem detected: cs300 folder must contain 2 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++)
      {
        if (!listContent.contains(expectedList.get(i))) 
        {
            System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
            return false;
        }
      }
      
      //Scenario 2
      //Empty folder
      ArrayList<String> scenario2Test =  FileExplorer.deepListContents(new File("cs300/grades"));
      if(scenario2Test.size() != 0)
      {
        System.out.println("Problem detected: Expected output of size 0 is not true.");
        return false;
      }
      
      //Scenario 3
      //File input
      try
      {
        ArrayList<String> scenario3Test = FileExplorer.deepListContents(new File("cs300/todo.txt"));
        //though the ArrayList is technically not utilized, that's okay because it's main use is
        //to check for the NotDirectoryException
      }
      catch(NotDirectoryException e)
      {
        //error expected, test passes
      }
    }
    catch(NotDirectoryException e)
    {
      
    }
    return true;
  }
  
  /**
   * testDeepListContentsRecursiveCase tests if the deepListContents 
   * method in FileExplorer works properly for recursive cases
   * @param folder, File representing majority directory to use for testing
   * @return boolean, true if the tested method works properly, false if an error occurred
   */
  public static boolean testDeepListContentsRecursiveCase(File folder)
  {
    try
    {
      //Note that plenty of similar scenarios were tested by base case, and although not identical,
      //the same issues would've been caught in the earlier test method, denoting this one is 
      //essentially just for a recursive call of deepListContent();
      //Scenario 1
      //List the entire contents of programs folder, also checks size
      ArrayList<String> listContent = FileExplorer.deepListContents(folder);
      // expected output content
      String[] contents = new String[] {"COVIDTracker.java", "COVIDTrackerTester.java",
          "WisconsinPrairie.java", "Benchmarker.java", "ComparisonMethods.java", "Program01.pdf", 
          "Program02.pdf", "Program03.pdf"};
      List<String> expectedList = Arrays.asList(contents);
      if (listContent.size() != 8) 
      {
        System.out.println("Problem detected: cs300 folder must contain 8 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++)
      {
        if (!listContent.contains(expectedList.get(i))) 
        {
            System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
            return false;
        }
      }
    }
    catch(NotDirectoryException e)
    {
      
    }
    return true;
  }
  
  /**
   * testSearchByFileName tests if the searchByFileName method in FileExplorer works properly
   * @param folder, File representing majority directory to use for testing
   * @return boolean, true if the tested method works properly, false if an error occurred
   */
  public static boolean testSearchByFileName(File folder)
  {
    //Scenario 1
    //search for the file and the file exists
    String compareTo1 = FileExplorer.searchByName(folder, "COVIDTracker.java");
    String compareTo2 = "cs300\\programs\\p01\\COVIDTracker.java";
    if(!compareTo1.equals(compareTo2))
    {
      return false;
    }
    
    //Scenario 2
    //search for the file that doesn't exist
    try
    {
      compareTo1 = FileExplorer.searchByName(folder, "MissingHUD.exe");
    }
    catch(NoSuchElementException e)
    {
      //as to be expected for success
    }
    
    //Scenario 3
    //search for a null file
    try
    {
      compareTo1 = FileExplorer.searchByName(folder, null);
    }
    catch(NoSuchElementException e)
    {
      //as to be expected for success
    }
    
    //Scenario 4
    //search with a null directory
    try
    {
      compareTo1 = FileExplorer.searchByName(null, "COVIDTracker.java");
    }
    catch(NoSuchElementException e)
    {
      //as to be expected for success
    }
    
    //Scenario 5
    //search a non-directory for a file
    try
    {
      compareTo1 = FileExplorer.searchByName(new File("cs300/lecture notes/unit1/"
          + "ExceptionHandling.txt"), "COVIDTracker.java");
    }
    catch(NoSuchElementException e)
    {
      //as to be expected for success
    }
    
    return true;
  }
  
  /**
   * testSearchByFileKey tests if the searchByFileKey method in FileExplorer works properly
   * @param folder, File representing majority directory to use for testing
   * @return boolean, true if the tested method works properly, false if an error occurred
   */
  public static boolean testSearchByKey(File folder)
  {
    //Scenario 1
    //look for the key in a real directory
    ArrayList<String> compareTo = FileExplorer.searchByKey(folder, ".java");
    String[] compareTo2 = {"COVIDTracker.java", "COVIDTrackerTester.java", "WisconsinPrairie.java",
        "Benchmarker.java", "ComparisonMethods.java", "codeSamples.java"};
    String[] compareTo1 = new String[compareTo.size()];
    int correctCounter = 0;
    for(int i = 0; i < compareTo.size(); i++)
    {
      compareTo1[i] = compareTo.get(i);
    }
    for(int i = 0; i < compareTo2.length; i++)
    {
      if(compareTo1[i].equals(compareTo2[i])) correctCounter++;
    }
    if(correctCounter != compareTo2.length) return false;
    
    //Scenario 2
    //Look for the key in an empty folder
    ArrayList<String> scenario2Test = FileExplorer.searchByKey(new File("cs300/grades"), "17");
    if(scenario2Test.size() != 0) return false;
    
    //Scenario 3
    //Look for the search key with a file input
    ArrayList<String> scenario3Test = FileExplorer.searchByKey(new File("cs300/todo.txt"), "17");
    if(scenario3Test.size() != 0) return false;
    
    return true;
  }
  
  /**
   * testSearchByFileSize tests if the searchByFileSize method in FileExplorer works properly
   * @param folder, File representing majority directory to use for testing
   * @return boolean, true if the tested method works properly, false if an error occurred
   */
  public static boolean testSearchBySize(File folder)
  {
    //Scenario 1
    //Look for the file in a real directory with a real target
    String[] contents = new String[] {"Program01.pdf", "Program03.pdf", "todo.txt"};
    ArrayList<String> scenario1Test = FileExplorer.searchBySize(folder, 100, 1000000);
    List<String> expectedList = Arrays.asList(contents);
    for(int i = 0; i < scenario1Test.size(); i++)
    {
        if (!scenario1Test.contains(expectedList.get(i))) 
        {
            System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
            return false;
        }
    }
    
    //Scenario 2
    //Look for the files in an empty directory
    ArrayList<String> scenario2Test = FileExplorer.searchBySize(new File("cs300/grades"), 0, 100);
    if(scenario2Test.size() != 0) return false;
    
    //Scenario 3
    //Look for the files in a null directory
    ArrayList<String> scenario3Test = FileExplorer.searchBySize(null, 0, 1000000);
    if(scenario3Test.size() != 0) return false;
    
    //Scenario 4
    //Look for non right sized files
    ArrayList<String> scenario4Test = FileExplorer.searchBySize(folder, 10, 11);
    if(scenario4Test.size() != 0) return false;
    
    return true;
  }
  
  public static void main(String[] args) 
  {
    //tests all the test methods and respectively prints out if they work correctly
    System.out.println("testListContents: " + testListContents(new File("cs300")));
    System.out.println("testDeepListContentsBaseCase: " + testDeepListContentsBaseCase(new File(
        "cs300/programs/p01")));
    System.out.println("testDeepListContentsRecursiveCase: " 
        + testDeepListContentsRecursiveCase(new File("cs300/programs")));
    System.out.println("testSearchByFileName: " 
        + testSearchByFileName(new File("cs300/programs")));
    System.out.println("testSearchByKey: " + testSearchByKey(new File("cs300")));
    System.out.println("testSearchByKey: " + testSearchBySize(new File("cs300")));
  }

}
