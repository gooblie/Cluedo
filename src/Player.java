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

    public Call suggest(Game game, Call call) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select a character to suggest:");
        for (int j = 0; j < game.getPlayers().size(); j++) {
            System.out.println(j+": "+game.getPlayers().get(j).getName());
        }
        int playerNumber = -1;
        while(0 > playerNumber || playerNumber > game.getPlayers().size()){
            String input = scan.nextLine();
            playerNumber = Integer.parseInt(input);
        }
        String playerName = game.getPlayers().get(playerNumber).getName();
        CharacterCard charSuggestion = new CharacterCard(playerName);

        System.out.println("Please select a weapon to suggest:");
        for (int j = 0; j < game.getWeapons().size(); j++) {
            System.out.println(j+": "+game.getWeapons().get(j).getName());
        }
        int weaponNumber = -1;
        while(0 > weaponNumber || weaponNumber > game.getWeapons().size()){
            String input = scan.nextLine();
            weaponNumber = Integer.parseInt(input);
        }
        String weapName = game.getWeapons().get(weaponNumber).getName();
        WeaponCard weapSuggestion = new WeaponCard(weapName);

        //check if weapon is in room, moving it to room if not
        if(game.getWeapons().get(weaponNumber).getRoom() != this.getRoom()){
            this.getRoom().addWeapon(game.getWeapons().get(weaponNumber));
        }

        //check if player is in room, moving it to room if not
        if(game.getPlayers().get(playerNumber).getRoom() != this.getRoom()){
            this.getRoom().addPlayer(game.getPlayers().get(playerNumber));
        }

        RoomCard roomSuggestion = new RoomCard(this.getRoom().getName());
        return new Call(weapSuggestion, roomSuggestion, charSuggestion);
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