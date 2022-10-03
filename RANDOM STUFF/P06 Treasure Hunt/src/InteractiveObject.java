//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    InteractiveObject - Super parent method for a bunch of other derived classes
//                               which use this class's foundations to do various other things
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

import processing.core.PApplet;

public class InteractiveObject 
{
  private final String NAME;
  private boolean isActive;
  private static PApplet processing = null;
  
  /**
   * This constructor creates an InteractiveObject object with one argument (name),
   * and sets its activity to true, meaning it can be interacted with and is visible
   * @param name, String representing the title of the object
   */
  public InteractiveObject(String name)
  {
    this.NAME = name;
    isActive = true;
  }
  
  /**
   * setProcessing initializes the processing field for the InteractiveObject object
   * @param processing1, the import processing field to initialize into
   */
  public static void setProcessing(PApplet processing1)
  {
    processing = processing1;
  }
  
  /**
   * getProcessing returns the processing field which is otherwise static
   * @return PApplet, the object type processing is
   */
  protected static PApplet getProcessing()
  {
    return processing;
  } 

  /**
   * hasName tests if the import name is the same as the InteractiveObject object's name
   * @param name, String to compare local InteractiveObject object's name to
   * @return boolean, true if the contents are the same and false if otherwise
   */
  public boolean hasName(String name)
  {
    if(name.equals(NAME))
    {
      return true;
    }
    return false;
  }
  
  /**
   * isActive checks if the given InteractiveObject object is active or not
   * @return boolean, true if InteractiveObject object is active and false if it isn't
   */
  public boolean isActive()
  {
    if(isActive == true)
    {
      return true;
    }
    return false;
  }
  
  /**
   * activate changes the InteractiveObject object's isActive field to true
   */
  public void activate()
  {
    isActive = true;
  }
  
  /**
   * deactivate changes the the InteractiveObject object's isActive field to false
   */
  public void deactivate()
  {
    isActive = false;
  }
  
  /**
   * super method for the classes derived from the InteractiveObject class, generic and
   * pretty much useless if I'm being honest
   * @return Action, something to return for the derived classes down the line
   */
  public Action update()
  { 
    return null;
  }
  
}
