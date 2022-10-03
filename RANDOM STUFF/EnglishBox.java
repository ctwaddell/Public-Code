
public class EnglishBox<T> implements Parcel<String, T>
{
  private String volume;
  private T weight;
  public EnglishBox(String s, T t)
  {
    volume = s;
    weight = t;
  }
  public String getVolume()
  {
    return volume;
  }
  public T getWeight() 
  {
    return weight;
  }
}
