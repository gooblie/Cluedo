import java.util.*;

public class CardStack
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //CardStack Associations
    private List<Card> cards;
    private Game game;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public CardStack(Game game)
    {
        this.game = game;
        cards = new ArrayList<Card>();
        //characters:
        cards.addAll(this.game.getCharacters());
        //weapons:
        cards.addAll(this.game.getWeapons());
        //roomCards:
        cards.addAll(this.game.getRoomCards());
    }

    //------------------------
    // INTERFACE
    //------------------------

    public WeaponCard getWeaponCard(){
        List<WeaponCard> weaponCards = new ArrayList<>();
        for (Card c: cards) {
            if(c instanceof WeaponCard){
                weaponCards.add((WeaponCard)c);}
        }
        //return null if no weapons in deck
        if(weaponCards.isEmpty()){return null;}
        //otherwise choose a random weaponCard
        Random rand = new Random();
        int RandIndex = rand.nextInt(weaponCards.size());
        WeaponCard weaponCard = weaponCards.get(RandIndex);
        weaponCards.remove(weaponCard);
        return weaponCard;
    }

    public CharacterCard getCharacterCard(){
        List<CharacterCard> characters = new ArrayList<>();
        for (Card c: cards) {
            if(c instanceof CharacterCard){characters.add((CharacterCard)c);}
        }
        //return null if no characters in deck
        if(characters.isEmpty()){return null;}
        //otherwise choose a random character
        Random rand = new Random();
        int RandIndex = rand.nextInt(characters.size());
        CharacterCard character = characters.get(RandIndex);
        characters.remove(character);
        return character;
    }

    public RoomCard getRoomCard(){
        List<RoomCard> rooms = new ArrayList<>();
        for (Card c: cards) {
            if(c instanceof RoomCard){rooms.add((RoomCard)c);}
        }
        //return null if no characters in deck
        if(rooms.isEmpty()){return null;}
        //otherwise choose a random character
        Random rand = new Random();
        int RandIndex = rand.nextInt(rooms.size());
        RoomCard room = rooms.get(RandIndex);
        rooms.remove(room);
        return room;
    }

    public Card getRandCard(){
        //return null if no more cards to deal
        if(cards.isEmpty()){return null;}
        //otherwise return a random card
        Random rand = new Random();
        int RandIndex = rand.nextInt(cards.size());
        Card card = cards.get(RandIndex);
        cards.remove(card);
        return card;
    }

    public boolean hasCard(){
        return !cards.isEmpty();
    }

    public List<Card> getCards() {
        return cards;
    }
}