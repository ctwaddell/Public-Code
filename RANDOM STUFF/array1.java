/*
Lori Hunt
This program is written with the formative assessment we used in class
to learn about arrays


*/

public class array1
{
   // format an integer array as a String to make it print nicely
   public static String print(int[] a)
   {
      String output = "[";
      for( int number : a)
      {
         output += number + " ";
      }
      return output + "]";
   }
   
   // add one to every element of an integer array
   public static void increment(int[] a)
   {
      for(int i = 0; i<a.length; i++)
      {
         a[i]++;
      }
   }

   // add 1 to an integer sent as parameter
   public static void increment(int a)
   {
      a++;
   }
   
   
   // this method will determine if there is more than 1
   // of the same number in an array.
   public static boolean moreThan1(int[] a)
   {
      for(int i = 0; i<a.length;i++)
      {
         for(int j = i+1; j<a.length; j++)
         {
            if(a[i] == a[j]) return true;
         }
      }//end for
   return false;
   } 

   public static void main(String[] args)
   {
      //declare an object reference data type (list of ints)
      int[] myObject = {9, 21,4, 3, 17, 21};
      // declare a primitive data type (int)
      int myPrimitive = 9;
      //Notice a primitive variable value will not change when it is 
      // sent to a function (so if you want the new, changed value
      // you would have to return it!
      System.out.println("myPrimitive before the call is "+myPrimitive);
      increment(myPrimitive);
      System.out.println("myPrimitive after the call is "+myPrimitive);
      
      // But an object sent as a parameter is working as a 'pointer'
      // so if it changes in the method, it will change here too!
      System.out.println("myObject before the call is "+print(myObject));
      increment(myObject);
      System.out.println("myObject after the call is "+print(myObject));
      
      System.out.println(moreThan1(myObject));      
   
   }
}


