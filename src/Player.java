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
        cards = new ArrayList<>();
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

    public void addCard(Card card){
        cards.add(card);
    }

    public boolean suggest(Call call) {
        //TODO
        return false;
    }

    public boolean accuse(Call call) {
        //TODO
        return false;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void leaveRoom(){
        this.setPosition(this.room.getExit());
        this.room.removePlayer(this);
        this.setRoom(null);
    }

    public void move() {

    }

}