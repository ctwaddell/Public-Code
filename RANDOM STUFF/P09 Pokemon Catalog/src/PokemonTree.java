//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PokemonTree - Structures PokemonNode objects into a binary tree w/ relevant methods
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
 * This class implements a binary search tree (BST) which stores a set of
 * Pokemons. The left subtree contains the Pokemons who are less powerful than
 * the Pokemon stored at a parent node. The right subtree contains the Pokemons
 * who are more powerful than the Pokemon stored at a parent node.
 *
 */
public class PokemonTree 
{
    private PokemonNode root; // root of this binary search tree
    private int size; // total number of Pokemons stored in this tree.

    /**
     * Checks whether this binary search tree (BST) is empty
     * 
     * @return true if this PokemonTree is empty, false otherwise
     */
    public boolean isEmpty() 
    {
        if(size == 0)
        {
          return true;
        }
        else 
        {
          return false;
        }
    }

    /**
     * Returns the number of Pokemons stored in this BST.
     * 
     * @return the size of this PokemonTree
     */
    public int size() 
    {
        return size;
    }

    /**
     * Recursive helper method to add a new Pokemon to a PokemonTree rooted at
     * current.
     * 
     * @param current    The "root" of the subtree we are inserting new pokemon
     *                   into.
     * @param newPokemon The Pokemon to be added to a BST rooted at current.
     * @return true if the newPokemon was successfully added to this PokemonTree,
     *         false otherwise
     */
    public static boolean addPokemonHelper(Pokemon newPokemon, PokemonNode current) 
    {
      if(newPokemon.compareTo(current.getPokemon()) < 0) //to the left to the left to the left...
      {
        if(current.getLeftChild() == null)
        {
          PokemonNode newLeft = new PokemonNode(newPokemon);
          current.setLeftChild(newLeft);
          return true;
        }
        else
        {
          PokemonNode newSeach = current.getLeftChild();
          return addPokemonHelper(newPokemon, newSeach);
        }
      }
      
      if(newPokemon.compareTo(current.getPokemon()) > 0) //to the right to the right...
      {
        if(current.getRightChild() == null)
        {
          PokemonNode newRight = new PokemonNode(newPokemon);
          current.setRightChild(newRight);
          return true;
        }
        else
        {
          PokemonNode newSearch = current.getRightChild();
          return addPokemonHelper(newPokemon, newSearch);
        }
      }
      
      if(newPokemon.compareTo(current.getPokemon()) == 0) //same CP, can't add
      {
        return false;
      }
      else
      {
        return false;
      }
    }

    /**
     * Adds a new Pokemon to this PokemonTree
     * 
     * @param newPokemon a new Pokemon to add to this BST.
     * @return true if the new was successfully added to this BST, and returns false
     *         if there is a match with this Pokemon already already stored in this
     *         BST.
     */
    public boolean addPokemon(Pokemon newPokemon) 
    {
      if (isEmpty()) 
      { // Add new to an empty PokemonTree
        root = new PokemonNode(newPokemon);
        size++;
        return true;
      }
      //DONT FORGET SIZE++;
      else 
      { // Add new to an non-empty PokemonTree
        if(addPokemonHelper(newPokemon, root) == true)
        {
          size++;
          return true;
        }
        else
        {
          return false;
        }
      }
    }

    /**
     * Recursive helper method which returns a String representation of the BST
     * rooted at current. An example of the String representation of the contents of
     * a PokemonTree is provided in the description of the above toString() method.
     * 
     * @param current reference to the current PokemonNode within this BST.
     * @return a String representation of all the Pokemons stored in the sub-tree
     *         PokemonTree rooted at current in increasing order with respect to the
     *         CP values. Returns an empty String "" if current is
     *         null.
     */
    public static String toStringHelper(PokemonNode current) 
    {
      String returnString = "";
      if(current == null)
      {
        return "";
      }
      returnString += toStringHelper(current.getLeftChild());
      returnString += current.getPokemon().toString() + "\n";
      returnString += toStringHelper(current.getRightChild());
      return returnString;
    }

    /**
     * Returns a String representation of all the Pokemons stored within this BST in
     * the increasing order, separated by a newline "\n". For instance:
     * "[Pikachu CP:123 (A:1 S:2 D:3)]" + "\n" + "[Eevee CP:224 (A:2 S:2 D:4)]" + "\n" + 
     * [Lapras CP:735 (A:7 S:3 D:5)] + "\n" + "[Mewtwo CP:999 (A:9 S:9 D:9)]" + "\n"
     * 
     * @return a String representation of all the Pokemons stored within this BST
     *         sorted in an increasing order with respect to the CP values.
     *         Returns an empty string "" if this BST is empty.
     */
    public String toString() 
    {
      if(size == 0)
      {
        return "";
      }
      String returnString = "";
      returnString += toStringHelper(root);
      return returnString;
    }

    /**
     * Search for a Pokemon (Pokemon) given the CP value as lookup key.
     * 
     * @param cp combat power of a Pokemon
     * @return the Pokemon whose CP value equals our lookup key.
     * @throws a NoSuchElementException with a descriptive error message if there is
     *           no Pokemon found in this BST having the provided CP value
     */
    public Pokemon lookup(int cp) 
    {
        return lookupHelper(cp, root);
    }

    /**
     * Recursive helper method to lookup a Pokemon given a reference Pokemon with
     * the same CP in the subtree rooted at current
     * 
     * @param cp    the target cp we are trying to find in the tree
     * 
     * @param current "root" of the subtree we are looking for a match to find
     *                within it.
     * @return reference to the Pokemon stored stored in this BST which matches
     *         find.
     * @throws NoSuchElementException with a descriptive error message if there is
     *                                no Pokemon whose CP value matches target value,
     *                                stored in this BST.
     */
    public static Pokemon lookupHelper(int cp, PokemonNode current) 
    {
      if(current == null)
      {
        throw new NoSuchElementException("The tree is empty!");
      }
      if(current.getPokemon().getCP() > cp)
      {
        if(current.getLeftChild() == null)
        {
          throw new NoSuchElementException("There is no Pokemon with a CP of " + cp + " here");
        }
        PokemonNode newSearch = current.getLeftChild();
        return lookupHelper(cp, newSearch);
      }
      if(current.getPokemon().getCP() < cp)
      {
        if(current.getRightChild() == null)
        {
          throw new NoSuchElementException("There is no Pokemon with a CP of " + cp + " here");
        }
        PokemonNode newSearch = current.getRightChild();
        return lookupHelper(cp, newSearch);
      }
      if(current.getPokemon().getCP() == cp)
      {
        return current.getPokemon();      
      }
      else
      {
        throw new NoSuchElementException("There is no Pokemon with a CP of " + cp + " here");
      }
    }

    /**
     * Computes and returns the height of this BST, counting the number of nodes
     * (PokemonNodes) from root to the deepest leaf.
     * 
     * @return the height of this Binary Search Tree
     */
    public int height() 
    {
      return heightHelper(root);
    }

    /**
     * Recursive helper method that computes the height of the subtree rooted at
     * current
     * 
     * @param current pointer to the current PokemonNode within a PokemonTree
     * @return height of the subtree rooted at current, counting the number of
     *         PokemonNodes
     */
    public static int heightHelper(PokemonNode current) 
    {
      if(current == null) 
      {
        return 0; 
      }
      else 
      {  
        int leftHeight = heightHelper(current.getLeftChild()); 
        int rightHeight = heightHelper(current.getRightChild()); 
        if (leftHeight > rightHeight) 
        {
          leftHeight++;
          return (leftHeight); 
        }
        else 
        {
          rightHeight++;
          return (rightHeight); 
        }
      } 

      /*
      returnString += toStringHelper(current.getLeftChild());
      returnString += current.getPokemon().toString() + "\n";
      returnString += toStringHelper(current.getRightChild());
      return returnString;
      */
    }

    /**
     * Recursive helper method to find the least powerful pokemon in the tree
     * @param current, pointer to the current PokemonNode within the tree
     * @return Pokemon object representing the least powerful one found
     */
    private static Pokemon getLeastPowerfulPokemonHelper(PokemonNode current)
    {
      if(current == null)
      {
        return null;
      }
      if(current.getLeftChild() == null)
      {
        return current.getPokemon();
      }
      PokemonNode newSearch = current.getLeftChild();
      return getLeastPowerfulPokemonHelper(newSearch);
    }
    
    /**
     * Returns the Pokemon of the least powerful Pokemon in this BST.
     * 
     * @return the Pokemon of the least powerful Pokemon in this BST and null if this tree
     *         is empty.
     */
    public Pokemon getLeastPowerfulPokemon() 
    {
      return getLeastPowerfulPokemonHelper(root);
    }

    /**
     * Recursive helper method to find the most powerful pokemon in the tree
     * @param current, pointer to the current PokemonNode within the tree
     * @return Pokemon object representing the most powerful one found
     */
    private static Pokemon getMostPowerfulPokemonHelper(PokemonNode current)
    {
      if(current == null)
      {
        return null;
      }
      if(current.getRightChild() == null)
      {
        return current.getPokemon();
      }
      PokemonNode newSearch = current.getRightChild();
      return getMostPowerfulPokemonHelper(newSearch);
    }
    
    /**
     * Returns the Pokemon of the most powerful Pokemon in this BST.
     * 
     * @return the Pokemon of the most powerful Pokemon in this BST, and null if this tree
     *         is empty.
     */
    public Pokemon getMostPowerfulPokemon() 
    {
      return getMostPowerfulPokemonHelper(root);
    }

}
