/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import com.sun.xml.internal.bind.v2.TODO;

import java.util.*;

// line 26 "model.ump"
// line 92 "model.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String name;
  private int num;

  //Player Associations
  private List<Card> cards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aName, int aNum)
  {
    name = aName;
    num = aNum;
  }

  //------------------------
  // INTERFACE
  //------------------------


  public String getName()
  {
    return name;
  }

  public int getNum() {
    return num;
  }

  public List<Card> getCards()
  {
    List<Card> newCards = Collections.unmodifiableList(cards);
    return newCards;
  }

  // line 31 "model.ump"
  public boolean suggest(){
    //TODO
    return false;
  }

  // line 34 "model.ump"
   public boolean accuse(){
    //TODO
    return false;
  }

  // line 37 "model.ump"
   public void move(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}