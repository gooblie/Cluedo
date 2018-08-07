/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.*;

// line 8 "model.ump"
// line 65 "model.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Associations
  private List<Squares> squares;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board()
  {
    squares = new ArrayList<Squares>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Squares getSquare(int index)
  {
    Squares aSquare = squares.get(index);
    return aSquare;
  }

  public List<Squares> getSquares()
  {
    List<Squares> newSquares = Collections.unmodifiableList(squares);
    return newSquares;
  }

  public int numberOfSquares()
  {
    int number = squares.size();
    return number;
  }

  public boolean hasSquares()
  {
    boolean has = squares.size() > 0;
    return has;
  }

  public int indexOfSquare(Squares aSquare)
  {
    int index = squares.indexOf(aSquare);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSquares()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addSquare(Squares aSquare)
  {
    boolean wasAdded = false;
    if (squares.contains(aSquare)) { return false; }
    squares.add(aSquare);
    if (aSquare.indexOfBoard(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSquare.addBoard(this);
      if (!wasAdded)
      {
        squares.remove(aSquare);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeSquare(Squares aSquare)
  {
    boolean wasRemoved = false;
    if (!squares.contains(aSquare))
    {
      return wasRemoved;
    }

    int oldIndex = squares.indexOf(aSquare);
    squares.remove(oldIndex);
    if (aSquare.indexOfBoard(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSquare.removeBoard(this);
      if (!wasRemoved)
      {
        squares.add(oldIndex,aSquare);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSquareAt(Squares aSquare, int index)
  {  
    boolean wasAdded = false;
    if(addSquare(aSquare))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSquares()) { index = numberOfSquares() - 1; }
      squares.remove(aSquare);
      squares.add(index, aSquare);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSquareAt(Squares aSquare, int index)
  {
    boolean wasAdded = false;
    if(squares.contains(aSquare))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSquares()) { index = numberOfSquares() - 1; }
      squares.remove(aSquare);
      squares.add(index, aSquare);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSquareAt(aSquare, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Squares> copyOfSquares = new ArrayList<Squares>(squares);
    squares.clear();
    for(Squares aSquare : copyOfSquares)
    {
      aSquare.removeBoard(this);
    }
  }

}