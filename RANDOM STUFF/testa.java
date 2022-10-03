import java.util.List;

public class testa 
{
  public static void tester()
  {
    MyList myList = new MyList();
    for(int i = 0; i < 10; i++)
    {
        myList.add(i);
    }
    System.out.println(myList.size());
    for(int j = 0; j < 10; j++)
    {
      System.out.println("index " + j);
        myList.remove(0);
        System.out.println(myList.size());
    }
  }

  public static void main(String[] args)
  {
    testa.tester();
  }
}
