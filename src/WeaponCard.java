public class WeaponCard extends Card {

    //------------------------
    // MEMBER VARIABLES
    //------------------------
    public Room room;
    //------------------------
    // CONSTRUCTOR
    //------------------------

    public WeaponCard(String aName) {
        super(aName);
    }

    //------------------------
    // INTERFACE
    //------------------------


    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void delete() {
        super.delete();
    }


}