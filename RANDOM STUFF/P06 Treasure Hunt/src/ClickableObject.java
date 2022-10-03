//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    ClickableObject - Class for objects that can be clicked to return an action
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

public class ClickableObject extends VisibleObject
{
  private Action action; // action returned from update when this object is clicked
  private boolean mouseWasPressed; // tracks whether the mouse was pressed
                                   // during the last update()
  /**
   * This constructor creates a ClickableObject object with four arguments (name, x, y, action)
   * also utilizes the constructor of VisibleObject (parent class)
   * @param name, String representing the title of this ClickableObject object
   * @param x, int representing where the object is on the window (distance from left to right)
   * @param y, int representing where the object is on the window (distance from top to bottom)
   * @param action, Action returned from update when this ClickableObject object is called
   */
  public ClickableObject(String name, int x, int y, Action action)  
  {
    super(name, x, y);
    this.action = action;
  }
  
  /**
   * update overrides VisibleObject's update() method but calls it too. Once called, it returns
   * the action of the given ClickableObject object if the mouse is clicked on it. 
   */
  @Override
  public Action update()
  {
    super.update();
    boolean isOverObject = isOver(getProcessing().mouseX, getProcessing().mouseY);
    boolean mouseIsPressed = getProcessing().mousePressed;
    if(mouseWasPressed == true && mouseIsPressed != true) // complex logic loops determine the 
    {                                                     // state of the mouse to prevent 
      mouseWasPressed = false;                            // continually returning action
    }
    if(mouseWasPressed == false && isOverObject == true && mouseIsPressed == true)
    {
      mouseWasPressed = true;
      return action;
    }
    return null;    
  }

}
