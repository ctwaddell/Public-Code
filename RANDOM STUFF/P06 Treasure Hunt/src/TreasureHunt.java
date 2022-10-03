//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TreasureHunt - Treasure hunting game using Processing Library tools
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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class TreasureHunt extends PApplet
{
  private PImage backgroundImage;
  private ArrayList<InteractiveObject> gameObjects;
  
  /**
   * settings defines the window size for the TreasureHunt object
   */
  @Override
  public void settings() 
  { 
    size(800,600); 
  }
  
  /**
   * setup defines and initializes the start conditions for the TreasureHunt object
   */
  @Override
  public void setup()
  {
    // Initialization
    this.getSurface().setTitle("Treasure Hunt"); // Displays the title of the display window
    backgroundImage = loadImage("images" + File.separator + "background-scene.png");
    InteractiveObject.setProcessing(this);
    gameObjects = new ArrayList<InteractiveObject>();
    
    
    // EARLIER TEST CODE - DO NOT REMOVE
    /*
    VisibleObject phone = new VisibleObject("phone", 700, 490);
    phone.deactivate();
    Action koalaAction = new Action("This koala kinda fresh doe.");
    Action keyAction = new Action("Open sesame.", phone);
    ClickableObject koala = new ClickableObject("koala", 350, 65, koalaAction);
    gameObjects.add(koala);
    VisibleObject chest = new VisibleObject("chest", 365, 400);
    gameObjects.add(chest);
    DroppableObject key = new DroppableObject("key", 50, 50, chest, keyAction);
    gameObjects.add(key);
    */
    
    loadGameSettings("clues" + File.separator + "treasureHunt.clues");
  }
  
  /**
   * the draw method handles updating the window given various inputs or changes happening
   * throughout the program
   */
  @Override
  public void draw()
  { 
    image(backgroundImage, 0, 0);
    try 
    {
      for(int i = 0; i < gameObjects.size(); i++)
      {
        try
        {
          gameObjects.get(i).update().act(gameObjects);
        }
        catch(NullPointerException e)
        {
          
        }
        if(gameObjects.get(i).isActive() == false)
        {
          gameObjects.remove(i);
          i--;
        }
      }
    }
    catch(NullPointerException e)
    {
      
    }
  }

  /**
   * This method loads a background image, prints out some introductory text,
   * and then reads in a set of interactive objects descriptions from a text file
   * with the provided name. These represent the different clues for our 
   * treasure hunt adventure game. The image is stored in this.backgroundImage, 
   * and the activated interactive objects are added to the this.gameObjects list.
   * 
   * @param filename - relative path of file to load, relative to current working directory
   */
  private void loadGameSettings(String filename) {
    // start reading file contents
    Scanner fin = null;
    int lineNumber = 1; // report first line in file as lineNumber 1
    try {
      fin = new Scanner(new File(filename));

      // read and store background image
      String backgroundImageFilename = fin.nextLine().trim();
      backgroundImageFilename = "images" + File.separator + backgroundImageFilename + ".png";
      backgroundImage = loadImage(backgroundImageFilename);
      lineNumber++;

      // read and print out introductory text
      String introductoryText = fin.nextLine().trim();
      System.out.println(introductoryText);
      lineNumber++;

      // then read and create new objects, one line per interactive object
      while (fin.hasNextLine()) {
        String line = fin.nextLine().trim();
        if (line.length() < 1)
          continue;

        // fields are delimited by colons within a given line
        String[] parts = line.split(":");
        InteractiveObject newObject = null;

        // first letter in line determines the type of the interactive object to create
        if (Character.toUpperCase(line.charAt(0)) == 'C')
          newObject = loadNewClickableObject(parts);
        else if (Character.toUpperCase(line.charAt(0)) == 'D')
          newObject = loadNewDroppableObject(parts);

        // even deactivated object references are being added to the gameObjects arraylist,
        // so they can be found.
        // these deactivated object references will be removed, when draw() is first called
        gameObjects.add(newObject);
        if (Character.isLowerCase(line.charAt(0))) // lower case denotes non-active object
          newObject.deactivate();
        lineNumber++;
      }

      // catch and report warnings related to any problems experienced loading this file
    } catch (FileNotFoundException e) {
      System.out.println("WARNING: Unable to find or load file: " + filename);
    } catch (RuntimeException e) {
      System.out.println("WARNING: Problem loading file: " + filename + " line: " + lineNumber);
      e.printStackTrace();
    } finally {
      if (fin != null)
        fin.close();
    }
  }

  /**
   * Helper method to retrieve interactive objects' references from the 
   * gameObjects list, based on their names. If multiple objects have that
   * name, this method will return the first (lowest-index) reference found.
   * 
   * @param name is the name of the object that is being found
   * @return a reference to an interactive object with the specified name, 
   *         or null when none is found
   */
  private InteractiveObject findObjectByName(String name) {
    for (int i = 0; i < gameObjects.size(); i++)
      if (gameObjects.get(i).hasName(name)) {
        return gameObjects.get(i);
      }
    System.out.println("WARNING: Failed to find an interactive object with name: " + name);
    return null;
  }

  /**
   * This method creates and returns a new ClickableObject based on the properties
   * specified as strings within the provided parts array.
   * 
   * @param parts contains the following strings in this order: 
   *     - C: indicates that a ClickableObject is being created
   *     - name: the name of the newly created interactive object 
   *     - x: the starting x position (as an int) for this object
   *     - y: the starting y position (as an int) for this object
   *     - message: a string of text to display when this object is clicked 
   *     - name of the object to activate (optional): activates this object when clicked
   * @return the newly created object
   */
  private ClickableObject loadNewClickableObject(String[] parts) {
    // C: name: x: y: message: name of object to activate (optional)
    
    // parse parts
    String name = parts[1].trim();
    int x = Integer.parseInt(parts[2].trim());
    int y = Integer.parseInt(parts[3].trim());
    String message = parts[4].trim();
    InteractiveObject activate = null;
    if (parts.length > 5)
      activate = findObjectByName(parts[5].trim());
    // create new clickable object
    ClickableObject newObject = new ClickableObject(name, x, y, 
        new Action(message, activate));
    return newObject;
  }

  /**
   * This method creates and returns a new DroppableObject based on the properties
   * specified as strings within the provided parts array.
   * 
   * @param parts contains the following strings in this order: 
   *   - D: indicates that a DroppableObject is being created 
   *   - name: the name of the newly created droppable object
   *   - x: the starting x position (as an int) for this object
   *   - y: the starting y position (as an int) for this object 
   *   - message: a string of text to display when this object is dropped on target
   *   - name of the object to activate (optional): activates this object 
   *     when dropped on target
   *     
   * @return the newly created droppable object
   */
  private DroppableObject loadNewDroppableObject(String[] parts) {
    // D: name: x: y: target: message: name of object to activate (optional)
    
    // parse parts
    String name = parts[1].trim();
    int x = Integer.parseInt(parts[2].trim());
    int y = Integer.parseInt(parts[3].trim());
    InteractiveObject dropTarget = findObjectByName(parts[4].trim());
    if (!(dropTarget instanceof VisibleObject))
      dropTarget = null;
    String message = parts[5].trim();
    InteractiveObject activate = null;
    if (parts.length > 6)
      activate = findObjectByName(parts[6].trim());
    // create new droppable object
    DroppableObject newObject =
        new DroppableObject(name, x, y, (VisibleObject) dropTarget, new Action(message, activate));
    return newObject;
  }
  
  /**
   * main runs the whole program
   * @param args, String[] representing arguments
   */
  public static void main(String[] args) 
  {
    PApplet.main("TreasureHunt");
  }
}
