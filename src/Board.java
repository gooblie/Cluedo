import java.util.*;

public class Board
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Direction { NORTH, EAST, SOUTH, WEST }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Attributes
  private char board[][];

  //Board Associations
  private List<Room> rooms;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(String fileName)
  {

  }

  //------------------------
  // INTERFACE
  //------------------------


  public List<Room> getRooms()
  {
    List<Room> newRooms = Collections.unmodifiableList(rooms);
    return newRooms;
  }



  public boolean isMoveValid(Player player, Direction dir){
    return false;
  }

}