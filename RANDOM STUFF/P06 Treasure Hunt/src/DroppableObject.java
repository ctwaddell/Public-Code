//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    DroppableObject - Class for objects that can be dragged and dropped around the
//                             screen for unique interactions
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

public class DroppableObject extends DraggableObject
{
  private VisibleObject target;
  private Action action;
  /**
   * This constructor creates a DroppableObject object with five arguments:
   * (name, x, y, target, action) also utilizes the constructor of DraggableObject (parent class)
   * @param name, String representing the title of this ClickableObject object
   * @param x, int representing where the object is on the window (distance from left to right)
   * @param y, int representing where the object is on the window (distance from top to bottom)
   * @param target, VisibleObject representing what this DroppableObject must be dropped on top
   *        of to return its action
   * @param action, Action indicating what will happen / be returned when the DroppableObject
   *        is dropped over it's target VisibleObject
   */
  public DroppableObject(String name, int x, int y, VisibleObject target, Action action) 
  {
    super(name, x, y);
    this.target = target;
    this.action = action;
  }
  
  /**
   * drop is what is activated when the parent's isDragging field changes. When this happens,
   * drop checks if the DroppableObject is over it's target VisibleObject. If yes, this returns
   * the DroppableObject's Action field, which results in some interaction on the screen 
   * (more information in the Action class regarding how Actions work). If the target is 
   * dropped upon, this DroppableObject and it's target will be disabled before returning Action.
   * If the DroppableObject is not above it's target, null is returned.
   */
  @Override
  protected Action drop()
  {
    if(this.isOver(target) && target.isActive() == true)
    {
      this.deactivate();    
      target.deactivate();
      return action;
    }
    return null;
  }

}
