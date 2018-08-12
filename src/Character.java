/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.*;

// line 36 "model.ump"
// line 98 "model.ump"
public class Character extends Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  private String name;
  private Squares location;
  private Room room;
  private String type;
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Character(String name) {
    super(name);
    this.name = name;
    this.type = "Character";
  }

  //------------------------
  // INTERFACE
  //------------------------

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  public Squares getLocation() {
    return location;
  }

  public String getType() {
    return type;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }
}