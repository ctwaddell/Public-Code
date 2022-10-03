
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    COVIDTracker - COVID 19 Test data organizer and statistical analyzer class
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

public class COVIDTracker
{
  /**
   * addTest adds ID to the appropriate test array if there is room.
   * @param pos, array of positive results
   * @param neg, array of negative results
   * @param id, unique ID of patient/search query
   * @param isPos, true indicates positive, false indicates negative
   * @return true if test was successfully added, false if there is an error adding the test
   */
  public static boolean addTest(String[] pos, String[] neg, String id, boolean isPos)
  {
    if(isPos == true)
    {
      for(int i = 0; i < pos.length; i++)
      {
        if(pos[i] == null) // adds id to array at the earliest found null value in array
        {
          pos[i] = id;
          return true;
        }
      }
    }
    if(isPos == false)
    {
      for(int i = 0; i < pos.length; i++)
      {
        if(neg[i] == null) // adds id to array at the earliest found null value in array
        {
          neg[i] = id;
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * removeIndividual removes the ID specified from both arrays and adjusts the array to fit
   * @param pos, array of positive results
   * @param neg, array of negative results
   * @param id, String indicating ID to remove
   * @return true if ID was successfully removed, false if something goes wrong and doesn't work
   */
  public static boolean removeIndividual(String[] pos, String[] neg, String id)
  {
    boolean r = false;
    
    for(int i = 0; i < pos.length; i++) // this entire loop evaluates the positive array for id
    {
      try 
      {
        if(pos[i].equals(id))
        {
          for(int j = i; j < pos.length; j++) // this entire loop deals with compacting the array
          {
            r = true; // this exists cause if ID is found, the array will be changed indicating true
            /*
             * the below if statement is written so the algorithm knows that it has reached the end
             * of the array, avoiding an out of bounds error
             */
            if(j == (pos.length-1))
            {
              pos[j] = null;
            }
            /* 
             * the below try/catch is to avoid a similar problem as above when comparing values
             * to nulls. the if/else statement is to catch if there are two of the same ID in
             * a row, which used to cause one to get 'preserved' as the array compressed
             */
            try 
            {
              if(pos[j+1].equals(id) == false)
              {
                pos[j] = pos[j+1]; 
              }
              else 
              {
                pos[j] = null;
                pos[j+1] = null;
              }  
            }
            catch(Exception NullPointerException)
            {
              pos[j] = pos[j+1];
            }
          }
        }
      }
      catch(Exception NullPointerException)
      {
      /*
       * this try avoids the code failing when an array contains a null value in it
       */
      }
    }
    
    for(int i = 0; i < neg.length; i++) // this entire loop evaluates the negative array for id
    {
      // this try/catch avoids the code failing when an array contains a null value in it
      try 
      {
        if(neg[i].equals(id))
        {
          for(int j = i; j < neg.length; j++) // this entire loop deals with compacting the array
          {
            r = true; // this exists cause if ID is found, the array will be changed indicating true
          /*
           * the below if statement is written so the algorithm knows that it has reached the end of 
           * the array, avoiding an out of bounds error
           */
            if(j == (neg.length-1))
            {
              neg[j] = null;
            }
              /* 
               * the below try/catch is to avoid a similar problem as above when comparing values
               * to nulls. the if/else statement is to catch if there are two of the same ID in
               * a row, which used to cause one to get 'preserved' as the array compressed
               */
            try
            {
              if(neg[j+1].equals(id) == false)
              {
                neg[j] = neg[j+1]; 
              }
              else 
              {
                neg[j] = null;
                neg[j+1] = null;
              } 
            }
            catch(Exception NullPointerException)
            {
              neg[j] = neg[j+1];
            }
          }
        }
      }    
      catch(Exception NullPointerException)
      {

      }
    }
    return r;
  }

 /**
  * getPopStats gives user a String of the basic statistics of the dataset, like total tests,
  * proportion of healthy to sick, unique individuals, etc.
  * @param pos, array of positive results
  * @param neg, array of negative results
  * @return String representing the basic statistics of the dataset
  */
 public static String getPopStats(String[] pos, String[] neg)
 {
   // Block 0 - initialization
   String r = "";
   int posCounter = 0;
   int negCounter = 0;
   int totalCounter = 0;
   double proportionSick = -1;
   int uniqueAdder = 0;
   int uniqueCounter = 0;
   int uniqueCounterPos = 0;
   int uniqueCounterNeg = 0; //NOTE: this is unused, but it could be useful in the future to store!
   double uniqueProportionPositive = 0;
   
   // Block 1 - writes positive and negative test amounts
   for(int i = 0; i < pos.length; i++)
   {
     if(pos[i] != null) posCounter++;
   }
   for(int i = 0; i < neg.length; i++)
   {
     if(neg[i] != null) negCounter++;
   }
   r += "Total tests: " + (posCounter + negCounter) + "\n";
   
   // Block 2 - observes and writes number of unique IDs for both pos[] and neg[]
   String[] uniqueID = new String[(pos.length + neg.length)];
   for(int i = 0; i < pos.length; i++)
   {
     uniqueAdder = 1;
     for(int j = 0; i < uniqueID.length; j++)
     {
       if(j >= uniqueID.length) //avoids index out of bounds exception
       {
         break;
       }
       try // the try/catch makes sure the code still works if the array has null in it
       {
         /*
          * the if statement below basically says "add the new ID unless proven otherwise"
          * Essentially, it will always be added until a match in the array is found
          */
         if(pos[i].equals(uniqueID[j]) == true)
         {
           uniqueAdder = 0;
         }
       }
       catch(Exception NullPointerException)
       {
         uniqueAdder = 0;
       }
     }
     if(uniqueAdder > 0)
     {
       uniqueAdder = 0; // resets uniqueAdder for the next iteration
       // the below for loop finds earliest empty space in uniqueID array and adds the uniqueID
       for(int k = 0; k < uniqueID.length; k++)
       {
         if(uniqueID[k] == null)
         {
           uniqueID[k] = pos[i];
           uniqueCounter++;
           uniqueCounterPos++;
           break;
         }
       }
     }
   }
   // all the same code and comments as above, just with the neg array instead of pos array
   for(int i = 0; i < neg.length; i++)
   {
     uniqueAdder = 1;
     for(int j = 0; i < uniqueID.length; j++)
     {
       if(j >= uniqueID.length)
       {
         break;
       }
       try
       {
         if(neg[i].equals(uniqueID[j]) == true)
         {
           uniqueAdder = 0;
         }
       }
       catch(Exception NullPointerException)
       {
         uniqueAdder = 0;
       }
     }
     if(uniqueAdder > 0)
     {
       uniqueAdder = 0;
       for(int k = 0; k < uniqueID.length; k++)
       {
         if(uniqueID[k] == null)
         {
           uniqueID[k] = neg[i];
           uniqueCounter++;
           uniqueCounterNeg++;
           break;
         }
       }
     }
   }
   r += ("Total individuals tested: " + uniqueCounter + "\n");
   
   // Block 3 - writes proportion of positive tests
   totalCounter = posCounter + negCounter;
   if(totalCounter != 0)
   {
     proportionSick = (((double) posCounter/totalCounter)*100);
   }
   else
   {
     proportionSick = (double)0;
   }
   r += ("Percent positive tests: " + proportionSick + "%\n");
   
   // Block 5 - writes the proportion of unique individuals that tested positive
   if(uniqueCounter != 0)
   {
     uniqueProportionPositive = (((double) uniqueCounterPos/uniqueCounter)*100);
   }
   else
   {
     uniqueProportionPositive = (double)0;
   }
   r += ("Percent positive individuals: " + uniqueProportionPositive + "%\n");
   return r;
 }
 
 /**
  * getIndividualStats gives user a String of the basic statistics of an individual like test
  * amount and results
  * @param pos, array of positive results
  * @param neg, array of negative results
  * @param id, String of ID user is searching for
  * @return String representing the basic statistics of the individual
  */
 public static String getIndividualStats(String[] pos, String[] neg, String id)
 {
   String r = "";
   int positiveCounter = 0;
   int negativeCounter = 0;
   for(int i = 0; i < pos.length; i++)
   {
     // the try/catch prevents the code from failing when an array contains null
     try
     {
       if(pos[i].equals(id))
       {
         positiveCounter++;
       }
     }
     catch(Exception NullPointerException)
     {
       
     }
   }
   for(int i = 0; i < neg.length; i++)
   {
     // the try/catch prevents the code from failing when an array contains null
     try
     {
       if(neg[i].equals(id))
       {
         negativeCounter++;
       }
     }
     catch(Exception NullPointerException)
     {
       
     }
   }
   r += "Total tests: " + (positiveCounter + negativeCounter) + "\n";
   r += "Positive: " + positiveCounter + "\n";
   r += "Negative: " + negativeCounter;
   return r;
 }
}
