/*
Casey Waddell
11/13/2019
v2.0
Joker Personality Quiz Adventure Game
*/

import javax.swing.*;
public class PersonalityQuiz
{
   public static void main(String[] args)
   {
      //declare vars
      int input;
      //WakeUp
      String[] WakeUp = {"Good", "Bad"};
      ImageIcon WakeUpImage = new ImageIcon("WakeUp.jpeg");
      input = JOptionPane.showOptionDialog(null, "Your eyes open up and you see the ceiling. How does the ceiling make you feel?", "Joker Quiz BETA", 1, 1, WakeUpImage, WakeUp, WakeUp[0]);
      switch(input)
      {
         //case 0 is GOOD --> go get coffee
         case 0: 
                  String[] Coffee = {"Shamrock Shake", "Black"};
                  ImageIcon CoffeeImage = new ImageIcon("Coffee.jfif");
                  input = JOptionPane.showOptionDialog(null, "Feeling good, you decide to go get some coffee. As you arrive, you ponder to yourself what kind of coffee do you want this morning?", "Joker Quiz BETA", 1, 1, CoffeeImage, Coffee, Coffee[0]);
                  switch(input)
                  {
                  //case 0 is SHAMROCK SHAKE --> what do you do while drinking coffee?
                  case 0:
                              String[] Activity = {"Draw", "Tell a joke"};
                              ImageIcon ActivityImage = new ImageIcon("ActivityImage.jpg");
                              input = JOptionPane.showOptionDialog(null, "After getting your shake you sit down. What are you going to do while you drink your coffee?", "Joker Quiz BETA", 1, 1, ActivityImage, Activity, Activity[0]);  
                              switch(input)
                              {
                                 //case 0 is DRAW --> minecraft or comics
                                 case 0:
                                             String[] Art = {"Minecraft Skin", "Comics"};
                                             ImageIcon ArtImage = new ImageIcon("ArtImage.png");
                                             input = JOptionPane.showOptionDialog(null, "Feeling in a creative mood, you decide to begin drawing in your sketchbook. What do you draw?", "Joker Quiz BETA", 1, 1, ArtImage, Art, Art[0]);
                                             switch(input)
                                             {
                                                //case 0 is MINECRAFT SKIN
                                                case 0:
                                                ImageIcon MinecraftJoker = new ImageIcon("MinecraftJoker.png");
                                                JOptionPane.showMessageDialog(null, "You are Minecraft Joker \n\n Creative at heart, the Minecraft Joker enjoys spreading creativity through artistic expression. Never feeling down, people gravitate towards your creative expression.", "Joker Quiz BETA", 2, MinecraftJoker);
                                                break;
                                                //case 1 is COMICS
                                                case 1: 
                                                ImageIcon TrainJoker = new ImageIcon("TrainJoker.jpg");
                                                JOptionPane.showMessageDialog(null, "You are Train Joker \n\n Incredibly proud of your immense comic knowledge, you are among the rare 1% of users who get the Train Joker achievement. You are the sin of pride incarnate.", "Joker Quiz BETA", 2, TrainJoker);
                                             }//end SKIN vs COMICS
                                             
                                 //case 1 is JOKE -->
                                 break;
                                 case 1:
                                             ImageIcon JokeImage = new ImageIcon("JokeImage.FILE");
                                             String[] Joke = {"Throw Laughing Gas", "Tell another"};
                                             input =  JOptionPane.showOptionDialog(null, "Nobody laughs at your joke. As a response, what do you do?", "Joker Quiz BETA", 1, 1, JokeImage, Joke, Joke[0]);
                                             switch(input)
                                             {
                                                //case 0 is THROW LAUGHING GAS --> Heath Ledger Joker
                                                case 0:
                                                ImageIcon HeathJoker = new ImageIcon("HeathJoker.FILE");
                                                JOptionPane.showMessageDialog(null, "You are Heath Ledger Joker\n\nA disturbed, anarchist figure, the Heath joker wants to make society pay. The incarnation of anger, Heath Joker is known to do some damage when angry. Don't get on his bad side >:^(", "Joker Quiz BETA", 2, HeathJoker);
                                                break;
                                                //case 1 is TELL ANOTHER --> LEGO JOKER
                                                case 1:
                                                ImageIcon LegoJoker = new ImageIcon("LegoJoker.FILE");
                                                JOptionPane.showMessageDialog(null, "You are LEGO_JOKER. \n\n You are a funny person. Only the funny people get the lego joker, which is you! Even when people don't find you funny, you don't give up! Good job!", "Joker Quiz BETA", 2, LegoJoker);
                                             }//end THROW GAS vs TELL ANOTHER
                              }//end DRAW vs JOKE   
                                  
                     //case 1 is BLACK COFFEE --> you notice a bully
                     break;
                     case 1:
                           String[] Bully = {"Attack", "Laugh"}; 
                           ImageIcon BullyImage = new ImageIcon("BullyImage.jpg");
                           input = JOptionPane.showOptionDialog(null, "Seeing a bully bothering the innocent employee, you can't help but feel... something. What do you do?", "Joker Quiz BETA", 1, 1, BullyImage, Bully, Bully[0]);
                           switch(input)
                           {
                              //case 0 is ATTACK --> you win and do you dance or society
                              case 0:
                              String[] Win = {"Dance", "Blame Society"};
                              ImageIcon WinImage = new ImageIcon("WinImage.png");
                              input = JOptionPane.showOptionDialog(null, "After slaying the bully, you decide to feel good. Do celebrate, or ponder why you attacked the bully?", "Joker Quiz BETA", 1, 1, WinImage, Win, Win[0]);
                              switch(input)
                              {
                                 //case 0 is DANCE --> epic win or crying?
                                 case 0:
                                 String[] Resolution = {"Epic Win", "Cry"};
                                 ImageIcon ResolutionImage = new ImageIcon("ResolutionImage.png");
                                 input = JOptionPane.showOptionDialog(null, "Euphoric in dance, you feel a strong emotion brewing inside of you! What is it?!", "Joker Quiz BETA", 1, 1, ResolutionImage, Resolution, Resolution[0]);
                                 switch(input)
                                 {
                                    //case 0 is EPIC WIN --> Jared Leto Joker
                                    case 0:
                                    ImageIcon JaredJoker = new ImageIcon("JaredJoker.jpg");
                                    JOptionPane.showMessageDialog(null, "You are Jared Leto Joker \n\n A hero of the people, you always fight to make sure people are treating right. You fight oppression, and most importantly society, with an ardent zeal of righteous justice.", "Joker Quiz BETA", 2, JaredJoker);
                                    break;
                                    //case 1 is CRY --> New Joker
                                    case 1:
                                    ImageIcon NewJoker = new ImageIcon("NewJoker.jpg");
                                    JOptionPane.showMessageDialog(null, "You are New Joker \n\n Unstable but good at heart, the new joker is a sad man. Never doing quite the right thing, you endanger people. Good news is, you are kinda swaggy.", "Joker Quiz BETA", 2, NewJoker);
                                 }//end WIN vs CRY
                                 
                                 //case 1 is BLAME SOCIETY
                                 break;
                                 case 1:
                                 ImageIcon JaredJoker = new ImageIcon("JaredJoker.jpg");
                                 JOptionPane.showMessageDialog(null, "You are Jared Leto Joker \n\n A hero of the people, you always fight to make sure people are treating right. You fight oppression, and most importantly society, with an ardent zeal of righteous justice.", "Joker Quiz BETA", 2, JaredJoker);
                              }//end DANCE vs SOCIETY
                             
                              //case 1 is LAUGH --> New Joker
                              break;
                              case 1:
                              ImageIcon NewJoker = new ImageIcon("NewJoker.jpg");
                              JOptionPane.showMessageDialog(null, "You are New Joker \n\n Unstable but good at heart, the new joker is a sad man. Never doing quite the right thing, you endanger people. Good news is, you are kinda swaggy.", "Joker Quiz BETA", 2, NewJoker);
                           }//end ATTACK vs LAUGH
                  }//end SHAMROCK vs BLACK         
         //case 1 is BAD --> why do you feel bad? --> hate self or hate society
         break;
         case 1:
                  String[] HateReason = {"Hate yourself", "Hate society"};
                  ImageIcon HateImage = new ImageIcon("HateImage.FILE");
                  input = JOptionPane.showOptionDialog(null, "You notice right of the bat you feel rather poorly. Why?", "Joker Quiz BETA", 1, 1, HateImage, HateReason, HateReason[0]);
                  switch(input)
                  {
                     //case 0 is HATE YOURSELF --> ANGER vs SHAME
                     case 0:
                     ImageIcon HateYouImage = new ImageIcon("HateYouImage.FILE");
                     String[] HateYou = {"Anger", "Shame"};
                     input = JOptionPane.showOptionDialog(null, "You have always hated yourself. What do you think is the driving cause behind it?", "Joker Quiz BETA", 1, 1, HateYouImage, HateYou, HateYou[0]);
                     switch(input)
                     {
                        //case 0 is ANGER --> Drew Russel Joker
                        case 0:
                        ImageIcon DrewJoker = new ImageIcon("DrewJoker.FILE"); 
                        JOptionPane.showMessageDialog(null, "You are Drew Russel Joker. \n\n Filled with emotions unimaginable, Drew Russel joker is the epitome of unsettling. Is he angry? Is he scared? No one knows. The only thing they do know is to run. That's all they ever did to drew russel joker... run", "Joker Quiz BETA", 2, DrewJoker);
                        break;
                        //case 1 is SHAME --> LUST or YOU DONT
                        case 1:
                        ImageIcon DarknessImage = new ImageIcon("DarknessImage.FILE");
                        String[] Darkness = {"Lust", "You don't"};
                        input = JOptionPane.showOptionDialog(null, "Searching deeper, you come across two diverging paths of hate. Which is the truth to your self-torment? Why do you hate yourself?!", "Joker Quiz BETA", 1, 1, DarknessImage, Darkness, Darkness[0]);
                        switch(input)
                        {
                           //case 0 is LUST --> Drew Russel Joker
                           case 0:
                           ImageIcon DrewJoker = new ImageIcon("DrewJoker.FILE");
                           JOptionPane.showMessageDialog(null, "You are Drew Russel Joker. \n\n Plagued with a deep self hate from a young age, you lashed out against your peers for your isnecurities. Seeking to comfort your anxiety in less righteous ways, you developed a lustful, shameful way of life. However, you never stop trying to help others around you in the art of performance", "Joker Quiz BETA", 2, DrewJoker);
                           break;
                           //case 1 is YOU DONT
                           case 1:
                           ImageIcon NewJoker = new ImageIcon("NewJoker.jpg");
                           JOptionPane.showMessageDialog(null, "You are New Joker. \n\n For the longest time you were told something was wrong with you... this manifested into self hate. However, in a recent revelation, you realize you don't hate yourself and you now feel 'awake.' Perhaps this could be helpful to healing?", "Joker Quiz BETA", 2, NewJoker);
                        }//end LUST vs YOU DONT
                     }//end ANGER vs SHAME  
                     
                     //case 1 is HATE SOCIETY
                     break;
                     case 1:
                     ImageIcon SocietyImage = new ImageIcon("SocietyImage.FILE");
                     String[] Society = {"Oppression", "Taxes"};
                     input = JOptionPane.showOptionDialog(null, "Realizing your hatred is directed outwards instead of inwards, you realize socity has caused your bad mood. What about society drives you to madness?", "Joker Quiz BETA", 1, 1, SocietyImage, Society, Society[0]);
                     switch(input)
                     {
                        //case 0 is OPPRESSION --> Jared Leto Joker
                        case 0:
                        ImageIcon JaredJoker = new ImageIcon("JaredJoker.jpg");
                        JOptionPane.showMessageDialog(null, "You are Jared Leto Joker \n\n A hero of the people, you always fight to make sure people are treating right. You fight oppression, and most importantly society, with an ardent zeal of righteous justice.", "Joker Quiz BETA", 2, JaredJoker);
                        break;
                        //case 1 is TAXES --> kill count
                        case 1:
                        ImageIcon TaxImage = new ImageIcon("TaxImage.FILE");
                        String[] Tax = {"26", "infinity"};
                        input = JOptionPane.showOptionDialog(null, "Taxes have always set you off a little. Just thinking about the government taking your money is enough to make your soup boil. The government has tried to take your money before, how many GMen have tried to apprehend you (and how many did you defeat >:^) )", "Joker QUiz BETA", 1, 1, TaxImage, Tax, Tax[0]);
                        switch(input)
                        {
                           //case 0 is 26 --> HeathJoker
                           case 0:
                           ImageIcon HeathJoker = new ImageIcon("HeathJoker.FILE");
                           JOptionPane.showMessageDialog(null, "You are Heath Ledger Joker \n\n Heath Joker is bad business. Kill count: 26. Heath Ledger Joker hates taxes, so don't try to take them from him. I'd imagine you are similar to him, no?... Why so serious?", "Joker Quiz BETA", 2, HeathJoker);
                           break;
                           //case 1 is infinity --> HeathJoker
                           case 1:
                           ImageIcon HeathJoker = new ImageIcon("HeathJoker.FILE");
                           JOptionPane.showMessageDialog(null, "You are Heath Ledger Joker \n\n You are one bad dude. You have killed infinity (or may as well be) IRS agents coming for your back. That's how you know you are invincible. I hope no one discovers this dialog route because this is so uninspired", "Joker Quiz BETA", 2, HeathJoker);
                        }//end 26 vs INFINITY
                     }//end OPPRESSION vs TAXES               
                  }//end YOURSELF vs SOCIETY
      }//end GOOD vs BAD
   }
}