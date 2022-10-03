import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>>{

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> 
    {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild; 
        public Node<T> rightChild; 
        public Node(T data) { this.data = data; 
        }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() 
        {
            return parent != null && parent.leftChild == this;
        }
        /**
         * This method performs a level order traversal of the tree rooted
         * at the current node. The string representations of each data value
         * within this tree are assembled into a comma separated string within
         * brackets (similar to many implementations of java.util.Collection).
         * Note that the Node's implementation of toString generates a level
         * order traversal. The toString of the RedBlackTree class below
         * produces an inorder traversal of the nodes / values of the tree.
         * This method will be helpful as a helper for the debugging and testing
         * of your rotation implementation.
         * @return string containing the values of this tree in level order
         */
        @Override
        public String toString() {
            String output = "[";
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
            return output + "]";
        }
    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree
    
    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(root == null) { root = newNode; size++; return true; } // add first node to an empty tree
        else{
            boolean returnValue = insertHelper(newNode,root); // recursively insert into subtree
            if (returnValue) size++;
	    else throw new IllegalArgumentException(
	    	"This RedBlackTree already contains that value.");
            return returnValue;
        }
    }

    /** 
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) return false;

        // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                return true;
            // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else { 
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                return true;
            // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }
    
    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException 
    {
      try
      {
        if(!parent.leftChild.equals(child) && !parent.rightChild.equals(child)) //if the child is not the child
        {
          throw new IllegalArgumentException("The child is not related to the parent");
        }
      }
      catch(NullPointerException e)
      {
        try
        {
          if(!parent.rightChild.equals(child))
          {
            throw new IllegalArgumentException("The child is not related to the parent");
          }
        }
        catch(NullPointerException c)
        {
          throw new IllegalArgumentException("The child is not related to the parent");
        }
      }
      
      boolean isRoot = false;
      if(parent.equals(root))
      {
        isRoot = true;
      }
      
      //left rotation if right child
      if(child.isLeftChild() == false) //check if left or right child
      {
        if(child.leftChild != null) //handle swapping extra children/branches
        {
          parent.rightChild = child.leftChild;
          child.leftChild.parent = parent;
        }
        else
        {
          parent.rightChild = null;
        }
        
        if(isRoot == true) //re assign parents
        {
          root = child;
          child.parent = null;
        } 
        if(isRoot == false)
        {
          if(parent.isLeftChild() == true) //depending on which child the parent is, the swap will differ
          {
            parent.parent.leftChild = child;
            child.parent = parent.parent;
          }
          else
          {
            parent.parent.rightChild = child;
            child.parent = parent.parent;
          }
        }         
       // System.out.println(parent.data);
       // System.out.println(child.data);
       // System.out.println(child.leftChild.data);
        child.leftChild = parent;
        
        parent.parent = child;
        return;
      }
      
      //right rotation method
      if(child.isLeftChild() == true)
      {
        if(child.rightChild != null) //handle swapping extra children/branches
        {
          parent.leftChild = child.rightChild;
          child.rightChild.parent = parent;
        }
        else
        {
          parent.leftChild = null;
        }
        
        if(isRoot == true) //re assign parents
        {
          root = child;
          child.parent = null;
        } 
        if(isRoot == false)
        {
          if(parent.isLeftChild() == false) //right child means new right child
          {
            parent.parent.rightChild = child;
            child.parent = parent.parent;
          }
          else //left child means new left
          {
            parent.parent.leftChild = child;
            child.parent = parent.parent;
          }
        }         
        child.rightChild = parent;
        parent.parent = child;
        return;
      }
    }
      
      //awful, outdated method that doesn't work but I don't want to delete
      //System.out.println("parent: " + parent.data);
      //System.out.println("child: " + child.data);
      //System.out.println("tree: " + root.toString());
      /*
      boolean rootCheck = false;
      boolean childCheck = false;
      boolean childCheck2 = false;
      boolean areSame = false;
      
      if(parent.equals(root))
      {
        rootCheck = true;
      }
      if(child.leftChild != null)
      {
        childCheck = true;
      }
      if(child.rightChild != null)
      {
        childCheck2 = true;
      }
      if(parent.isLeftChild() == true && child.isLeftChild() == true)
      {
        areSame = true;
      }
      if(parent.isLeftChild() != true && child.isLeftChild() != true)
      {
        areSame = true;
      }
      
      //System.out.println(rootCheck + " " + childCheck + " " + child.isLeftChild());
      //!!!might not need the child checks!!!
      
      
      if(child.isLeftChild() == false) //if the child is the right child -- left rotation
      {   
        //LEFT ROTATION
        if(rootCheck == true) //if the parent is the root
        {
          if(areSame == true)
          {
            parent.rightChild = child.leftChild;
            if(child.leftChild == null)
            {
              
            }
            else
            {
              parent.rightChild.parent = parent;
            }
            parent.parent = child;
            child.leftChild = parent;
            return;
          }
          //handle for root scenario
          if(childCheck == true)
          {
            //handle for hasChild
            root = child;
            parent.rightChild = child.leftChild;
            parent.parent = child;
            child.leftChild = parent;
            return;
          }
          if(childCheck == false)
          {
            //handle for no child
            root = child;
            parent.rightChild = null;
            parent.parent = child;
            child.leftChild = parent;
            return;
          }
        }   
        
        if(rootCheck == false) //if the parent isn't the root
        {
          if(areSame == true)
          {
            parent.parent.rightChild = parent.leftChild;
            if(parent.leftChild == null)
            {
              
            }
            else
            {
              parent.leftChild.parent = parent.parent;
            }
            parent.leftChild = parent.parent;
            parent.leftChild.parent = parent;
            return;
          }
          //handle for default/nonroot scenario
          if(childCheck == true)
          {
            //handle for hasChild
            parent.parent.leftChild = child;
            parent.parent = child;
            parent.rightChild = child.leftChild;
            child.leftChild = parent;
            return;
          }
          if(childCheck == false)
          {
            //handle for no child
            parent.parent.leftChild = child;
            parent.parent = child;
            parent.rightChild = null;
            child.leftChild = parent;
            return;
          }
        }
      }   
      
      if(child.isLeftChild() == true) //if the child is the left child -- right rotation
      {   
        //RIGHT ROTATION
        if(rootCheck == true) //if the parent is the root
        {
          if(areSame == true)
          {
            parent.leftChild = child.rightChild;
            if(child.rightChild == null)
            {
              
            }
            else
            {
              parent.leftChild.parent = parent;
            }
            parent.parent = child;
            child.rightChild = parent;
            return;
          }
          //handle for root scenario
          if(childCheck2 == true)
          {
            //handle for hasChild
            root = child;
            parent.leftChild = child.rightChild;
            parent.parent = child;
            child.rightChild = parent;
            return;
          }
          if(childCheck2 == false)
          {
            //handle for no child
            root = child;
            parent.leftChild = null;
            parent.parent = child;
            child.rightChild = parent;
            return;
          }
        }   
        
        if(rootCheck == false) //if the parent isn't the root
        {
          if(areSame == true)
          {
            parent.parent.leftChild = parent.rightChild;
            if(parent.rightChild == null)
            {
              
            }
            else
            {
              parent.rightChild.parent = parent.parent;
            }
            parent.rightChild = parent.parent;
            parent.rightChild.parent = parent;
            return;
          }
          //handle for default/nonroot scenario
          if(childCheck2 == true)
          {
            //handle for hasChild
            parent.parent.rightChild = child;
            parent.parent = child;
            parent.leftChild = child.rightChild;
            child.rightChild = parent;
            return;
          }
          if(childCheck2 == false)
          {
            //handle for no child
            parent.parent.rightChild = child;
            parent.parent = child;
            parent.leftChild = null;
            child.rightChild = parent;
            return;
          }
        }
      }  */ 
    
    
    // For the next two test methods, review your notes from 5.RBT Insert
    // Activity. Questions one and two in that activity presented you with
    // an initial tree and then asked you to trace the changes that would
    // be applied as a result of performing different rotations on that
    // tree. For each of the following tests, you'll create the
    // initial tree that you performed each of these rotations on. Replace the
    // letters from the activity (W, X, V, U, Y, T, and Z) with numbers so that
    // the resulting tree satisfies the BST properties.
    // Then apply the rotations described in that activity: the left-rotation
    // in the Part1 test below, and the right-rotation in the Part2 test below.
    // Then ensure that these tests fail if and only if the level ordering of
    // tree values does not match the order that you came up with in that
    // activity.

    //@BeforeEach
    public void setup()
    {
      RedBlackTree<Integer> testTree = new RedBlackTree<>();
      testTree.insert(5);
      testTree.insert(3);
      testTree.insert(7);
      testTree.insert(1);
      testTree.insert(4);
      testTree.insert(6);
      testTree.insert(8);
    }
    
    public static void main(String[] args)
    {
      RedBlackTree<Integer> testTree = new RedBlackTree<Integer>();
      testTree.insert(41);
      System.out.println(testTree.root.toString());
      testTree.insert(43);
      System.out.println(testTree.root.toString());
      testTree.insert(32);
      System.out.println(testTree.root.toString());
      testTree.insert(68);
      System.out.println(testTree.root.toString());
      testTree.insert(88);
      System.out.println(testTree.root.toString());   
      System.out.println("???" + testTree.root.rightChild.rightChild.data); //expected 68
      System.out.println(testTree.root.rightChild.rightChild.rightChild.data); //expected 88
      testTree.rotate(testTree.root.rightChild.rightChild, testTree.root.rightChild);
      System.out.println(testTree.root.toString());   
      
      testTree.week05ActivityTestPart3();
    }
    
    @Test
    public void week05ActivityTestPart1() 
    {
      System.out.println("testonebegin");
      RedBlackTree<Integer> testTree = new RedBlackTree<>();
      testTree.insert(5);
      testTree.insert(3);
      testTree.insert(7);
      testTree.insert(1);
      testTree.insert(4);
      testTree.insert(6);
      testTree.insert(8);
      if(!testTree.root.toString().equals("[5, 3, 7, 1, 4, 6, 8]"))
      {
        fail("The tree was not constructed properly");
      }
      testTree.rotate(testTree.root.leftChild.rightChild, testTree.root.leftChild);
      if(!testTree.root.toString().equals("[5, 4, 7, 3, 6, 8, 1]"))
      {
        fail("The tree was not (left rotation) rotated correctly");
      }
    }

    @Test
    public void week05ActivityTestPart2() 
    {
      RedBlackTree<Integer> testTree = new RedBlackTree<>();
      testTree.insert(5);
      testTree.insert(3);
      testTree.insert(7);
      testTree.insert(1);
      testTree.insert(4);
      testTree.insert(6);
      testTree.insert(8);
      if(!testTree.root.toString().equals("[5, 3, 7, 1, 4, 6, 8]"))
      {
        fail("The tree was not constructed properly");
      }
      System.out.println(testTree.root.toString());
      testTree.rotate(testTree.root.leftChild, testTree.root);
      System.out.println(testTree.root.toString());
      if(!testTree.root.toString().equals("[3, 1, 5, 4, 7, 6, 8]"))
      {
        fail("The tree was not (right rotation) rotated correctly");
      }
    }

    // For the third test, you will recreate the first rotation in your tree in
    // steps 3-8 of the 05.RBT Insert Activity. Ensure that this test only fails
    // if the result does not match the order of the expected result.
	
	@Test
	public void week05ActivityTestPart3() {
      RedBlackTree<Integer> testTree = new RedBlackTree<>();
      testTree.insert(41);
      if(!testTree.root.toString().equals("[41]"))
      {
        fail("The tree insertion was not done correctly");
      }
      testTree.insert(43);
      if(!testTree.root.toString().equals("[41, 43]"))
      {
        fail("The tree insertion/rotation was not done correctly");
      }
      testTree.insert(32);
      if(!testTree.root.toString().equals("[41, 32, 43]"))
      {
        fail("The tree insertion/rotation was not done correctly");
      }
      testTree.insert(68);
      if(!testTree.root.toString().equals("[41, 32, 43, 68]"))
      {
        fail("The tree insertion/rotation was not done correctly");
      }
      testTree.insert(88);
      if(!testTree.root.toString().equals("[41, 32, 43, 68, 88]"))
      {
        fail("The tree insertion/rotation was not done correctly");
      }
      testTree.rotate(testTree.root.rightChild.rightChild, testTree.root.rightChild);
      if(!testTree.root.toString().equals("[41, 32, 68, 43, 88]"))
      {
        fail("The rotation within the tree was unsuccessful");
      }
      int heightCounter = 1;
      Node<T> nextOne = root;
      try
      {
        while(nextOne.rightChild != null)
        {
          heightCounter++;
          nextOne = nextOne.rightChild;
        }
      }
      catch(NullPointerException e)
      {
        
      }
      if(heightCounter != 3)
      {
        fail("The expected height did not match the calculated height");
      }
	}

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }

    /**
     * Returns an iterator over the values in in-order (sorted) order.
     * @return iterator object that traverses the tree in in-order sequence
     */
    
    public Iterator<T> iterator() {
        // use an anonymous class here that implements the Iterator interface
        // we create a new on-off object of this class everytime the iterator
        // method is called
        return new Iterator<T>() {
            // a stack and current reference store the progress of the traversal
            // so that we can return one value at a time with the Iterator
            Stack<Node<T>> stack = null;
            Node<T> current = root;

            /**
             * The next method is called for each value in the traversal sequence.
             * It returns one value at a time.
             * @return next value in the sequence of the traversal
             * @throws NoSuchElementException if there is no more elements in the sequence
             */
            public T next() {
                // if stack == null, we need to initialize the stack and current element
                if (stack == null) {
                    stack = new Stack<Node<T>>();
                    current = root;
                }
                // go left as far as possible in the sub tree we are in until we hit a null
                // leaf (current is null), pushing all the nodes we fund on our way onto the
                // stack to process later
                while (current != null) {
                    stack.push(current);
                    current = current.leftChild;
                }
                // as long as the stack is not empty, we haven't finished the traversal yet;
                // take the next element from the stack and return it, then start to step down
                // its right subtree (set its right sub tree to current)
                if (!stack.isEmpty()) {
                    Node<T> processedNode = stack.pop();
                    current = processedNode.rightChild;
                    return processedNode.data;
                } else {
                    // if the stack is empty, we are done with our traversal
                    throw new NoSuchElementException("There are no more elements in the tree");
                }

            }

            /**
             * Returns a boolean that indicates if the iterator has more elements (true),
             * or if the traversal has finished (false)
             * @return boolean indicating whether there are more elements / steps for the traversal
             */
            public boolean hasNext() {
                // return true if we either still have a current reference, or the stack
                // is not empty yet
                return !(current == null && (stack == null || stack.isEmpty()) );
            }
            
        };
    }

    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    @Override
    public String toString() {
        // use the inorder Iterator that we get by calling the iterator method above
        // to generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        Iterator<T> treeNodeIterator = this.iterator();
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (treeNodeIterator.hasNext())
            sb.append(treeNodeIterator.next());
        while (treeNodeIterator.hasNext()) {
            T data = treeNodeIterator.next();
            sb.append(", ");
            sb.append(data.toString());
        }
        sb.append(" ]");
        return sb.toString();
    }

}
