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

    // line 31 "model.ump"
    public boolean suggest(Call call) {
        //TODO
        return false;
    }

    // line 34 "model.ump"
    public boolean accuse(Call call) {
        //TODO
        return false;
    }

    // line 37 "model.ump"
    public void move() {

    }

}