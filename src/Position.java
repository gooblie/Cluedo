/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/



// line 46 "model.ump"
// line 106 "model.ump"
public class Position
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Position Attributes
  private int x;
  private int y;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Position(int aX, int aY)
  {
    x = aX;
    y = aY;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setX(int aX)
  {
    boolean wasSet = false;
    x = aX;
    wasSet = true;
    return wasSet;
  }

  public boolean setY(int aY)
  {
    boolean wasSet = false;
    y = aY;
    wasSet = true;
    return wasSet;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]";
  }
}