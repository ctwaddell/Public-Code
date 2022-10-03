class Vehicle
{
  public void sound() { System.out.print("Beep .."); }
}

class Truck extends Vehicle 
{
  public void sound() 
  {
    System.out.print("Honk ..");
  }
  
  public static void main(String[] args)
  {
    Truck swag = new Truck();
    swag.sound();
  }
}
