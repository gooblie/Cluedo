import java.util.Objects;

public class Call
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Call Associations
    private Weapon weapon;
    private Room room;
    private GameCharacter character;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Call(Weapon aWeapon, Room aRoom, GameCharacter aCharacter)
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
    public GameCharacter getCharacter()
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
    public boolean setCharacter(GameCharacter aNewCharacter)
    {
        boolean wasSet = false;
        if (aNewCharacter != null)
        {
            character = aNewCharacter;
            wasSet = true;
        }
        return wasSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Call call = (Call) o;
        return Objects.equals(weapon, call.weapon) &&
                Objects.equals(room, call.room) &&
                Objects.equals(character, call.character);
    }

    @Override
    public int hashCode() {

        return Objects.hash(weapon, room, character);
    }
}