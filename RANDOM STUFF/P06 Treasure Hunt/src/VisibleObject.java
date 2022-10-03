//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    VisibleObject - Parent class for various derived classes, adds a layer of 
//                           visual representation for itself and child class's / objects
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

import processing.core.PImage;
import java.io.File;

public class VisibleObject extends InteractiveObject
{
  private PImage image; // the graphical representation of this object
  private int x;
  private int y;
  
  /**
   * This constructor creates a VisibleObject with three arguments (name, x, y)
   * Also utilizes constructor of InteractiveObject (parent class)
   * @param name, String representing the title of this object (using this name, the constructor
   *        also derives which image it is
   * @param x, int representing where the object is on the window (distance from left to right)
   * @param y, int representing where the object is on the window (distance from top to bottom)
   */
  public VisibleObject(String name, int x, int y) 
  {
    super(name);
    this.x = x;
    this.y = y;
    this.image = getProcessing().loadImage("images" + File.separator+ name + ".png"); //POTENTIALLY WRONG
  }
  
  /**
   * update overrides InteractiveObject's update() method. This version of update draws the
   * VisibleObject object on the window before returning null
   * @return Action, although this will technically always return null
   */
  @Override
  public Action update()
  {
    getProcessing().image(image, x, y);
    return null;
  }
  
  /**
   * move determines how much to move a given VisibleObject object given two import parameter
   * @param dx, int representing how far to move the object on the x-axis
   * @param dy, int representing how far to move the object on the y-axis
   */
  public void move(int dx, int dy)
  {
    x = x + dx;
    y = y + dy;
  } 

  
  /**
   * getWidth returns the width of the VisibleObject's image
   * @return float, representing the width of the image
   */
  public float getWidth()
  {
    return image.width;
  }
  
  /**
   * getHeight returns the height of the VisibleObject's image
   * @return float, representing the height of the image
   */
  public float getHeight()
  {
    return image.height;
  }
  
  /**
   * isOver determines if a set of coordinates falls over the VisibleObject object
   * @param x, int representing the x-coordinate to determine
   * @param y, int representing the y-coordinate to determine
   * @return boolean, true if the coordinates fall over the VisibleObject object,
   *         and false if the coordinates don't
   */
  public boolean isOver(int x, int y)
  {
    float width = image.width; //essentially these variables establish a range which to calculate
    float absoluteWidth = width + this.x;
    float height = image.height;
    float absoluteHeight = height + this.y;
    if(x >= this.x && x <= absoluteWidth && y >= this.y && y <= absoluteHeight)
    {
      return true; //if the coordinate falls within the range variables, it is over
    }
    return false;
  } 
  
  /**
   * isOver is an applied version of the earlier isOver, this one's purpose is to tell if
   * another image overlaps this VisibleObject's image
   * @param other, the other VisibleObject object to determine if overlaps
   * @return boolean, true if overlap occurs and false if no overlap
   */
  public boolean isOver(VisibleObject other)
  {
    if(other.isOver(x, y)) //runs isOver with VisibleObject other's coordinates as input parameters
    {
      return true;
    }
    return false;
  } 

}
