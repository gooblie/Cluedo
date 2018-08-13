import java.util.*;

public class Player {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Player Attributes
    private String name;
    private int num;

    //Player Associations
    private List<Card> cards;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Player(String aName, int aNum) {
        name = aName;
        num = aNum;
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

    public void move() {

    }

}