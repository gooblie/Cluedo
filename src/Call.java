/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/



// line 52 "model.ump"
// line 111 "model.ump"
public class Call
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Call Associations
  private Weapon weapon;
  private Room room;
  private Character character;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Call(Weapon aWeapon, Room aRoom, Character aCharacter)
  {
    if (!setWeapon(aWeapon))
    {
      throw new RuntimeException("Unable to create Call due to aWeapon");
    }
    if (!setRoom(aRoom))
    {
      throw new RuntimeException("Unable to create Call due to aRoom");
    }
    if (!setCharacter(aCharacter))
    {
      throw new RuntimeException("Unable to create Call due to aCharacter");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Weapon getWeapon()
  {
    return weapon;
  }
  /* Code from template association_GetOne */
  public Room getRoom()
  {
    return room;
  }
  /* Code from template association_GetOne */
  public Character getCharacter()
  {
    return character;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setWeapon(Weapon aNewWeapon)
  {
    boolean wasSet = false;
    if (aNewWeapon != null)
    {
      weapon = aNewWeapon;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setRoom(Room aNewRoom)
  {
    boolean wasSet = false;
    if (aNewRoom != null)
    {
      room = aNewRoom;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setCharacter(Character aNewCharacter)
  {
    boolean wasSet = false;
    if (aNewCharacter != null)
    {
      character = aNewCharacter;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    weapon = null;
    room = null;
    character = null;
  }

}