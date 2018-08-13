import java.util.*;

public class Game {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Game Attributes
    private int turn;
    private Call envelope;
    private boolean won;
    List<CharacterCard> characters;
    List<RoomCard> roomCards;
    List<WeaponCard> weapons;


    //Game Associations
    private Board board;
    private CardStack cardStack;
    private List<Player> players;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Game() {
        initGame();
    }

    //------------------------
    // METHODS
    //------------------------

    public void initGame() {
        turn = 1;
        players = new ArrayList<>();
        board =new Board("board.txt", this);
        cardStack = new CardStack(this);

        //init envelope:
        envelope = new Call(cardStack.getWeaponCard(), cardStack.getRoomCard(), cardStack.getCharacterCard());
        System.out.println("Welcome to Cluedo!");

        //select players:
        int numOfPlayers = 0;
        while(3 > numOfPlayers || numOfPlayers > 6){
            System.out.println("how many players do you have? (3-6)");
            Scanner read = new Scanner(System.in);
            String input = read.nextLine();
            numOfPlayers = Integer.parseInt(input);
        }

        characters = new ArrayList<>();
        characters.add(new CharacterCard("Miss Scarlett"));
        characters.add(new CharacterCard("Colonel Mustard"));
        characters.add(new CharacterCard("Mrs White"));
        characters.add(new CharacterCard("Mr Green"));
        characters.add(new CharacterCard("Mrs Peacock"));
        characters.add(new CharacterCard("Professor Plum"));
        for (int i = 1; i <= numOfPlayers; i++) {
            System.out.println("Player "+(i)+" - Please select a name:");
            for (int j = 0; j < characters.size(); j++) {
                System.out.println(j+": "+characters.get(j).getName());
            }
            int characterNumber = -1;
            while(0 > characterNumber || characterNumber > characters.size()){
                Scanner read = new Scanner(System.in);
                String input = read.nextLine();
                characterNumber = Integer.parseInt(input);
            }
            String name = characters.get(characterNumber).getName();
            characters.remove(characterNumber);
            Player player = new Player(name, i);
            players.add(player);
        }

        //deal hand to players:
        int i = 0;
        while(cardStack.hasCard()){
            if(i>=players.size()){i=0;}
            players.get(i++).addCard(cardStack.getRandCard());
        }

        //weapons:
        weapons.add(new WeaponCard("Candlestick"));
        weapons.add(new WeaponCard("Dagger"));
        weapons.add(new WeaponCard("Lead Pipe"));
        weapons.add(new WeaponCard("Revolver"));
        weapons.add(new WeaponCard("Rope"));
        weapons.add(new WeaponCard("Spanner"));
        //roomCards:
        roomCards.add(new RoomCard("Kitchen"));
        roomCards.add(new RoomCard("Ballroom"));
        roomCards.add(new RoomCard("Conservatory"));
        roomCards.add(new RoomCard("Dining room"));
        roomCards.add(new RoomCard("Lounge"));
        roomCards.add(new RoomCard("Billiard room"));
        roomCards.add(new RoomCard("Library"));
        roomCards.add(new RoomCard("Study"));
        roomCards.add(new RoomCard("Hall"));

    }

    public void doTurn() {

    }

    public void doWin(Player player) {
        won = true;
    }

    public Board getBoard() {
        return board;
    }

    public Call getEnvelope() {
        return envelope;
    }

    public boolean isWon() {
        return won;
    }

    public CardStack getCardStack() {
        return cardStack;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<CharacterCard> getCharacters() {
        return characters;
    }

    public List<RoomCard> getRoomCards() {
        return roomCards;
    }

    public List<WeaponCard> getWeapons() {
        return weapons;
    }

    public static void main(String[] args) {
        Game game = new Game();
        while (!game.isWon()) {
            game.doTurn();
        }
    }

}