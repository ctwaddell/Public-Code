//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    ClickableObject - Class for objects that can be dragged around the window,
//                             also the parent class for DroppableObject
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

public class DraggableObject extends VisibleObject
{
  private boolean mouseWasPressed; // similar to use in ClickableObject
  private boolean isDragging; // true when this object is being dragged by the user
  private int oldMouseX; // horizontal position of mouse during last update
  private int oldMouseY; // vertical position of mouse during last update

  /**
   * This constructor creates a DraggableObject object with three arguments (name, x, y),
   * also utilizes the constructor of VisibleObject (parent class)
   * @param name, String representing the title of this ClickableObject object
   * @param x, int representing where the object is on the window (distance from left to right)
   * @param y, int representing where the object is on the window (distance from top to bottom)
   */
  public DraggableObject(String name, int x, int y)
  {
    super(name, x, y);
    oldMouseX = x;
    oldMouseY = y;
  } 
  
  /**
   * update overrides VisibleObject's update() method. First, it calls the parent's update() method,
   * then calculates the drag of the mouse and translates that into the window. When isDragging
   * changes, drop() will be called and actions returned from drop() will then be returned by this
   */
  @Override
  public Action update()
  {
    super.update();
    boolean isDragChange = isDragging;
    boolean mouseIsPressed = getProcessing().mousePressed;
    boolean isOverObject = isOver(getProcessing().mouseX, getProcessing().mouseY);
    int newX = getProcessing().mouseX - ((int)getWidth()/2);
    int newY = getProcessing().mouseY - ((int)getHeight()/2);
    
    // if the three conditions are met, the object is therefore dragged,
    // and the mouseWasPressed field updates
    if(mouseWasPressed == false && mouseIsPressed == true && isOverObject == true)
    { 
      isDragging = true;
      mouseWasPressed = true;
    }
    // if the two conditions are met, we can deduce the object should no longer be dragged,
    // and mouseWasPressed field updates
    if(mouseWasPressed == true && mouseIsPressed == false)
    {
      isDragging = false;
      mouseWasPressed = false;
    }
    
    if(isDragging == true)
    {
      super.move((newX - oldMouseX), (newY - oldMouseY));
      oldMouseX = newX;
      oldMouseY = newY;
    }
    
    else
    {
      isDragging = false;
    }  
    
    if(isDragChange != isDragging)
    {
      isDragChange = isDragging;
      return drop();
    }
    return null;
  } 
  
  /**
   * drop() returns null in it's current state, but derived class DroppableObject's drop()
   * method returns something more substantial
   * @return
   */
  protected Action drop()
  { 
    return null;
  } 

}
