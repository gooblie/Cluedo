public class CharacterCard extends Card
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------
    private Room room;
    //------------------------
    // CONSTRUCTOR
    //------------------------

    public CharacterCard(String aName)
    {
        super(aName);
    }

    //------------------------
    // INTERFACE
    //------------------------


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void delete()
    {
        super.delete();
    }

}