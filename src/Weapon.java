/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.*;

// line 42 "model.ump"
// line 104 "model.ump"
public class Weapon extends Card
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------
  private String name;
  private Squares location;
  private String type;
  private Room room;
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Weapon(String name) {
    super(name);
    this.name = name;
    this.type = "Weapon";
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