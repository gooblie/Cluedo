/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.*;

// line 47 "model.ump"
// line 109 "model.ump"
public class Room extends Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  private String name;
  private Squares location;
  private String type;
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Room (String name) {
    super(name);
    this.name = name;
    this.type = "Room";
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
}