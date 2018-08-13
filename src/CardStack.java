import java.lang.Character;
import java.util.*;

public class CardStack
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //CardStack Associations
    private List<Card> cards;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public CardStack()
    {
        cards = new ArrayList<Card>();
        //characters:
        cards.add(new GameCharacter("Miss Scarlett"));
        cards.add(new GameCharacter("Colonel Mustard"));
        cards.add(new GameCharacter("Mrs White"));
        cards.add(new GameCharacter("Mr Green"));
        cards.add(new GameCharacter("Mrs Peacock"));
        cards.add(new GameCharacter("Professor Plum"));
        //weapons:
        cards.add(new Weapon("Candlestick"));
        cards.add(new Weapon("Dagger"));
        cards.add(new Weapon("Lead Pipe"));
        cards.add(new Weapon("Revolver"));
        cards.add(new Weapon("Rope"));
        cards.add(new Weapon("Spanner"));
        //rooms:
        cards.add(new Room("Kitchen"));
        cards.add(new Room("Ballroom"));
        cards.add(new Room("Conservatory"));
        cards.add(new Room("Dining room"));
        cards.add(new Room("Lounge"));
        cards.add(new Room("Billiard room"));
        cards.add(new Room("Library"));
        cards.add(new Room("Study"));
        cards.add(new Room("Hall"));
    }

    //------------------------
    // INTERFACE
    //------------------------

    public Weapon getWeaponCard(){
        List<Weapon> weapons = new ArrayList<>();
        for (Card c: cards) {
            if(c instanceof Weapon){weapons.add((Weapon)c);}
        }
        //return null if no weapons in deck
        if(weapons.isEmpty()){return null;}
        //otherwise choose a random weapon
        Random rand = new Random();
        int RandIndex = rand.nextInt(weapons.size());
        return weapons.get(RandIndex);
    }

    public GameCharacter getCharacterCard(){
        List<GameCharacter> characters = new ArrayList<>();
        for (Card c: cards) {
            if(c instanceof GameCharacter){characters.add((GameCharacter)c);}
        }
        //return null if no characters in deck
        if(characters.isEmpty()){return null;}
        //otherwise choose a random character
        Random rand = new Random();
        int RandIndex = rand.nextInt(characters.size());
        return characters.get(RandIndex);
    }

    public Room getRoomCard(){
        List<Room> rooms = new ArrayList<>();
        for (Card c: cards) {
            if(c instanceof Room){rooms.add((Room)c);}
        }
        //return null if no characters in deck
        if(rooms.isEmpty()){return null;}
        //otherwise choose a random character
        Random rand = new Random();
        int RandIndex = rand.nextInt(rooms.size());
        return rooms.get(RandIndex);
    }

    public Card getRandCard(){
        //return null if no more cards to deal
        if(cards.isEmpty()){return null;}
        //otherwise return a random card
        Random rand = new Random();
        int RandIndex = rand.nextInt(cards.size());
        return cards.get(RandIndex);
    }

    public List<Card> getCards() {
        return cards;
    }
}