//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Action - Assists the TreasureHunt game to work. Specifically useful for interactions
//                    between two objects
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

import java.util.ArrayList;

public class Action 
{
  private String message;
  private InteractiveObject object;
  
  /**
   * This constructor creates an Action object with one argument (object)
   * @param object, InteractiveObject to activate once the Action is called through act()
   */
  public Action(InteractiveObject object)
  {
    this.object = object;
  }
  
  /**
   * This constructor creates an Action object with two arguments (message, object)
   * @param message, String to print to console once the Action is called through act()
   * @param object, InteractiveObject to activate once the Action is called through act()
   */
  public Action(String message, InteractiveObject object)
  {
    this.message = message;
    this.object = object;
  }
  
  /**
   * This constructor creates an Action object with one argument (message)
   * @param message, String to print to console once the Action is called through act()
   */
  public Action(String message)
  {
    this.message = message;
  }
  
  
  /**
   * act is used upon an Action object and interacts as the Action specifies it should. 
   * For example, if an Action contains a message String, it prints it to the console.
   * Likewise, if an Action contains an object InteractiveObject to activate,
   * it activates it, adds it to the import array (usually will be gameObjects) then deactivates 
   * the object locally
   * For both a message and object, these two reactions occur
   * @param o, InteractiveObject ArrayList to add the InteractiveObject object from the Action to
   *           once act() is called
   */
  public void act(ArrayList<InteractiveObject> o)
  {
    if(object != null)
    {
      object.activate();
      o.add(object);
      object = null;
    }
    if(message != null)
    {
      System.out.println(message);
    }
  }

}
