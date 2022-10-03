//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Ballot - a class specifying Ballot objects for P05
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

public class Ballot
{
  private static ArrayList<Party> parties = new ArrayList<Party>();
  private static int counter = 1;
  private Candidate[] votes;
  private int ID;
  
  /**
   * Ballot constructor 1 creates a Ballot object with room for 3 votes in a vote array as well
   * as a unique ID for the ballot
   */
  public Ballot()
  {
    votes = new Candidate[3];
    ID = counter;
    counter++;
  }
  
  /**
   * getVote returns the Candidate that the ballot has voted for for their said office choice
   * @param office, the search query for which Candidate the ballot voted for
   * @return Candidate that the ballot voted for specified by which office searched for, or null
   * if there is no vote for said office
   */
  public Candidate getVote(String office)
  {
    for(int i = 0; i < votes.length; i++)
    {
      try
      {
        if(votes[i].getOffice().equals(office))
        {
          return votes[i];
        }
      }
      catch(NullPointerException e)
      {
        
      }
    }
    return null;
  }
  
  /**
   * getID is a private helper method which returns the ID of a Ballot object to make comparisons
   * between IDs easier
   * @return in representing the ID of the ballot
   */
  private int getID()
  {
    return ID;
  }
  
  
  /**
   * equals compares a Ballot to any given object, although it only returns true if the input Object
   * o is also a ballot, as well as the same ID
   * @param Object o, the object one wishes to check equality of
   * @return boolean. True if Object o has the same ID and false if Object o isn't a ballot or 
   * doesn't have the same ID
   */
  @Override
  public boolean equals(Object o) 
  {
    Ballot comparator = new Ballot();
    if(!o.getClass().equals(comparator.getClass()))
    {
      return false;
    }
    if(ID == ((Ballot) o).getID())
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /**
   * vote takes the input Candidate object, checks its office, and adds it respectively into the
   * votes array
   * @param c, Candidate object that one wishes to vote for
   */
  public void vote(Candidate c)
  {
    if(c.getOffice().equals("President"))
    {
      votes[0] = c;
    }
    if(c.getOffice().equals("Vice President"))
    {
      votes[1] = c;
    }
    if(c.getOffice().equals("Secretary"))
    {
      votes[2] = c;
    }
  }
  
  /**
   * addParty adds the Party object to the Ballot object's parties ArrayList<Party>
   * @param p, Party object to add to said ArrayList<Party>
   */
  public static void addParty(Party p)
  {
    int uniqueParty = 0;
    for(int i = 0; i < parties.size(); i++)
    {
      if(parties.get(i).equals(p))
      {
        uniqueParty++;
      }
    }
    if(uniqueParty == 0)
    {
      parties.add(p);
    }
  }
  
  /**
   * getCandidates takes an office search query and returns all the Candidate objects with the
   * same office throughout all parties
   * @param office, office to search through
   * @return ArrayList<Candidate> a list of all Candidates for the given search office query
   */
  public static ArrayList<Candidate> getCandidates(String office)
  {
    ArrayList<Candidate> returnList = new ArrayList<Candidate>();
    for(int i = 0; i < parties.size(); i++)
    {
      try 
      {
        if(parties.get(i).getCandidate(office).getOffice().equals(office))
        {
          returnList.add(parties.get(i).getCandidate(office));
        }
      }
      catch(NoSuchElementException e)
      {
        
      }
    }
    return returnList;
  }
}
