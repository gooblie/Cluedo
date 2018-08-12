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

  public int indexOfSquare(Squares aSquare)
  {
    int index = squares.indexOf(aSquare);
    return index;
  }

  public static Room getRoom(Squares location){
    return new Room("Some room");
  }
}