//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PokemonTreeTester - tests that the PokemonTree and PokemonNode classes work correctly.
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

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods
 * defined in the class PokemonTree.
 *
 */
public class PokemonTreeTester 
{

    /**
     * Checks the correctness of the implementation of both addPokemon() and
     * toString() methods implemented in the PokemonTree class. This unit test
     * considers at least the following scenarios. (1) Create a new empty
     * PokemonTree, and check that its size is 0, it is empty, and that its string
     * representation is an empty string "". (2) try adding one Pokemon and then
     * check that the addPokemon() method call returns true, the tree is not empty,
     * its size is 1, and the .toString() called on the tree returns the expected
     * output. (3) Try adding another Pokemon which is more powerful than the one at
     * the root, (4) Try adding a third Pokemon which is less powerful than the one
     * at the root, (5) Try adding at least two further Pokemons such that one must
     * be added at the left subtree, and the other at the right subtree. For all the
     * above scenarios, and more, double check each time that size() method returns
     * the expected value, the add method call returns true, and that the
     * .toString() method returns the expected string representation of the contents
     * of the binary search tree in an ascendant order from the most powerful
     * Pokemon to the least powerful one. (6) Try adding a Pokemon whose CP value
     * was used as a key for a Pokemon already stored in the tree. Make sure that
     * the addPokemon() method call returned false, and that the size of the tree
     * did not change.
     * 
     * @return true when this test verifies a correct functionality, and false
     *         otherwise
     */
    public static boolean testAddPokemonToStringSize() 
    {
      //scenario 1
      PokemonTree testTree = new PokemonTree();
      if(testTree.size() != 0)
      {
        return false;
      }
      if(testTree.isEmpty() != true)
      {
        return false;
      }
      if(!testTree.toString().equals(""))
      {
        return false;
      }
      
      //scenario 2
      Pokemon finneon = new Pokemon("Finneon", "1,1,1");
      if(testTree.addPokemon(finneon) != true)
      {
        return false;
      }
      if(testTree.size() != 1)
      {
        return false;
      }
      if(testTree.isEmpty() != false)
      {
        return false;
      }
      if(!testTree.toString().equals("[Finneon CP:111 (A:1 S:1 D:1)]" + "\n"))
      {
        return false;
      }
      
      //scenario 3
      Pokemon carbink = new Pokemon("Carbink", "2,2,2");
      if(testTree.addPokemon(carbink) != true)
      {
        return false;
      }
      if(testTree.size() != 2)
      {
        return false;
      }
      if(!testTree.toString().equals("[Finneon CP:111 (A:1 S:1 D:1)]" + "\n" + 
                                     "[Carbink CP:222 (A:2 S:2 D:2)]" + "\n"))
      {
        return false;
      }
      
      //scenario 4
      Pokemon castform = new Pokemon("Castform", "0,1,1");
      if(testTree.addPokemon(castform) != true)
      {
        return false;
      }
      if(testTree.size() != 3)
      {
        return false;
      }
      if(!testTree.toString().equals("[Castform CP:11 (A:0 S:1 D:1)]" + "\n" + 
                                     "[Finneon CP:111 (A:1 S:1 D:1)]" + "\n" + 
                                     "[Carbink CP:222 (A:2 S:2 D:2)]" + "\n"))
      {
        return false;
      }
      
      //scenario 5
      Pokemon jakePaul = new Pokemon("Jake Paul", "0,2,1");
      Pokemon loganPaul = new Pokemon("Logan Paul", "9,9,9");
      if(testTree.addPokemon(jakePaul) != true)
      {
        return false;
      }
      if(testTree.addPokemon(loganPaul) != true)
      {
        return false;
      }
      if(testTree.size() != 5)
      {
        return false;
      }
      if(!testTree.toString().equals("[Castform CP:11 (A:0 S:1 D:1)]" + "\n" + 
                                     "[Jake Paul CP:21 (A:0 S:2 D:1)]" + "\n" + 
                                     "[Finneon CP:111 (A:1 S:1 D:1)]" + "\n" + 
                                     "[Carbink CP:222 (A:2 S:2 D:2)]" + "\n" + 
                                     "[Logan Paul CP:999 (A:9 S:9 D:9)]" + "\n"))  
      {
        return false;
      }
      Pokemon djSlacc = new Pokemon("DJ Slacc", "9,9,9");
      if(testTree.addPokemon(djSlacc) != false)
      {
        return false;
      }
      if(testTree.size() != 5)
      {
        return false;
      }
      return true;
    }

    /**
     * This method checks mainly for the correctness of the PokemonTree.lookup()
     * method. It must consider at least the following test scenarios. (1) Create a
     * new PokemonTree. Then, check that calling the lookup() method with any valid
     * CP value must throw a NoSuchElementException. (2) Consider a PokemonTree of
     * height 3 which consists of at least 5 PokemonNodes. Then, try to call
     * lookup() method to search for the Pokemon at the root of the tree, then a
     * Pokemon at the right and left subtrees at different levels. Make sure that
     * the lookup() method returns the expected output for every method call. (3)
     * Consider calling .lookup() method on a non-empty PokemonTree with a CP value 
     * not stored in the tree, and ensure that the method call throws a
     * NoSuchElementException.
     * 
     * @return true when this test verifies a correct functionality, and false
     *         otherwise
     */
    public static boolean testAddPokemonAndLookup() 
    {
      //scenario 1
      PokemonTree testTree = new PokemonTree();
      try
      {
        if(testTree.lookup(17).equals(null))
        {
          return false;
        }
      }
      catch(NullPointerException e)
      {
        //expected to happen
      }
      
      //scenario 2
      Pokemon jakePaul = new Pokemon("Jake Paul", "5,5,5");
      Pokemon loganPaul = new Pokemon("Logan Paul", "9,9,8");
      Pokemon djSlacc = new Pokemon("DJ Slacc", "9,9,9");
      Pokemon jungwon = new Pokemon("Jungwon", "0,0,1");
      Pokemon thomas = new Pokemon("Thomas", "0,0,0");
      testTree.addPokemon(jakePaul);
      testTree.addPokemon(loganPaul);
      testTree.addPokemon(djSlacc);
      testTree.addPokemon(jungwon);
      testTree.addPokemon(thomas);
      if(!testTree.lookup(555).equals(jakePaul))
      {
        return false;
      }
      if(!testTree.lookup(0).equals(thomas))
      {
        return false;
      }
      if(!testTree.lookup(999).equals(djSlacc))
      {
        return false;
      }
      
      //scenario 3
      try
      {
        if(testTree.lookup(333).equals(jungwon))
        {
          return false;
        }
      }
      catch(NoSuchElementException e)
      {
        //expected case
      }
      
      return true;
    }

    /**
     * Checks for the correctness of PokemonTree.height() method. This test must
     * consider several scenarios such as, (1) ensures that the height of an empty
     * Pokemon tree is zero. (2) ensures that the height of a tree which consists of
     * only one node is 1. (3) ensures that the height of a PokemonTree with the
     * following structure for instance, is 4. 
     *      (*) 
     *     /   \ 
     *   (*)   (*) 
     *     \   / \ 
     *    (*)(*) (*) 
     *           /
     *         (*)
     * 
     * @return true when this test verifies a correct functionality, and false
     *         otherwise
     */
    public static boolean testHeight() 
    {
      //scenario 1
      PokemonTree testTree = new PokemonTree();
      if(testTree.height() != 0)
      {
        return false;
      }
      
      //scenario 2
      Pokemon swagDas = new Pokemon("SwagDas", "5,5,5");
      testTree.addPokemon(swagDas);
      if(testTree.height() != 1)
      {
        return false;
      }
      
      //scenario 3
      PokemonTree newTree = new PokemonTree();
      Pokemon loosey = new Pokemon("loosey", "1,1,1");
      Pokemon goosey = new Pokemon("goosey", "1,1,2");
      Pokemon swagDer = new Pokemon("SwagDer", "5,5,7");
      Pokemon swagDie = new Pokemon("SwagDie", "5,5,6");
      Pokemon beelzebub = new Pokemon("Beelzebub", "6,6,6");
      Pokemon jakePaul = new Pokemon("Jake Paul", "9,9,9");
      newTree.addPokemon(swagDas);
      newTree.addPokemon(swagDer);
      newTree.addPokemon(swagDie);
      newTree.addPokemon(beelzebub);
      newTree.addPokemon(jakePaul);
      newTree.addPokemon(loosey);
      newTree.addPokemon(goosey);
      if(newTree.height() != 4)
      {
        return false;
      }
      return true;
    }

    /**
     * Checks for the correctness of PokemonTree.getLeastPowerfulPokemon() method.
     * 
     * @return true when this test verifies a correct functionality, and false
     *         otherwise
     */
    public static boolean testGetLeastPowerfulPokemon()
    {
      PokemonTree testTree = new PokemonTree();
      Pokemon jakePaul = new Pokemon("Jake Paul", "5,5,5");
      Pokemon fakePaul = new Pokemon("Fake Paul", "2,2,2");
      Pokemon loganPaul = new Pokemon("Logan Paul", "9,9,9");
      Pokemon aaronPaul = new Pokemon("Jesse", "1,1,1");
      testTree.addPokemon(jakePaul);
      testTree.addPokemon(fakePaul);
      testTree.addPokemon(loganPaul);
      testTree.addPokemon(aaronPaul);
      if(!testTree.getLeastPowerfulPokemon().equals(aaronPaul))
      {
        return false;
      }
      
      return true;
    }

    /**
     * Checks for the correctness of PokemonTree.getMostPowerfulPokemon() method.
     * 
     * @return true when this test verifies a correct functionality, and false
     *         otherwise
     */
    public static boolean testGetMostPowerfulPokemon()
    {
      PokemonTree testTree = new PokemonTree();
      Pokemon jakePaul = new Pokemon("Jake Paul", "5,5,5");
      Pokemon fakePaul = new Pokemon("Fake Paul", "1,1,1");
      Pokemon loganPaul = new Pokemon("Logan Paul", "9,9,9");
      Pokemon aaronPaul = new Pokemon("Jesse", "7,7,7");
      testTree.addPokemon(jakePaul);
      testTree.addPokemon(fakePaul);
      testTree.addPokemon(loganPaul);
      testTree.addPokemon(aaronPaul);
      if(!testTree.getMostPowerfulPokemon().equals(loganPaul))
      {
        return false;
      }
      
      return true;
    }

    /**
     * Calls the test methods
     * 
     * @param args input arguments if any
     */
    public static void main(String[] args) 
    {
      System.out.println(testAddPokemonToStringSize());
      System.out.println(testAddPokemonAndLookup());
      System.out.println(testHeight());
      System.out.println(testGetLeastPowerfulPokemon());
      System.out.println(testGetMostPowerfulPokemon());
    }

}
