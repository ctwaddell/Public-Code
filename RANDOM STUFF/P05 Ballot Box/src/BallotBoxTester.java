//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BallotBoxTester - a test method for all other 4 classes to test interaction and success
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

public class BallotBoxTester 
{
  /**
   * testCandidate runs through multiple scenarios that might cause errors for the Candidate class
   * to ensure the program runs as expected
   * @return boolean, true if it works correctly and false if there is an error.
   */
  public static boolean testCandidate()
  {
    //tests constructor
    Candidate optionOne = new Candidate("George Washington", "President");
    Candidate optionTwo = new Candidate("John Adams", "Vice President");
    try
    {
      Candidate optionThree = new Candidate("Imposter", "Dictator");
    }
    catch(IllegalArgumentException e)
    {
      //invalid office should throw this error, to be expected
    }
    
    //tests accessors
    if(!optionOne.getName().equals("George Washington"))
    {
      return false;
    }
    if(!optionTwo.getOffice().equals("Vice President"))
    {
      return false;
    }
    if(!optionOne.toString().equals("George Washington (President)"))
    {
      return false;
    }
    
    //tests equals method override
    if(optionOne.equals(optionTwo) == true)
    {
      return false;
    }
    if(optionOne.equals(optionOne) == false)
    {
      return false;
    }
    Candidate optionFour = new Candidate("George Washington", "Secretary");
    if(optionOne.equals(optionFour) == true)
    {
      return false;
    }
    
    return true;
  }
  
  /**
   * testParty runs through multiple different scenarios to test the effectiveness of the Party
   * class at handling different errors and whatnot
   * @return boolean, true if all tests pass and false if a problem occurs
   */
  public static boolean testParty()
  {
    //tests constructor
    Party optionOne = new Party("Federalists");
    Party optionTwo = new Party("Democratic Republicans");
    
    //tests mutators
    Candidate washington = new Candidate("George Washington", "President");
    Candidate adams = new Candidate("John Adams", "President");
    Candidate hamilton = new Candidate("Alexander Hamilton", "Secretary");
    optionOne.addCandidate(washington);
    optionOne.addCandidate(hamilton);
    try
    {
      optionOne.addCandidate(adams);
    }
    catch(IllegalArgumentException e)
    {
      //to be expected, a party cannot have more than one candidate for the same office
    }
    
    //tests accessors
    if(!optionOne.getCandidate("President").equals(washington))
    {
      return false;
    }
    if(!optionOne.getCandidate("Secretary").equals(hamilton))
    {
      return false;
    }
    try
    {
      optionOne.getCandidate("Vice President");
    }
    catch(NoSuchElementException e)
    {
      //to be expected, an empty office would throw a NSE exception
    }
    if(!optionOne.getName().equals("Federalists"))
    {
      return false;
    }
    if(optionOne.getSize() != 2)
    {
      return false;
    }
    Candidate madison = new Candidate("James Madison", "Vice President");
    optionOne.addCandidate(madison);
    if(optionOne.getSize() != 3)
    {
      return false;
    }
    if(optionTwo.getSize() != 0)
    {
      return false;
    }
    
    return true;
  }
  
  /**
   * testBallot runs through multiple different scenarios to test the effectiveness of the Party
   * class at handling different errors and whatnot
   * @return boolean, true if all tests pass and false if a problem occurs
   */
  public static boolean testBallot()
  {
    Ballot one = new Ballot();
    Ballot two = new Ballot();
    
    //tests equals override method
    if(one.equals(two))
    {
      return false;
    }
    if(!one.equals(one))
    {
      return false;
    }
    
    //tests mutators
    Candidate washington = new Candidate("George Washington", "President");
    one.vote(washington); //no errors, it works (verified with accessors later)
    Candidate adams = new Candidate("John Adams", "Vice President");
    one.vote(adams);
    Party federalists = new Party("Federalists");
    one.addParty(federalists); //no errors, it works (verified with accessors later)
    
    //tests accessors
    if(!one.getVote("President").equals(washington))
    {
      return false;
    }
    Candidate washingtonsClone = new Candidate("George Washington 2", "President");
    one.vote(washingtonsClone);
    if(!one.getVote("President").equals(washingtonsClone))
    {
      return false; //verifies that a vote can be changed
    }
    if(one.getVote("Secretary") != null)
    {
      return false;
    }
    Party democraticRepublicans = new Party("Democratic Republicans");
    Candidate jefferson = new Candidate("Thomas Jefferson", "President");
    democraticRepublicans.addCandidate(jefferson);
    federalists.addCandidate(washington);
    one.addParty(federalists);
    one.addParty(federalists);
    one.addParty(democraticRepublicans);
    ArrayList<Candidate> testCase = new ArrayList<Candidate>();
    testCase.add(washington);
    testCase.add(jefferson);
    if(!one.getCandidates("President").equals(testCase))
    {
      return false; //tests the getCandidates method as well as the case of adding duplicate
                    //parties using the addParty mutator
    }
        
    return true;
  }
  
  public static boolean testBallotBox() 
  {
    //tests constructor
    BallotBox theBox = new BallotBox();
    
    //tests getNumBallots()
    try 
    {
      theBox.getNumBallots();
    }
    catch(NullPointerException e)
    {
      //to be expected, an empty ballot box throws this error
    }
    Ballot one = new Ballot();
    Candidate washington = new Candidate("George Washington", "President");
    one.vote(washington);
    Ballot two = new Ballot();
    Ballot three = new Ballot();
    theBox.submit(one);
    theBox.submit(two);
    theBox.submit(three);
    if(theBox.getNumBallots() != 3)
    {
      return false;
    }
    
    //tests getWinner()
    BallotBox theBoxTwo = new BallotBox();
    Candidate adams = new Candidate("John Adams", "President");
    two.vote(washington);
    three.vote(adams);
    theBoxTwo.submit(one);
    theBoxTwo.submit(two);
    theBoxTwo.submit(three);
    //should be 2 votes washington, one adams
    if(!theBoxTwo.getWinner("President").equals(washington))
    {
      return false;
    }
    Ballot four = new Ballot();
    four.vote(adams);
    theBoxTwo.submit(four);
    //should be 2 votes washington, two adams, returning the earlier index (washington) as winner
    if(!theBoxTwo.getWinner("President").equals(washington))
    {
      return false;
    }
    BallotBox theBoxThree = new BallotBox();
    if(theBoxThree.getWinner("President") != null)
    {
      return false;
    }
    return true;
  }
  
  public static void main(String[] args)
  {
    System.out.println(testCandidate());
    System.out.println(testParty());
    System.out.println(testBallot());
    System.out.println(testBallotBox());
  }
}
