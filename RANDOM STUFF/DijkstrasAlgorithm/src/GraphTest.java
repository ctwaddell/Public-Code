// --== CS400 File Header Information ==--
// Name: Casey Waddell
// Email: ctwaddell@wisc.edu
// Team: KB
// TA: Keren
// Lecturer: Gary
// Notes to Grader: N/A

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.NoSuchElementException;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<String> graph;
    
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices A-E
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        // insert edges from Week 09. Dijkstra's Activity
        graph.insertEdge("A","B",2);
        graph.insertEdge("A","D",4);
        graph.insertEdge("A","E",1);
        graph.insertEdge("B","C",5);
        graph.insertEdge("C","A",3);
        graph.insertEdge("D","B",3);
        graph.insertEdge("D","C",7);
        graph.insertEdge("D","E",1);
        graph.insertEdge("E","C",8);
    }

    /**
     * Checks the distance/total weight cost from the vertex labelled C to E
     * (should be 4), and from the vertex labelled A to C (should be 7).
     */
    @Test
    public void providedTestToCheckPathCosts() {
        assertTrue(graph.getPathCost("C", "E") == 4);    
        assertTrue(graph.getPathCost("A", "C") == 7);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * labelled C to E, and from the vertex labelled A to C.
     */
    @Test
    public void providedTestToCheckPathContents() {
        assertTrue(graph.shortestPath("C", "E").toString().equals(
            "[C, A, E]"
        ));
        assertTrue(graph.shortestPath("A", "C").toString().equals(
            "[A, B, C]"
        ));
    }
    
    /**
     * Checks if the ordering of my response to the homework is correct
     * The assertions are what I said was correct (as well as their order)
     */
    @Test
    public void wasICorrectSequenceTest()
    {
      assertTrue(graph.shortestPath("A", "C").toString().equals("[A, B, C]"));
      assertTrue(graph.shortestPath("A", "E").toString().equals("[A, E]"));
      assertTrue(graph.shortestPath("A", "D").toString().equals("[A, D]"));
      assertTrue(graph.shortestPath("A", "B").toString().equals("[A, B]"));
    }
    
    /**
     * Checks if the cost associated with the responses in my homework is correct
     * The assertions are what I said was the correct cost for each.
     */
    @Test
    public void wasICorrectCostTest()
    {
      assertTrue(graph.getPathCost("A", "C") == 7);
      assertTrue(graph.getPathCost("A", "E") == 1);
      assertTrue(graph.getPathCost("A", "D") == 4);
      assertTrue(graph.getPathCost("A", "B") == 2);

    }
    
    /**
     * Checks if the program can handle a disconnected Node correctly
     */
    @Test
    public void disconnectedTest()
    {
      graph.insertVertex("F");
      try
      {
        assertTrue(graph.shortestPath("A", "F").toString().equals("This will never occur"));
      }
      catch(NoSuchElementException e)
      {
        //yes sir
      }
      try
      {
        assertTrue(graph.shortestPath("F", "A").toString().equals("This will never occur"));
      }
      catch(NoSuchElementException e)
      {
        //yes sir (other way around)
      }
      
    }
    
    /**
     * Checks to see what happens if the node to search for is the same as the starting node
     */
    @Test 
    public void toItselfTest()
    {
      assertTrue(graph.shortestPath("A", "A").toString().equals("[A]"));
      assertTrue(graph.getPathCost("A", "A") == 0);
    }
    
    /**
     * Checks to see what happens if the node start or end is a null value
     */
    public void nullTest()
    {
      try
      {
        assertTrue(graph.shortestPath(null, "B").toString().equals("This will never occur part 2"));
      }
      catch(NoSuchElementException e)
      {
        //yes sir
      }
      try
      {
        assertTrue(graph.shortestPath("A", null).toString().equals("This will never occur part 2"));
      }
      catch(NoSuchElementException e)
      {
        //yes sir
      }
    }

}