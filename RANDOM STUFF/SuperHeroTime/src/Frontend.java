import java.util.Scanner;

public class Frontend
{
  private Scanner scanner = new Scanner(System.in);
  private BackendDummy backend;
  private String searchRange = "n";
  private String toBreak = "";
  private String answer = "";
  private boolean loop = true;
  private CharacterDummy[] roster;
  private int rosterPower = 100;
  private CharacterDummy[] characterArray;
  private int lower;
  private int upper;
  private boolean firstTimeBaseMode = true;
  
  public Frontend(BackendDummy backend)
  {
    this.backend = backend;
    roster = new CharacterDummy[100]; //SUBJECT TO CHANGE, DISCUSS WITH TEAM!
  }
    
  public static void main(String[] args)
  {
    BackendDummy backend = new BackendDummy();
    Frontend runner = new Frontend(backend);
    runner.run();
  }
  
  public void run()
  {
    System.out.println("Welcome to the SAD Database. Type 'help' at any time to display commands.");
    baseMode();
  }
  
  public void baseMode()
  {
    String toDo = "B";
    while(loop == true)
    {
      if(!searchRange.equals("n"))
      {
        System.out.println("The current search range being displayed is " + searchRange);
        String[] bounds = searchRange.split("-");
        lower = Integer.parseInt(bounds[0]);
        upper = Integer.parseInt(bounds[1]);
        characterArray = backend.searchScore(lower, upper);
        System.out.println(arrayToString(backend.searchScore(lower, upper))); //can implement with getThree if need be?
        System.out.println("If you would like to add any of the above characters follow these commands:");
        displayCommands("adding");
        String userInput = scanner.nextLine();
        if(userInput.equalsIgnoreCase("X"))
        {
          System.out.println("Returning to Base Mode...");
          userInput = "?";
          searchRange = "n";
          //do nothing
        }
        if(userInput.equalsIgnoreCase("N"))
        {
          //use backend interface or getThree here
        }
        if(userInput.equalsIgnoreCase("P"))
        {
          //use backend interface or getThree thing
        }
        if(userInput.equalsIgnoreCase("help") || userInput.equalsIgnoreCase("ls"))
        {
          displayCommands("adding");
          continue;
        }
        if(userInput.equalsIgnoreCase("A"))
        {
          while(loop == true)
          {
            System.out.println("Adding character to roster. Enter index [0/1/2] to choose. Alternatively, press [X] to cancel.");
            userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("x"))
            {
              System.out.println("Done adding characters...\n");
              break;
            }
            try
            {
              int index = Integer.parseInt(userInput);
              if(index < 0 || index > 2)
              {
                System.out.println("You must enter a number [0/1/2] or quit by typing X.");
                continue;
              }
              addToRoster(characterArray[index]);
              System.out.println("You may keep adding until you indicate you are done by typing [X].");
            }
            catch(NumberFormatException e)
            {
              System.out.println("Input was not an integer. Please try again.");
            }
          }
          continue;
        }
      }
      //DISPLAY/OUTPUT SETTINGS
      /*if(currentSearchMode.equals("name"))
      {
        System.out.println("----------------------------------------\nCurrently displaying " + currentSearchMode + " mode options\n----------------------------------------");
      }
      if(currentSearchMode.equals("score"))
      {
        System.out.println("----------------------------------------\nCurrently displaying " + currentSearchMode + " mode options\n----------------------------------------");
      }
      if(currentSearchMode.equals("fight"))
      {
        System.out.println("----------------------------------------\nCurrently displaying " + currentSearchMode + " mode options\n----------------------------------------");
      } */ 
      
      //INPUT SETTINGS
      if(firstTimeBaseMode == false)
      {
        System.out.println("You are currently in Base Mode. Type 'help' to display commands");
      }
      toDo = scanner.nextLine();
      firstTimeBaseMode = false;
      if(toDo.equalsIgnoreCase("help") || toDo.equalsIgnoreCase("ls"))
      {
        displayCommands("base");
        continue;
      }
      if(toDo.equalsIgnoreCase("N"))
      {
        System.out.println("Now entering Name Mode...");
        nameMode();
        continue;
      }
      if(toDo.equalsIgnoreCase("S"))
      {
        System.out.println("Now entering Score Mode...");
        searchRange = scoreMode();
        continue;
      }
      /*if(toDo.equalsIgnoreCase("F"))
      {
        System.out.println("Now entering fight mode...");
        battleMode();
        continue;
      }*/
      if(toDo.equalsIgnoreCase("R"))
      {
        System.out.println(getRoster());
        continue;
      }
      if(toDo.equalsIgnoreCase("E"))
      {
        System.out.println("Now entering Roster Editing Mode");
        rosterMode();
        continue;
      }
      if(toDo.equalsIgnoreCase("X"))
      {
        System.out.println("Goodbye!");
        System.exit(0);
      }
        System.out.println("Sorry, we did not understand your input. Try again");
    }
  }
  
  public void nameMode()
  {
    while(loop == true)
    {
      System.out.println("\nYou are currently in name mode. Type 'help' for commands or a name to search for.");
      toBreak = scanner.nextLine();
      if(toBreak.equalsIgnoreCase("help") || toBreak.equalsIgnoreCase("ls"))
      {
        displayCommands("name");
        
        continue;
      }
      if(toBreak.equalsIgnoreCase("X"))
      {
        System.out.println("Returning to base mode...");
        return;
      }
      System.out.println("Searching the data base for: " + toBreak);
      CharacterDummy result = backend.searchName(toBreak);
      if(result.getName().equalsIgnoreCase(toBreak))
      {
        System.out.println(toBreak + " was found. " + toBreak + "'s attrributes are:\n" + characterFormatter(result));
        System.out.println("Would you like to add " + toBreak + " to your roster? [Y/N]");
        answer = scanner.nextLine();
        boolean hasAdded = false;
        if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes"))
        {
          addToRoster(result);
        }
        if(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no"))
        {
          System.out.println("Ok, search for another hero or exit with [X] once complete.");
        }  
        if(!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"))
        {
          System.out.println("Your input could not be recognized. Please search and try again.");
        }
      }
      else
      {
        System.out.println(toBreak + " was not found. Please try again.");
      }
    }
    return;
  }
  
  public String scoreMode()
  {
    String range = "";
    int lower = 0;
    int upper = 0;
    while(loop == true)
    {
      System.out.println("You are currently in score mode. Type a range of powers to search for.");
      range = searchQuery();   
      while(loop == true)
      {
        System.out.println("The range being searched is " + range + "\nIf you are happy with this range, press [X]. Otherwise, press [R] to refine the range.");
        toBreak = scanner.nextLine();
        if(toBreak.equalsIgnoreCase("r"))
        {
          break;
        }
        if(toBreak.equalsIgnoreCase("x"))
        {
          System.out.println("The range " + range + " has been confirmed. Returning to base mode.");
          return range;
        }
        else
        {
          System.out.println("The command was not recognized. Please try again.");
        }
      }
    }
    String bounds[] = range.split("-");
    lower = Integer.parseInt(bounds[0]);
    upper = Integer.parseInt(bounds[1]);
    System.out.println(arrayToString(backend.searchScore(upper, lower)));
    return "ERROR";
  }
  
  public void rosterMode()
  {
    while(loop == true)
    {
      System.out.println("You are currently in Roster Editing Mode. Type 'help' for commands");
      String rosterRead = scanner.nextLine();
      if(rosterRead.equalsIgnoreCase("help") || rosterRead.equalsIgnoreCase("ls"))
      {
        displayCommands("roster");
      }
      if(rosterRead.equalsIgnoreCase("d"))
      {
        System.out.println(getRoster());
      }
      if(rosterRead.equalsIgnoreCase("r"))
      {
        while(loop == true)
        {
          System.out.println("Removing from roster. Type [index] of character to remove");
          String toRemove = scanner.nextLine();
          int toRemoveInt;
          try
          {
            toRemoveInt = Integer.parseInt(toRemove);
            if(toRemoveInt < 0 || toRemoveInt > roster.length)
            {
              
              System.out.println("Input was less than zero or greater than the roster's size. Please try again.");
              continue;
            }
          }
          catch(NumberFormatException e)
          {
            System.out.println("Input was not an integer. Please try again.");
            continue;
          }
          removeFromRoster(toRemoveInt);
          break;
        }
      }
      if(rosterRead.equalsIgnoreCase("x"))
      {
        System.out.println("Returning to Base Mode...");
        return;
      }
    }
  }
  
  private String searchQuery()
  {
    boolean hasLower = false;
    boolean hasUpper = false;
    String immediateAdd = "";
    int checker = 0;
    int checker2 = 0;
    String r = "";
    while(hasLower == false)
    {
      System.out.println("Enter a lower bound:");
      immediateAdd = scanner.nextLine();
      try
      {
        checker = Integer.parseInt(immediateAdd);
        if(checker < 0)
        {
          System.out.println("Input was less than 0. Please try again.");
          continue;
        }
        r += immediateAdd;
        hasLower = true;
      }
      catch(NumberFormatException e)
      {
        System.out.println("Input was not an integer. Please try again.");
      }
    }
    r += "-";
    while(hasUpper == false)
    {
      System.out.println("Enter an upper bound:");
      immediateAdd = scanner.nextLine();
      try
      {
        checker2 = Integer.parseInt(immediateAdd);
        if(checker2 < 0 || checker2 < checker)
        {
          System.out.println("Input was less than 0 or less than lower bound. Please try again.");
          continue;
        }
        r += immediateAdd;
        hasUpper = true;
      }
      catch(NumberFormatException e)
      {
        System.out.println("Input was not an integer. Please try again.");
      }
    }
    return r;
  }
  
  private String getRoster()
  {
    if(roster[0] == null)
    {
      return "The roster is currently empty. (Power available: " + rosterPower + "/100)";
    }
    String r = "Power available: " + rosterPower + "/100\n[";
    for(int i = 0; i < roster.length; i++)
    {
      if(roster[i] != null)
      {
        if(i == 0)
        {
          r += characterFormatter(roster[0]);
          continue;
        }
        else
        {
          
        }
        r += ";\n " + characterFormatter(roster[i]);
      }
    }
    r += "]";
    return r;
  }
  
  public boolean addToRoster(CharacterDummy c)
  {
    boolean r = false;
    if(rosterPower - c.getPower() < 0)
    {
      System.out.println("The character cannot be added to the roster, as the maximum roster power would be overwhelmed.");
      return r;
    }
    if(roster[0] == null)
    {
      roster[0] = c;
      System.out.println(c.getName() + " has been added to your roster.");
      rosterPower = rosterPower - c.getPower();
      return true;
    }
    for(int i = 0; i < roster.length; i++)
    {

      if(roster[i] == null)
      {
        roster[i] = c;
        rosterPower = rosterPower - c.getPower();
        System.out.println(c.getName() + " has been added to your roster.");
        return true;
      }  
      if(roster[i].getName().equalsIgnoreCase(c.getName())) //this part is if we allow duplicates or not
      {
        System.out.println("Duplicate heros cannot be added.");
        return false;
      }
    }
    if(r == false)
    {
      System.out.println(c.getName() + " could not be added. The roster is likely full.");
      return false;
    }
    return false;
  }
  
  public boolean removeFromRoster(int a)
  {
    System.out.println("Removing from index " + a);
    CharacterDummy[] duplicateArray = roster;
    rosterPower = rosterPower + roster[a].getPower();
    System.out.println(roster[a].getName() + " has been removed.");
    roster[a] = null;
    for(int i = a; i < duplicateArray.length; i++)
    {
      if(duplicateArray[i + 1] == null)
      {
        duplicateArray[i] = null; //handles the end of the list just fine...
        break;
      }
      duplicateArray[i] = duplicateArray[i+1]; //some error here, remove element 3 and it duplicates something
    }
    roster = duplicateArray;

    return true;
  }
  
  private String arrayToString(CharacterDummy[] a)
  {
    String r = "[";
    for(int i = 0; i < a.length; i++)
    {
      if(i == 0)
      {
        r += characterFormatter(a[i]);
        continue;
      }
      else
      {
        r += ";\n " + characterFormatter(a[i]);
      }
    }
    r += "]";
    return r;
  }
  
  private String characterFormatter(CharacterDummy c)
  {
    String r = "Name: ";
    r += c.getName();
    r += ", Power Level: ";
    r += c.getPower();
    r += ", Superpowers: ";
    r += c.getSuperpowers();
//  r += ", Allignment: ";
//  r += c.getAllignment();
    return r;
  }

  private void displayCommands(String a)
  {
    if(a.equalsIgnoreCase("base"))    
    {
      System.out.println("[N] enters Name Mode");
      System.out.println("[S] enters Score Mode");
      System.out.println("[R] displays the current roster");
      System.out.println("[E] enters Roster Edit Mode");
      System.out.println("[X] at any time exits the mode/program");
      return;
    }
    if(a.equalsIgnoreCase("name"))
    {
      System.out.println("Type a name into the terminal to search for that character");
      System.out.println("[Y/N] either adds or doesn't add a character once it's found");
      System.out.println("[X] at any time exits the mode");
      return;
    }
    if(a.equalsIgnoreCase("score"))
    {
      System.out.println("Type a lower range followed by a higher range to search for that range");
      System.out.println("[R] allows to refine the power range");
      System.out.println("[X] at any time exits the search / confirms the range");
    }
    if(a.equalsIgnoreCase("adding"))
    {
      System.out.println("[A] indicates you would like add a hero");
      System.out.println("[0/1/2] after pressing [A] indicates which hero to add");
      System.out.println("[N] displays the next three characters within the power range");
      System.out.println("[P] displays the previous three characters within the power range");
      System.out.println("[X] cancels the adding process and returns to base mode");
    }
    if(a.equalsIgnoreCase("roster"))
    {
      System.out.println("In Roster Editing Mode, you may remove characters. If you wish to add, please add through either Name or Score Mode.");
      System.out.println("[D] displays the full roster");
      System.out.println("[R] indicates you would like to remove a character");
      System.out.println("[index] after [R] removes the character from the given index of your roster");
      System.out.println("[X] at any time exits Roster Editing Mode");
    }
  }
}
