import java.util.*;

public class Player {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Player Attributes
    private String name;
    private int num;
    private Position position;
    private Room room;

    //Player Associations
    private List<Card> cards;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Player(String aName, int aNum, Position aPosition) {
        name = aName;
        num = aNum;
        position = aPosition;
        room = null;
    }

    //------------------------
    // INTERFACE
    //------------------------


    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public List<Card> getCards() {
        List<Card> newCards = Collections.unmodifiableList(cards);
        return newCards;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public boolean suggest(Call envelope) {
        //TODO: ask user for weapon, room, character and create call to compare to envolope
        return false;
    }

    public boolean accuse(Call envelope) {
        //TODO
        return false;
    }

    public void leaveRoom(){
        if(room == null){return;}
        //TODO: change players position to room exit

        room = null;
    }
}