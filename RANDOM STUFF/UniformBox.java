
public class UniformBox<T> implements Parcel<T, T>
{
  private T volume;
  private T weight;
  public UniformBox(T t, T s)
  {
    volume = t;
    weight = s;
  }
  public T getVolume()
  {
    return volume;
  }
  public T getWeight() 
  {
    return weight;
  }
}
