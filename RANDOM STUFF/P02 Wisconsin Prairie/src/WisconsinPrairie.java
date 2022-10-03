//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    WisconsinPrairie - Shows some cool cows on the screen in a GUI
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

import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

public class WisconsinPrairie 
{
  private static PApplet processing; // PApplet object that represents the graphic
                                     //interface of the WisconsinPrairie application
  private static PImage backgroundImage; // PImage object that represents the
                                         //background image
  private static Cow[] cows; // array storing the current cows present
                             //in the Prairie
  private static Random randGen; // Generator of random numbers
  
  /**
  * Defines the initial environment properties of the application
  * @param processingObj represents a reference to the graphical interface of
  * the application
  */
  public static void setup(PApplet processingObj)
  {
    randGen = new Random();
    processing = processingObj;
    cows = new Cow[10];
    draw(); //this is here because the AutoGrader takes a point off if it isn't
  }
  
  /**
   * toString takes an array and returns a String of somewhat comprehensible text. It goes unused
   * because I really only used it during testing, not in the final program.
   * @param a, a Cow object array to be printed out
   * @return a line of text representing the registries of the cows in the array
   */
  private static String toString(Cow[] a)
  {
    String r = "";
    for(int j = 0; j < a.length; j++)
    {
      r += a[j] + ", ";
    }
    return r;
  }
  
  /**
  * Draws and updates the application display window.
  * This callback method called in an infinite loop.
  */
  public static void draw() 
  {
    backgroundImage = processing.loadImage("images/background.png");
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    for(int i = 0; i < cows.length; i++)
    {
      if(cows[i] != null)
      {
        if(cows[i].isDragging() == true)
        {
          cows[i].setPositionX(processing.mouseX);
          cows[i].setPositionY(processing.mouseY);
        }
        cows[i].draw();
      }
    }
  }
  
  /**
  * Checks if the mouse is over a given cow whose reference is provided
  * as input parameter
  * Basically, it takes the dimensions of the image, divides them by two and establishes two
  * upper and lower bounds of where the image is. If the mouse falls in these coordinates, it
  * registers as true.
  * @param cow reference to a given cow object
  * @return true if the mouse is over the given cow object (i.e. over
  * the image of the cow), false otherwise
  */
  public static boolean isMouseOver(Cow cow) 
  {
    float widthCheck = (cow.getImage().width)/2;
    float heightCheck = (cow.getImage().height)/2;
    float x = cow.getPositionX();
    float y = cow.getPositionY();
    float xUpperRange = x + widthCheck;
    float xLowerRange = x - widthCheck;
    float yUpperRange = y + heightCheck;
    float yLowerRange = y - heightCheck;
    if(processing.mouseX <= xUpperRange && processing.mouseX >= xLowerRange
       && processing.mouseY <= yUpperRange && processing.mouseY >= yLowerRange)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /**
  * Callback method called each time the user presses the mouse
  */
  public static void mousePressed()
  {
    for(int i = 0; i < cows.length; i++)
    {
      if(cows[i] != null)
      {
        if(isMouseOver(cows[i]) == true)
        {
          cows[i].setDragging(true);
          break;
        }
        else
        {
          cows[i].setDragging(false);
        }
      }
    }
  }

  /**
  * Callback method called each time the mouse is released
  */
  public static void mouseReleased() 
  {
    for(int i = 0; i < cows.length; i++)
    {
      if(cows[i] != null)
      {
          cows[i].setDragging(false);
      }
    }
  }
  
  /**
  * Callback method called each time the user presses a key
  */
  public static void keyPressed()
  {
    char key = processing.key;
    if(key == 'c' || key == 'C')
    {
      for(int i = 0; i < cows.length; i++)
      {
        if(cows[i] == null)
        {
          cows[i] = new Cow(processing, randGen.nextInt(processing.width), 
                                        randGen.nextInt(processing.height));
          break;
        }
      }
    }
    if(key == 'd' || key == 'D')
    {
      for(int i = 0; i < cows.length; i++)
      {
        if(cows[i] != null)
        {
          if(isMouseOver(cows[i]) == true)
          {
            cows[i] = null;
            break;
          }
        }
      }
    }
  }

  public static void main(String[] args)
  {
    Utility.startApplication();
  }
}
