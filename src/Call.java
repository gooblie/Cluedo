import java.util.Objects;

public class Call
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Call Associations
    private WeaponCard weaponCard;
    private RoomCard room;
    private CharacterCard character;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Call(WeaponCard aWeaponCard, RoomCard aRoom, CharacterCard aCharacter)
    {
        if (!setWeapon(aWeaponCard))
        {
            throw new RuntimeException("Unable to create Call due to aWeaponCard");
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
    public WeaponCard getWeaponCard()
    {
        return weaponCard;
    }

    /* Code from template association_GetOne */
    public RoomCard getRoom()
    {
        return room;
    }

    /* Code from template association_GetOne */
    public CharacterCard getCharacter()
    {
        return character;
    }

    /* Code from template association_SetUnidirectionalOne */
    public boolean setWeapon(WeaponCard aNewWeaponCard)
    {
        boolean wasSet = false;
        if (aNewWeaponCard != null)
        {
            weaponCard = aNewWeaponCard;
            wasSet = true;
        }
        return wasSet;
    }
    /* Code from template association_SetUnidirectionalOne */
    public boolean setRoom(RoomCard aNewRoom)
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
    public boolean setCharacter(CharacterCard aNewCharacter)
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
        return Objects.equals(weaponCard, call.weaponCard) &&
                Objects.equals(room, call.room) &&
                Objects.equals(character, call.character);
    }

    public boolean contains(Card c) {
        if (c == null) return false;
        return weaponCard.equals(c) ||
                room.equals(c) ||
                character.equals(c);
    }

    @Override
    public int hashCode() {

        return Objects.hash(weaponCard, room, character);
    }
}