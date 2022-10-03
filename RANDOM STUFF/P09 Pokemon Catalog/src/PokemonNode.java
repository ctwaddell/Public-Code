//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PokemonNode - stores Pokemon object's and two references to make a binary tree
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

public class PokemonNode 
{
  private Pokemon data;
  private PokemonNode leftChild;
  private PokemonNode rightChild;
  
  /**
   * One argument constructor for a PokemonNode object
   * @param data, the Pokemon to be stored in this PokemonNode
   */
  public PokemonNode(Pokemon data)
  {
    if(data == null)
    {
      throw new IllegalArgumentException("The pokemon cannot be MISSINGNO");
    }
    this.data = data;
    leftChild = null;
    rightChild = null;
  }
  
  /**
   * getLeftChild returns the leftChild field
   * @return PokemonNode of the node's leftChild field
   */
  public PokemonNode getLeftChild()
  {
    return leftChild;
  }
  
  /**
   * getRightChild returns the rightChild field
   * @return PokemonNode of the node's rightChild field
   */
  public PokemonNode getRightChild()
  {
    return rightChild;
  }
  
  /**
   * getPokemon returns the data field
   * @return Pokemon of the node's data field
   */
  public Pokemon getPokemon()
  {
    return data;
  }
  
  /**
   * setLeftChild resets the leftChild data field to a new PokemonNode
   * @param left, PokemonNode to replace the old leftChild
   */
  public void setLeftChild(PokemonNode left)
  {
    leftChild = left;
  }
  
  /**
   * setRightChild resets the rightChild data field to a new PokemonNode
   * @param right, PokemonNode to replace the old rightChild
   */
  public void setRightChild(PokemonNode right)
  {
    rightChild = right;
  }
}
