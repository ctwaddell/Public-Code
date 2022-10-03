//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Candidate - a class specifying Candidate objects for P05
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

public class Candidate
{
  protected static final String[] OFFICE = {"President", "Vice President", "Secretary"};
  private String name = "";
  private String office = "";
  
  /**
   * Candidate constructor 1 creates a new Candidate object
   * @param name, String representing the name of the candidate
   * @param office, String representing the office of the candidate
   * @throws IllegalArgumentException if the office specified doesn't equal one of the given three
   * specified in the final String[] OFFICE
   */
  public Candidate(String name, String office)
  {
    int exceptionThrower = 0;
    this.name = name;
    for(int i = 0; i < OFFICE.length; i++)
    {
      if(OFFICE[i].equals(office))
      {
        this.office = office;
        exceptionThrower++;
        break;
      }
    }
    if(exceptionThrower == 0)
    {
      throw new IllegalArgumentException("The given office is not valid");
    }
  }
  
  /**
   * getName is an accessor to get the name of a candidate as a String
   * @return name, String representing name of the candidate
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * getOffice is an accessor to get the office of a candidate as a String
   * @return office, String representing office of the candidate
   */
  public String getOffice()
  {
    return office;
  }
  
  /**
   * toString returns the data of the Candidate object in a readable format
   * @return String, representing the formatted data of Candidate object
   */
  public String toString()
  {
    return (name + " (" + office + ")");
  }
  
  /**
   * equals compare two candidates for maximum accuracy of Pog
   * @param o, Object to compare to
   */
  @Override
  public boolean equals(Object o)
  {
    int check = 0;
    if(name.equals(((Candidate)o).getName()))
    {
      check++;
    }
    if(office.equals(((Candidate)o).getOffice()))
    {
      check++;
    }
    if(check == 2)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}