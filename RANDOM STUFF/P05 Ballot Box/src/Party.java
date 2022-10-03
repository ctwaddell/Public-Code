//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Party - a class specifying Party objects for P05
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
import java.util.NoSuchElementException;

public class Party
{
  private String name = "";
  private ArrayList<Candidate> candidates = new ArrayList<Candidate>();
  
  /**
   * Party constructor 1 creates a new Party object using input String as name
   * @param name, String representing the name of the party
   */
  public Party(String name)
  {
    this.name = name;
  }
  
  /**
   * getName returns the name of a party as a String
   * @return String representing name of the party
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * getSize returns an int representing the count of candidates in a party
   * @return int, representing number of elements in Party object's candidates ArrayList<Candidate>
   */
  public int getSize()
  {
    return candidates.size();
  }
  
  /**
   * getCandidate returns a Candidate object of a given office, specifically pertaining to a party
   * @param office, String representing the office the Candidate can be found in
   * @return Candidate, the Candidate object of said office
   * @throws NoSuchElementException if the Candidate cannot be found
   */
  public Candidate getCandidate(String office)
  {
    for(int i = 0; i < candidates.size(); i++)
    {
      if(candidates.get(i).getOffice().equals(office))
      {
        return candidates.get(i);
      }
    }
    throw new NoSuchElementException("The given office is not being filled by any Candidate");
  }
  
  /**
   * addCandidate adds a Candidate object to a Party object's candidates ArrayList<Candidate>
   * @param c, Candidate object to add to said array
   * @throws IllegalArgumentException if Candidate is running for the same office as another 
   * Candidate in the party
   */
  public void addCandidate(Candidate c)
  {
    for(int i = 0; i < candidates.size(); i++)
    {
      if(c.getOffice().equals(candidates.get(i).getOffice()))
      {
        throw new IllegalArgumentException("A party may not have more than one Candidate running "+
            "for the same office");
      }
    }
    candidates.add(c);
  }
}






