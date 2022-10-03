package TicTacToe;
/*

Casey Waddell
1/13/19
v1.2.1
This program is a game where you are in a maze and you have to escape, fight, and explore
*/
import javax.swing.*;

public class SteampunkMaze
   {
      //other vars
      static int choice = -1;
      static boolean wincondition = false, combat = false, legendsword = false;
      static int key = -1;
      static String keyMessage = "";
      static final String title = "Professor Elemental's Laboratory v1.2.1";
      //combat vars
      //player vars
      static int Health = 15, playerDamage = 0;
      static double ATTACK = 5, CRIT = 1.1;
      static boolean playerAlive = true;
      //enemy vars
      static boolean enemyAlive = true, enemy1Alive = true, enemy2Alive = true, enemy3Alive = true;
      static int enemyHealth = 99, enemyDamage = 0;
      static double enemyATK = 6, enemyCrit = 2;
      static String enemyName = "default";
      
   //player attack code       
   public static int playerAttack()
      {
      //base damage by the player
      int playerAttack = (int)(Math.random()*ATTACK+1);
      int crit = 0;
      //rolls how many crit multipliers you will get
      for(int i=0; i<3; i++)
         {
         crit += (int)(Math.random()*CRIT);
         }
      //multiplies base damage by crit to get total damage
      playerDamage = (playerAttack)*(crit + 1);
      System.out.println("You attack, dealing " + playerAttack + " times a critical multiplier of " + (crit + 1) + " for a total of " + playerDamage);
      return playerDamage; 
      }
   
   //enemy attack code   
   public static int enemyAttack()
      {
      //base damage by the enemy
      int enemyAttack = (int)(Math.random()*enemyATK);
      int crit = 0;
      //rolles how many crit multipliers the enemy will get
      for(int i=0; i<3; i++)
         {
         crit += (int)(Math.random()*enemyCrit);
         }
      //multiplies base damage by crit to get total damage
      enemyDamage = (enemyAttack)*(crit + 1);
      System.out.println(enemyName + " attacks, dealing " + enemyAttack + " times a critical multiplier of " + (crit + 1) + " for a total of " + enemyDamage);
      return enemyDamage;      
      }
      
   //combat code
   public static boolean combat()
      {
      int turn = 1;
      System.out.println("\nCombat begins!");
      //cyckles through turns repeating combat process until one entity dies
      while(enemyAlive == true && playerAlive == true)
         {
         JOptionPane.showMessageDialog(null, "Turn " + turn);
         System.out.println("----- Turn " + turn + "-----\n");
         playerDamage = playerAttack();
         enemyHealth = (enemyHealth) - (playerDamage);
         enemyDamage = enemyAttack();
         Health = (Health) - (enemyDamage);
         System.out.println("\nYour HP: " + Health + "\nEnemy's HP: " + enemyHealth);
         turn++;
         if(Health <= 0)
            {
            playerAlive = false;
            System.out.println("You have died!");
            System.exit(0);
            }
         if(enemyHealth <= 0)
            {
            enemyAlive = false;
            System.out.println(enemyName + " is dead.");
            }
         try
            {
            Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
            Thread.currentThread().interrupt();
            }    
         }
         System.out.println("Combat concludes.");
      return combat; 
      }      
      
   //room template -- ALL COMMENTS PERTAINING TO ROOM FUNCTIONS WILL BE FOUND HERE. THE ROOMS FOLLOW THIS TEMPLATE
   public static int room()
      {
      //possible choices/directions
      String[] roomOption = {"", ""};
      //define image
      ImageIcon roomImage = new ImageIcon("roomImage.jpg");
      //option box
      int choice = JOptionPane.showOptionDialog(null, "", title, 1, 1, roomImage, roomOption, roomOption[0]);
      //indicates choice and sends it to the run loop
      choice = (choice + 0);
      return choice;
      }      
      
   //room one code
   public static int roomOne()  
      {
      choice = -1;
      String[] weapon = {"Steam-Sword", "Steam-Hammer", "Steam-Shield"};
      String[] roomOneOption = {"Go East", "Go South"};
      ImageIcon weaponImage = new ImageIcon("weaponImage.jpg");
      ImageIcon roomOneImage = new ImageIcon("roomOneImage.jpg");
      int weaponchoice = JOptionPane.showOptionDialog(null, "A speaker crackles to life, waking you up.\n'Cheerio!\nMy name is Professor Elemental. I noticed you fell into my laboratory! As a true steampunk gentleman, I will help you escape.\nFirst, pick up a weapon I have sent to you.'\n(A steam-sword prioritizes consistent damage\nA steam-hammer has a higher chance to deal a critical strike at cost of lower base damage\nA steam-shield is a defensive option, increasing health)", title, 1, 1, weaponImage, weapon, weapon[0]);
      //alters stats based on choice
      if(weaponchoice == 0)
         {
         ATTACK = 5; CRIT = 1.1;
         }
      if(weaponchoice == 1)
         {
         ATTACK = 4; CRIT = 1.25;
         }
      if(weaponchoice == 2)
         {
         ATTACK = 3; CRIT = 1.05; Health = 20;
         }  
      choice = JOptionPane.showOptionDialog(null, "'Okay! With your item, I shall begin to guide you out of my lab... the only problem is, I don't have a map!\n Either direction should be fine, but I do warn you, there are some *ahem* test subjects around who might not take kindly to your presence.\nGive it a lick and a promise, blimey!'", title, 1, 1, roomOneImage, roomOneOption, roomOneOption[0]);
      return choice;
      }
      
   //room two code 
   public static int roomTwo()
      {
      choice = -1;
      String[] roomTwoOption = {"Go East"};
      ImageIcon roomTwoImage = new ImageIcon("roomTwoImage.jpg");
      choice = JOptionPane.showOptionDialog(null, "'Oh no! I accidentally shut the doors to the main room! I tend to repeat myself, so don't question me saying the same dialogue for each room :)'", title, 1, 1, roomTwoImage, roomTwoOption, roomTwoOption[0]);
      choice = (choice + 20);
      return choice;
      }  
   //room three code
   public static int roomThree()
      {
      choice = -1;
      //adds key to inventory, allowing user to access secret room later
      if(key == -1)
         {
         keyMessage = " You find a functioning cog on the floor.\nYou pick it up and put it in your pocket.";
         key = 1; 
         }
      String[] roomThreeOption = {"Go South", "Go West"};
      ImageIcon roomThreeImage = new ImageIcon("roomThreeImage.jpg");
      int choice = JOptionPane.showOptionDialog(null, "You enter a dark room full of empty beakers and broken gears. There is an eerie silence over the radio." + keyMessage, title, 1, 1, roomThreeImage, roomThreeOption, roomThreeOption[0]);
      keyMessage = "";
      choice = (choice + 30);
      return choice;
      }
   //room four code
   public static boolean roomFour()
      {
      JOptionPane.showMessageDialog(null, "You emerge out of Professor Elemental's lab alive! Congratulations.");
      wincondition = true;
      System.exit(0);
      return wincondition;
      }        
   //room five code
   public static int roomFive()
      {
      choice = -1;
      String[] roomFiveOption = {"Go South"};
      ImageIcon roomFiveImage = new ImageIcon("roomFiveImage.jpg");
      choice = JOptionPane.showOptionDialog(null, "'Oh no! I accidentally shut the doors to the main room! I tend to repeat myself, so don't question me saying the same dialogue for each room :)'", title, 1, 1, roomFiveImage, roomFiveOption, roomFiveOption[0]);
      choice = (choice + 50);
      return choice;
      }
   //room six code
   public static int roomSix()
      {
         //sets the combat parameters
         if(enemy1Alive == true)
         {
         enemyAlive = true;
         enemyHealth = 10;
         enemyName = "Steam-Slime";
         enemyATK = 3;
         enemyCrit = 1.05;
         JOptionPane.showMessageDialog(null, "You enter a room with shattered vats. There appears to be a watery substance on the floor, but upon walking in, it forms into a steam-slime!");
         combat();
         }
      enemy1Alive = false;
      String[] roomSixOption = {"Go East", "Go South"};
      ImageIcon roomSixImage = new ImageIcon("roomSixImage.png");
      int choice = JOptionPane.showOptionDialog(null, "The steam-slime is now a simple puddle of water on the floor. You notice an awkward groaning over the speaker, but press forth anyway.", title, 1, 1, roomSixImage, roomSixOption, roomSixOption[0]);
      choice = (choice + 60);
      return choice;
      }
      
   //room seven code
   public static int roomSeven()
      {
      choice = -1;
      String[] roomSevenOption = {"Go South", "Go West", "Go North"};
      ImageIcon roomSevenImage = new ImageIcon("roomSevenImage.jpg");
      int choice = JOptionPane.showOptionDialog(null, "'Cheerio!\nYou are currently in the lab's main junction. Don't get balled up now, I think going west was the right way... I think.'", title, 1, 1, roomSevenImage, roomSevenOption, roomSevenOption[0]);
      choice = (choice + 70);
      return choice;
      }
      
   //room eight code
   public static int roomEight()
      {
      //sets the combat parameters
      if(enemy3Alive == true)
         {
         enemyAlive = true;
         enemyHealth = 25;
         enemyATK = 4;
         enemyCrit = 1.3;
         enemyName = "God From the Machine";
         JOptionPane.showMessageDialog(null, "You enter the final chamber and see a prodigious metal behemoth before you. Was this the titan Professor Elemental warned you about?");
         combat();
         JOptionPane.showMessageDialog(null, "You finally smash the steam-powered titan and it's steamed it's last steam. Suddenly, a lone steampunker climbs out of the machine. He will get his revenge!");
         enemyAlive = true;
         enemyHealth = 1;
         enemyName = "Professor Elemental";
         enemyATK = 3;
         enemyCrit = 3;
         combat();
         }
      enemy3Alive = false;
      String[] roomEightOption = {"Go North", "Go South"};
      ImageIcon roomEightImage = new ImageIcon("roomEightImage.jpg");
      int choice = JOptionPane.showOptionDialog(null, "Professor Elemental croaks a final, mechanical laugh and dies.\nAhead of you lies the light of freedom, but behind you, untold riches of a dead-man's laboratory.", title, 1, 1, roomEightImage, roomEightOption, roomEightOption[0]);
      choice = (choice + 80);
      return choice;
      }  
       
   //room nine code
   public static int roomNine()
      {
      String[] roomNineOption = {"Go East", "Go North"};
      ImageIcon roomNineImage = new ImageIcon("roomNineImage.jpg");
      int choice = JOptionPane.showOptionDialog(null, "'Cheerio!\nI believe if you continue easy along this corridor you will aproach the exit! Hurry ahead!'", title, 1, 1, roomNineImage, roomNineOption, roomNineOption[0]);
      choice = (choice + 90);
      return choice;
      }
         
   //room ten code
   public static int roomTen()
      {
      String[] roomTenOption = {"Go North", "Go West"};
      ImageIcon roomTenImage = new ImageIcon("roomTenImage.jpg");
      int choice = JOptionPane.showOptionDialog(null, "'Cheerio!\nYes, just due north here is the exit! Go!'", title, 1, 1, roomTenImage, roomTenOption, roomTenOption[0]);
      choice = (choice + 100);
      return choice;
      }
         
   //room eleven code
   public static int roomEleven()
      {
      String[] roomElevenOption = {"Go South", "Go North"};
      ImageIcon roomElevenImage = new ImageIcon("roomElevenImage.png");
      int choice = JOptionPane.showOptionDialog(null, "You come across an uninteresting hallway. A sign on the wall reads, 'uninteresting hallway.'", title, 1, 1, roomElevenImage, roomElevenOption, roomElevenOption[0]);
      choice = (choice + 110);
      return choice;
      }
         
   //room twelve code
   public static int roomTwelve()
      {
      String[] roomTwelveOption = {"Go North", "Go South"};
      ImageIcon roomTwelveImage = new ImageIcon("roomTwelveImage.jpg");
      int choice = JOptionPane.showOptionDialog(null, "You notice a mechanical whir eminating from the room north of you. You must be getting close to some sort of functioning equipment!\nA way out, perhaps? Suddenly, a speaker plays the Professor's voice.\n'Cheerio!\nI warn you! Do NOT progress. You could die. One of my steam-titans is lose!'", title, 1, 1, roomTwelveImage, roomTwelveOption, roomTwelveOption[0]);
      choice = (choice + 120);
      return choice;
      }
         
   //room thirteen code
   public static int roomThirteen()
      {
      //boosts stats
      String legendarysword = "";
      legendsword = false;
      if(legendsword == false)
         {
         legendarysword = "You notice a steam-rifle upon the wall. Serving better than a sabre, shield, or hammer, you take the rifle.";
         }
      String[] roomThirteenOption = {"Go East"};
      ImageIcon roomThirteenImage = new ImageIcon("roomThirteenImage.jpg");
      int choice = JOptionPane.showOptionDialog(null, "You enter the secret closet. " + legendarysword, title, 1, 1, roomThirteenImage, roomThirteenOption, roomThirteenOption[0]);
      ATTACK = 15;
      CRIT = 2.22;
      legendarysword = "";
      choice = (choice + 130);
      return choice;
      }
         
   //room fourteen code
   public static int roomFourteen()
      {
      //checks if key is in user's inventory and opens door if so
      if(key == 1)
         {
         String[] roomFourteenOption = {"Go East", "Go West"};
         ImageIcon roomFourteenImage = new ImageIcon("roomFourteenImage.jpg");
         int choice = JOptionPane.showOptionDialog(null, "You notice the cog you picked up earlier fits perfectly into a depression in the wall.\nYou insert the gear and notice a hidden closet opens up. The cog doesn't seem very stable, so you must make haste before the door closes.", title, 1, 1, roomFourteenImage, roomFourteenOption, roomFourteenOption[0]);
         choice = (choice + 140);
         key = -1;
         return choice;
         }
      else 
         {
         String[] roomFourteenOption = {"Go East"};
         ImageIcon roomFourteenImage = new ImageIcon("roomFourteenImage.jpg");
         int choice = JOptionPane.showOptionDialog(null, "A dead end. Looks like you'll have to go the other direction", title, 1, 1, roomFourteenImage, roomFourteenOption, roomFourteenOption[0]);
         choice = (choice + 140);
         return choice; 
         }
      }
         
   //room fifteen code
   public static int roomFifteen()
      {
      String[] roomFifteenOption = {"Go East", "Go North", "Go West"};
      ImageIcon roomFifteenImage = new ImageIcon("roomFifteenImage.jpg");
      int choice = JOptionPane.showOptionDialog(null, "'Cheerio\nYou are approaching the lab's exit soon. I do encourage you to rush east and then north! *heeheehee*'", title, 1, 1, roomFifteenImage, roomFifteenOption, roomFifteenOption[0]);
      choice = (choice + 150);
      return choice;
      }
         
   //room sixteen code
   public static int roomSixteen()
      {
      //sets combat parameters
      if(enemy2Alive == true)
         {
         enemyAlive = true;
         enemyHealth = 1;
         enemyName = "Steam-Figure in Shawl";
         enemyATK = 0;
         enemyCrit = -1;
         JOptionPane.showMessageDialog(null, "You enter the room and see a masked figure kneeling on the ground. Suddenly, it moves.");
         combat();
         }
      enemy2Alive = false;
      String[] roomSixteenOption = {"Go North", "Go West"};
      ImageIcon roomSixteenImage = new ImageIcon("roomSixteenImage.png");
      int choice = JOptionPane.showOptionDialog(null, "The room is now empty. There is no transmission over the radio.", title, 1, 1, roomSixteenImage, roomSixteenOption, roomSixteenOption[0]);
      choice = (choice + 160);
      return choice;
      }   
     
   //main         
   public static void main(String[] swag)
      {
      //this whole main function works by looping through the choices over and over, and the output from each room is a certain room number.
      //When the program receives the choice from each room, the program loops through to see which room number matches the choice
      //once finding it, the program places you in any given room depending on your choice
      //this is how I emulated a grid setup. There's probably easier or more efficient ways to make a grid layout for a maze, but I used what I knew to make this.
      roomOne();
      while(wincondition!=true)
      {
      //room one choices
      if(choice == 0)
         {choice = roomTwo();}
      if(choice == 1)
         {roomFive();}
      //room two choices  
      if(choice == 20)
         {choice = roomThree();}
      //room three choices
      if(choice == 30)
         {choice = roomSeven();}
      if(choice == 31)
         {choice = roomTwo();}
      //room four choices 
      //room five choices
      if(choice == 50)
         {choice = roomNine();}              
      //room six choices
      if(choice == 60)
         {choice = roomSeven();}
      if(choice == 61)
         {choice = roomTen();}               
      //room seven choices
      if(choice == 70)
         {choice = roomEleven();}
      if(choice == 71)
         {choice = roomSix();}
      if(choice == 72)
         {choice = roomThree();}
      //room eight choices
      if(choice == 80)
         {wincondition = roomFour();}
      if(choice == 81)
         {choice = roomTwelve();}       
      //room nine choices
      if(choice == 90)
         {choice = roomTen();}
      if(choice == 91)
         {choice = roomFive();}       
      //room ten choices
      if(choice == 100)
         {choice = roomSix();}
      if(choice == 101)
         {choice = roomNine();}       
      //room eleven choices
      if(choice == 110)
         {choice = roomFifteen();}
      if(choice == 111)
         {choice = roomSeven();}       
      //room twelve choices
      if(choice == 120)
         {choice = roomEight();}
      if(choice == 121)
         {choice = roomSixteen();}       
      //room thirteen choices
      if(choice == 130)
         {choice = roomFourteen();}     
      //room fourteen choices
      if(choice == 140)
         {choice = roomFifteen();}
      if(choice == 141)
         {choice = roomThirteen();}       
      //room fifteen choices
      if(choice == 150)
         {choice = roomSixteen();}
      if(choice == 151)
         {choice = roomEleven();}
      if(choice == 152)
         {choice = roomFourteen();}                
      //room sixteen choices
      if(choice == 160)
         {choice = roomTwelve();}
      if(choice == 161)
         {choice = roomFifteen();}                                                  
      }
      
      }
   }