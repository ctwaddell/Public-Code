
class MetricBox implements Parcel<Double, Double>
{
  private Double volume;
  private Double weight;
  public MetricBox(double d, double e) {
    volume = d;
    weight = e;
  }
  public Double getVolume()
  {
    return volume;
  }
  public Double getWeight() 
  {
    return weight;
  }

}
