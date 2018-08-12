/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.*;

// line 26 "model.ump"
// line 86 "model.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Associations
  private List<Card> cards;
  private Squares location;
  private Room currentRoom;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(Squares spawnSquare, Game game)
  {
    cards = new ArrayList<Card>();
    this.location = spawnSquare;
    this.currentRoom = Board.getRoom(this.location);
    this.game = game;
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Card getCard(int index)
  {
    Card aCard = cards.get(index);
    return aCard;
  }

  public List<Card> getCards()
  {
    List<Card> newCards = Collections.unmodifiableList(cards);
    return newCards;
  }

  public int numberOfCards()
  {
    int number = cards.size();
    return number;
  }

  public boolean hasCards()
  {
    boolean has = cards.size() > 0;
    return has;
  }

  public int indexOfCard(Card aCard)
  {
    int index = cards.indexOf(aCard);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCards()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCard(Card aCard)
  {
    if (cards.contains(aCard)) { return false; }
    cards.add(aCard);
    return true;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCard(Card aCard)
  {
    boolean wasRemoved = false;
    if (!cards.contains(aCard)) { return false; }
    cards.remove(cards.indexOf(aCard));
    return true;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCardAt(Card aCard, int index)
  {  
    boolean wasAdded = false;
    if(addCard(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      cards.remove(aCard);
      cards.add(index, aCard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCardAt(Card aCard, int index)
  {
    boolean wasAdded = false;
    if(cards.contains(aCard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCards()) { index = numberOfCards() - 1; }
      cards.remove(aCard);
      cards.add(index, aCard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCardAt(aCard, index);
    }
    return wasAdded;
  }

  public boolean isValidMove(){return true;} //todo when grid programmed

  public void move(String dir, int steps){
    if(isValidMove()){
      switch(dir){
        case "North": this.location.setY(this.location.getY() + steps);
        case "East": this.location.setX(this.location.getX() + steps);
        case "South": this.location.setY(this.location.getY() - steps);
        case "West": this.location.setX(this.location.getX() - steps);
      }
      this.currentRoom = Board.getRoom(this.location);
    }
  }

  public boolean suggest(Character c, Weapon w){
    if(!inRoom())return false;
    this.game.setSuggestion(new Call(currentRoom, c, w));
    if(c.getRoom() != currentRoom)c.setRoom(currentRoom);
    if(w.getRoom() != currentRoom)w.setRoom(currentRoom);
    return true;
  }

  public Card refute(Card card){
    if(!game.getSuggestion().contains(card))return null;
    return card;
  }

  public boolean inRoom(){
    return(this.currentRoom != null);
  }
}