/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.*;

// line 52 "model.ump"
// line 114 "model.ump"
public class Squares
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Squares Associations
  private List<Board> boards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Squares()
  {
    boards = new ArrayList<Board>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Board getBoard(int index)
  {
    Board aBoard = boards.get(index);
    return aBoard;
  }

  public List<Board> getBoards()
  {
    List<Board> newBoards = Collections.unmodifiableList(boards);
    return newBoards;
  }

  public int numberOfBoards()
  {
    int number = boards.size();
    return number;
  }

  public boolean hasBoards()
  {
    boolean has = boards.size() > 0;
    return has;
  }

  public int indexOfBoard(Board aBoard)
  {
    int index = boards.indexOf(aBoard);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBoards()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBoard(Board aBoard)
  {
    boolean wasAdded = false;
    if (boards.contains(aBoard)) { return false; }
    boards.add(aBoard);
    if (aBoard.indexOfSquare(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBoard.addSquare(this);
      if (!wasAdded)
      {
        boards.remove(aBoard);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBoard(Board aBoard)
  {
    boolean wasRemoved = false;
    if (!boards.contains(aBoard))
    {
      return wasRemoved;
    }

    int oldIndex = boards.indexOf(aBoard);
    boards.remove(oldIndex);
    if (aBoard.indexOfSquare(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBoard.removeSquare(this);
      if (!wasRemoved)
      {
        boards.add(oldIndex,aBoard);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBoardAt(Board aBoard, int index)
  {  
    boolean wasAdded = false;
    if(addBoard(aBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBoards()) { index = numberOfBoards() - 1; }
      boards.remove(aBoard);
      boards.add(index, aBoard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBoardAt(Board aBoard, int index)
  {
    boolean wasAdded = false;
    if(boards.contains(aBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBoards()) { index = numberOfBoards() - 1; }
      boards.remove(aBoard);
      boards.add(index, aBoard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBoardAt(aBoard, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Board> copyOfBoards = new ArrayList<Board>(boards);
    boards.clear();
    for(Board aBoard : copyOfBoards)
    {
      aBoard.removeSquare(this);
    }
  }

}