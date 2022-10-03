//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BallotBox - a class specifying BallotBox objects for P05
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

public class BallotBox
{
  private ArrayList<Ballot> ballots;
  
  /**
   * BallotBox constructor 1 initializes the BallotBox object with an empty array list to hold
   * Ballot objects
   */
  public BallotBox()
  {
    ballots = new ArrayList<Ballot>();
  }
  
  /**
   * getNumBallots counts the number of unique Ballot objects in the BallotBox
   * @return int representing the count of unique Ballots
   * @throws NullPointerException if the BallotBox is empty
   */
  public int getNumBallots() throws NullPointerException
  {
    int toAdd = 0;
    ArrayList<Ballot> discriminatorArray = new ArrayList<Ballot>(0);
    //establishes the discriminator array and checks to see if there are ballots in the box
    if(ballots.size() == 0)
    {
      throw new NullPointerException("Error: The ballot box is empty");
    }
    
    for(int i = 0; i < ballots.size(); i++) //main ballot loop
    {
      toAdd = 0;
      for(int j = 0; j < discriminatorArray.size(); j++) //discriminator array loop
      {
        if(ballots.get(i).equals(discriminatorArray.get(j))) 
        {
          toAdd++;
          break;
        }
      }
      if(toAdd == 0)
      {
        discriminatorArray.add(ballots.get(i));
      }
    }
    return discriminatorArray.size();
  }
  
  /**
   * records all votes of the ballots for the specified office and returns the winner with the
   * most votes
   * @param office, the office to determine the winner for
   * @return Candidate who won, or null if there's some sort of problem like a tie
   */
  public Candidate getWinner(String office)
  {
    try 
    {
      ArrayList<Candidate> presidentList = new ArrayList<Candidate>();
      ArrayList<Candidate> vicePresidentList = new ArrayList<Candidate>();
      ArrayList<Candidate> secretaryList = new ArrayList<Candidate>();
      int toAdd = 0;
      int relativeMax = -1;
      int relativeMaxIndex = -1;
      int potentialConflict = 0;
      //case 1 -- office = President
      if(office.equals("President"))
      {
        //this for loop generates the list of all presidential candidates
        for(int i = 0; i < ballots.size(); i++) //works
        {
          toAdd = 0;
          for(int j = 0; j < presidentList.size(); j++)
          {
            if(ballots.get(i).getVote("President").equals(presidentList.get(j)))
            {
              toAdd++;
              break;
            }     
          }
          if(toAdd == 0)
          {
            presidentList.add(ballots.get(i).getVote("President"));
          }
        }
        
        //create an array same size as president list corresponding to each presidential candidiate
        int[] voteCount = new int[presidentList.size()];
        for(int y = 0; y < voteCount.length; y++) //fill array with 0 vote numbers
        {
          voteCount[y] = 0;
        }
        
        for(int t = 0; t < ballots.size(); t++) //loops through ballots
        {
          for(int h = 0; h < presidentList.size(); h++) //compares ballots to candidates
          {
            if(ballots.get(t).getVote("President").equals(presidentList.get(h)))
            {
              voteCount[h]++;
            }
          }
        }
        
        relativeMax = 0;
        for(int p = 0; p < voteCount.length; p++)
        {
          if(relativeMax == voteCount[p])
          {
            potentialConflict++;
          }
          if(relativeMax > voteCount[p])
          {
            //normal case, to be expected
          }
          if(relativeMax < voteCount[p])
          {
            relativeMax = voteCount[p];
            relativeMaxIndex = p;
            potentialConflict = 0;
          }
        }
        if(potentialConflict == 0) //if theres no tie, return highest vote count index
        {
          return (presidentList.get(relativeMaxIndex));
        }
        if(potentialConflict > 0) //if theres a tie, return the earlier index
        {
          return (presidentList.get(relativeMaxIndex));
        }
      }
      
      //case 2 -- office = Vice President
      if(office.equals("Vice President"))
      {
        //this for loop generates the list of all presidential candidates
        for(int i = 0; i < ballots.size(); i++) //works
        {
          toAdd = 0;
          for(int j = 0; j < vicePresidentList.size(); j++)
          {
            if(ballots.get(i).getVote("Vice President").equals(vicePresidentList.get(j)))
            {
              toAdd++;
              break;
            }     
          }
          if(toAdd == 0)
          {
            vicePresidentList.add(ballots.get(i).getVote("Vice President"));
          }
        }
        
        //create an array same size as president list corresponding to each presidential candidiate
        int[] voteCount = new int[vicePresidentList.size()];
        for(int y = 0; y < voteCount.length; y++) //fill array with 0 vote numbers
        {
          voteCount[y] = 0;
        }
        
        for(int t = 0; t < ballots.size(); t++) //loops through ballots
        {
          for(int h = 0; h < vicePresidentList.size(); h++) //compares ballots to candidates
          {
            if(ballots.get(t).getVote("Vice President").equals(vicePresidentList.get(h)))
            {
              voteCount[h]++;
            }
          }
        }
        
        relativeMax = 0;
        for(int p = 0; p < voteCount.length; p++)
        {
          if(relativeMax == voteCount[p])
          {
            potentialConflict++;
          }
          if(relativeMax > voteCount[p])
          {
            //normal case, to be expected
          }
          if(relativeMax < voteCount[p])
          {
            relativeMax = voteCount[p];
            relativeMaxIndex = p;
            potentialConflict = 0;
          }
        }
        if(potentialConflict == 0) //if theres no tie, return highest vote count index
        {
          return (vicePresidentList.get(relativeMaxIndex));
        }
        if(potentialConflict > 0) //if theres a tie, return the earlier index
        {
          return (vicePresidentList.get(relativeMaxIndex));
        }
      }
      
      //case 3 -- office = Secretary
      if(office.equals("Secretary"))
      {
        //this for loop generates the list of all presidential candidates
        for(int i = 0; i < ballots.size(); i++) //works
        {
          toAdd = 0;
          for(int j = 0; j < secretaryList.size(); j++)
          {
            if(ballots.get(i).getVote("Secretary").equals(secretaryList.get(j)))
            {
              toAdd++;
              break;
            }     
          }
          if(toAdd == 0)
          {
            secretaryList.add(ballots.get(i).getVote("Secretary"));
          }
        }
        
        //create an array same size as president list corresponding to each presidential candidiate
        int[] voteCount = new int[secretaryList.size()];
        for(int y = 0; y < voteCount.length; y++) //fill array with 0 vote numbers
        {
          voteCount[y] = 0;
        }
        
        for(int t = 0; t < ballots.size(); t++) //loops through ballots
        {
          for(int h = 0; h < secretaryList.size(); h++) //compares ballots to candidates
          {
            if(ballots.get(t).getVote("Secretary").equals(secretaryList.get(h)))
            {
              voteCount[h]++;
            }
          }
        }
        
        relativeMax = 0;
        for(int p = 0; p < voteCount.length; p++)
        {
          if(relativeMax == voteCount[p])
          {
            potentialConflict++;
          }
          if(relativeMax > voteCount[p])
          {
            //normal case, to be expected
          }
          if(relativeMax < voteCount[p])
          {
            relativeMax = voteCount[p];
            relativeMaxIndex = p;
            potentialConflict = 0;
          }
        }
        if(potentialConflict == 0) //if theres no tie, return highest vote count index
        {
          return (secretaryList.get(relativeMaxIndex));
        }
        if(potentialConflict > 0) //if theres a tie, return the earlier index
        {
          return (secretaryList.get(relativeMaxIndex));
        }
        
      }
    }
    catch(Exception e)
    {
      
    }
    return null;
  }
  
  /**
   * submit adds a ballot to the BallotBox (if it isn't already there)
   * @param b, Ballot indicating which ballot to add
   */
  public void submit(Ballot b) //works
  {
    if(ballots.size() == 0)
    {
      ballots.add(b);
      return;
    }
    int toAdd = 0;
    for(int i = 0; i < ballots.size(); i++)
    {
      if(ballots.get(i).equals(b))
      {
        toAdd++;
        break;
      }
    }
    if(toAdd == 0)
    {
      ballots.add(b);
      return;
    }
  }
}
