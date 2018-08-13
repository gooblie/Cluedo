/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.*;

// line 16 "model.ump"
// line 85 "model.ump"
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
  private char board;

  //Board Associations
  private List<Position> positions;
  private List<Room> rooms;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(char aBoard, Room... allRooms)
  {
    board = aBoard;
    positions = new ArrayList<Position>();
    rooms = new ArrayList<Room>();
    boolean didAddRooms = setRooms(allRooms);
    if (!didAddRooms)
    {
      throw new RuntimeException("Unable to create Board, must have 9 rooms");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBoard(char aBoard)
  {
    boolean wasSet = false;
    board = aBoard;
    wasSet = true;
    return wasSet;
  }

  public char getBoard()
  {
    return board;
  }
  /* Code from template association_GetMany */
  public Position getPosition(int index)
  {
    Position aPosition = positions.get(index);
    return aPosition;
  }

  public List<Position> getPositions()
  {
    List<Position> newPositions = Collections.unmodifiableList(positions);
    return newPositions;
  }

  public int numberOfPositions()
  {
    int number = positions.size();
    return number;
  }

  public boolean hasPositions()
  {
    boolean has = positions.size() > 0;
    return has;
  }

  public int indexOfPosition(Position aPosition)
  {
    int index = positions.indexOf(aPosition);
    return index;
  }
  /* Code from template association_GetMany */
  public Room getRoom(int index)
  {
    Room aRoom = rooms.get(index);
    return aRoom;
  }

  public List<Room> getRooms()
  {
    List<Room> newRooms = Collections.unmodifiableList(rooms);
    return newRooms;
  }

  public int numberOfRooms()
  {
    int number = rooms.size();
    return number;
  }

  public boolean hasRooms()
  {
    boolean has = rooms.size() > 0;
    return has;
  }

  public int indexOfRoom(Room aRoom)
  {
    int index = rooms.indexOf(aRoom);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPositions()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addPosition(Position aPosition)
  {
    boolean wasAdded = false;
    if (positions.contains(aPosition)) { return false; }
    positions.add(aPosition);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePosition(Position aPosition)
  {
    boolean wasRemoved = false;
    if (positions.contains(aPosition))
    {
      positions.remove(aPosition);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPositionAt(Position aPosition, int index)
  {  
    boolean wasAdded = false;
    if(addPosition(aPosition))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPositions()) { index = numberOfPositions() - 1; }
      positions.remove(aPosition);
      positions.add(index, aPosition);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePositionAt(Position aPosition, int index)
  {
    boolean wasAdded = false;
    if(positions.contains(aPosition))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPositions()) { index = numberOfPositions() - 1; }
      positions.remove(aPosition);
      positions.add(index, aPosition);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPositionAt(aPosition, index);
    }
    return wasAdded;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfRooms()
  {
    return 9;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRooms()
  {
    return 9;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfRooms()
  {
    return 9;
  }
  /* Code from template association_SetUnidirectionalN */
  public boolean setRooms(Room... newRooms)
  {
    boolean wasSet = false;
    ArrayList<Room> verifiedRooms = new ArrayList<Room>();
    for (Room aRoom : newRooms)
    {
      if (verifiedRooms.contains(aRoom))
      {
        continue;
      }
      verifiedRooms.add(aRoom);
    }

    if (verifiedRooms.size() != newRooms.length || verifiedRooms.size() != requiredNumberOfRooms())
    {
      return wasSet;
    }

    rooms.clear();
    rooms.addAll(verifiedRooms);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    positions.clear();
    rooms.clear();
  }

  // line 19 "model.ump"
   public boolean isMoveValid(Player player, Direction dir){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "board" + ":" + getBoard()+ "]";
  }
}