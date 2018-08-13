
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

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public Position move(Board.Direction direction){
    int newX = this.x;
    int newY = this.y;
    switch (direction){
      case EAST:
        newX++;
      case WEST:
        newX--;
      case NORTH:
        newY--;
      case SOUTH:
        newY++;
    }
    return new Position(newX, newY);
  }

}