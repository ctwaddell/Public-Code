
public class randomTester 
{
  
  public static String print(int[] a)
  {
    String s = "";
    for(int i = 0; i < a.length; i++)
    {
      s += a[i] + ", ";
    }
    return s;
  }
  
  public static void heapify(int[] a)
  {
  int rightLeaf = a.length-1;
  int leftLeaf = a.length-2;
  int prioSwap = -1;
  int tempNode = -1;
  int swapFactor = 0;
    while(leftLeaf >= 0 && rightLeaf >= 0)
    {
      if(a[((rightLeaf)-1)/2] < (a[rightLeaf]) || a[((leftLeaf)-1)/2] < (a[leftLeaf]))
      {
        if(a[rightLeaf] > (a[leftLeaf])) //if right > left
        {
          prioSwap = rightLeaf;
        }
        else
        {
          prioSwap = leftLeaf;
        }
        tempNode = a[prioSwap];
        a[prioSwap] = a[((rightLeaf)-1)/2];
        a[((rightLeaf)-1)/2] = tempNode;
        swapFactor++;
      }
      rightLeaf = rightLeaf - 2;
      leftLeaf = leftLeaf - 2;
      if(swapFactor > 0)
      {
        rightLeaf = a.length-1;
        leftLeaf = a.length-2;
        swapFactor = 0;
      }
    }
  }
  
  public static void percolateDown(int i, int[] a)
  {
    
    if((i*2) + 1 > a.length) //if index does not exist, avoid IndexOutOfBounds
    {
      System.out.println("index DNE");
      return;
    }
    if(a[(i*2) + 1] == 0 || a[(i*2) + 2] == 0) //if left child is empty the right is also
    {
      System.out.println("children are empty");
      return;
    }
    
    
    
    
    int chooseFactor = a[(i*2) + 1] - a[(i*2) + 2];
    System.out.println(chooseFactor);
    //if chooseFactor is 1 left child is greater than right child
    //if chooseFactor is 0 technically not possible
    //if chooseFactor is -1 right child is greater than left child
    System.out.println("I do be percolatin' ------------------");
    if(chooseFactor > 0) //prioritize left first
    {

      if(a[i] - a[i*2 + 1] < 0) //if parent is less than (left) child
      {
        int tempNode = a[i];
        a[i] = a[(i*2) + 1];
        a[(i*2) + 1] = tempNode;
        percolateDown((i*2) + 1, a);
      }
      if(a[i] - a[(i*2) + 2] < 0) //if parent is less than (right) child
      {
        int tempNode = a[i];
        a[i] = a[(i*2) + 2];
        a[(i*2) + 1] = tempNode;
        percolateDown((i*2) + 2, a);
      }
    }
    
    if(chooseFactor < 0) //prioritize right first
    {
      System.out.println((a[i] - a[(i*2)+2]));
      if(a[i] - a[(i*2) + 2] < 0) //if parent is less than (right) child
      {
        int tempNode = a[i];
        a[i] = a[(i*2) + 2];
        a[(i*2) + 2] = tempNode;
        percolateDown((i*2) + 2, a);
      }
      if(a[i] - a[i*2 + 1] < 0) //if parent is less than (left) child
      {
        int tempNode = a[i];
        a[i] = a[(i*2) + 1];
        a[(i*2) + 1] = tempNode;
        percolateDown((i*2) + 1, a);
      }
    }
    
  }
  
  public static void main(String[] args)
  {
    int[] tester = {2,1,4,7,8,19,21};
    System.out.println(print(tester));
    percolateDown(1, tester);
    System.out.println(print(tester));
    int[] testertwo = new int[10]   ;
    System.out.println(print(testertwo));
  }
}
