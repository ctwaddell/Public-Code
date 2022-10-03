import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVTOHTML {

    public static void main(String[] args) {
        try 
        {
            File data = new File("data.csv");
            ArrayList<String[]> dataArray = new ArrayList<>();
            dataArray = (ArrayList<String[]>)readAll(data);
            System.out.println("<html><body><p>");
            System.out.println("<table>");
            System.out.println("<tr>");
            System.out.println("<th>Name</th>");
            System.out.println("<th>MFR</th>");
            System.out.println("<th>Type</th>");
            System.out.println("<th>Calories</th>");
            System.out.println("<th>Protein</th>");
            System.out.println("<th>Fat</th>");
            System.out.println("<th>Sodium</th>");
            System.out.println("<th>Fiber</th>");
            System.out.println("<th>Carbohydrates</th>");
            System.out.println("<th>Sugars</th>");
            System.out.println("<th>Potassium</th>");
            System.out.println("<th>Vitamins</th>");
            System.out.println("<th>Shelf Life</th>");
            System.out.println("<th>Weight</th>");
            System.out.println("<th>Cups</th>");
            System.out.println("<th>Rating</th>");
            System.out.println("</tr>");
            System.out.println("<tr>");
            for(int i = 1; i < dataArray.size(); i++)
            {
              for(int j = 0; j < 16; j++)
              {
                System.out.println("<th>" + dataArray.get(i)[j] + "</th>");
              }
            }
            System.out.println("</tr>");
            System.out.println("</table>");
            System.out.println("</p></body></html>");
        } catch(Exception e) {
            System.out.println("<html><body><pre>");
            System.out.println("Oopsie! ~UwU~, something went wrong. There was an exception in the Java program:");
            e.printStackTrace(System.out);
            System.out.println("</pre></body></html>");
        }
    }

    public static List<String[]> readAll(File csvFile) throws Exception {
        List<String[]> output = new ArrayList<>();
        try (Scanner fin = new Scanner(csvFile)) {
            while(fin.hasNextLine()) {
                output.add(fin.nextLine().split(","));
            }    
        }
        return output;
    }    

}

