import java.io.FileReader;
import java.util.LinkedList;
import CS400Graph.Path;

public class Backend implements BackendInterface
{
  public class ParkDataReaderDummy
  {
    public ParkDataReaderDummy(String filename)
    {
      //DataWranglerShit.doFileReadingMagic
    }
  }
  private static ParkDataReaderDummy reader; 
  private static FileReader fr;
  
  public Path returnPathTo(ParkDummy destination) 
  {
    // TODO Auto-generated method stub
    return null;
  }

  public Path fromTo(ParkDummy departure, ParkDummy destination)
  {
    // TODO Auto-generated method stub
    return null;
  }

  public Path returnPathTo(ParkDummy destination)
  {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean doesParkExist(String searchQuery) 
  {
    // TODO Auto-generated method stub
    return false;
  }

  public LinkedList<ParkDummy> findParks(String searchQuery)
  {
    // TODO Auto-generated method stub
    return null;
  }

  public ParkDummy randomPark() 
  {
    // TODO Auto-generated method stub
    return null;
  }
}
