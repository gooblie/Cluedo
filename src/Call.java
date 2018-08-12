import java.lang.reflect.Array;
import java.util.ArrayList;

public class Call {
    private Room room;
    private Character character;
    private Weapon weapon;
    private ArrayList<Card> circumstance;

    //suggestion & accusation constructor
    public Call(Room r, Character c, Weapon w){
        this.room = r;
        this.character = c;
        this.weapon = w;
        this.circumstance.add(r);
        this.circumstance.add(c);
        this.circumstance.add(w);
    }

    //refutation constructor
    public Call(Card card){
        if(card instanceof Room)this.room = (Room) card;
        if(card instanceof Character)this.character = (Character) card;
        if(card instanceof Weapon)this.weapon = (Weapon) card;
        this.circumstance.add(card);
    }

    public Room getRoom() {
        return room;
    }

    public Character getCharacter() {
        return character;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean contains(Card card){
        return(circumstance.contains(card));
    }
}
