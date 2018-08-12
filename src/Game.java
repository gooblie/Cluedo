/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 58 "model.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Associations
  private Stack stack;
  private List<Player> players;
  private Board board;
  private Envelope envelope;
  private Call suggestion;
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(Stack aStack, Board aBoard, Envelope aEnvelope, Player... allPlayers)
  {
    if (!setStack(aStack))
    {
      throw new RuntimeException("Unable to create Game due to aStack");
    }
    players = new ArrayList<Player>();
    boolean didAddPlayers = setPlayers(allPlayers);
    if (!didAddPlayers)
    {
      throw new RuntimeException("Unable to create Game, must have 1 to 6 players");
    }
    if (!setBoard(aBoard))
    {
      throw new RuntimeException("Unable to create Game due to aBoard");
    }
    if (!setEnvelope(aEnvelope))
    {
      throw new RuntimeException("Unable to create Game due to aEnvelope");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Stack getStack()
  {
    return stack;
  }
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_GetOne */
  public Board getBoard()
  {
    return board;
  }
  /* Code from template association_GetOne */
  public Envelope getEnvelope()
  {
    return envelope;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setStack(Stack aNewStack)
  {
    boolean wasSet = false;
    if (aNewStack != null)
    {
      stack = aNewStack;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfPlayers()
  {
    return 6;
  }
  /* Code from template association_AddUnidirectionalMN */
  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    if (numberOfPlayers() < maximumNumberOfPlayers())
    {
      players.add(aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    if (!players.contains(aPlayer))
    {
      return wasRemoved;
    }

    if (numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasRemoved;
    }

    players.remove(aPlayer);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMN */
  public boolean setPlayers(Player... newPlayers)
  {
    boolean wasSet = false;
    ArrayList<Player> verifiedPlayers = new ArrayList<Player>();
    for (Player aPlayer : newPlayers)
    {
      if (verifiedPlayers.contains(aPlayer))
      {
        continue;
      }
      verifiedPlayers.add(aPlayer);
    }

    if (verifiedPlayers.size() != newPlayers.length || verifiedPlayers.size() < minimumNumberOfPlayers() || verifiedPlayers.size() > maximumNumberOfPlayers())
    {
      return wasSet;
    }

    players.clear();
    players.addAll(verifiedPlayers);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setBoard(Board aNewBoard)
  {
    boolean wasSet = false;
    if (aNewBoard != null)
    {
      board = aNewBoard;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setEnvelope(Envelope aNewEnvelope)
  {
    boolean wasSet = false;
    if (aNewEnvelope != null)
    {
      envelope = aNewEnvelope;
      wasSet = true;
    }
    return wasSet;
  }

  public void setSuggestion(Call suggestion) {
    this.suggestion = suggestion;
  }

  public Call getSuggestion() {
    return suggestion;
  }

  public void delete()
  {
    stack = null;
    players.clear();
    board = null;
    envelope = null;
  }

}