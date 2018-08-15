import java.io.IOException;
import java.util.*;

public class Game {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Game Attributes
    private int turn;
    private Call envelope;
    private boolean won;
    private List<CharacterCard> characters;
    private List<RoomCard> rooms;
    public List<WeaponCard> weapons;
    private Scanner scan;

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
        board = new Board("board.txt", this);
        scan = new Scanner(System.in);

        //characters:
        characters = new ArrayList<>();
        characters.add(new CharacterCard("Miss Scarlett"));
        characters.add(new CharacterCard("Col. Mustard"));
        characters.add(new CharacterCard("Mrs. White"));
        characters.add(new CharacterCard("Mr. Green"));
        characters.add(new CharacterCard("Mrs. Peacock"));
        characters.add(new CharacterCard("Prof. Plum"));
        //weapons:
        weapons = new ArrayList<>();
        weapons.add(new WeaponCard("Candlestick"));
        weapons.add(new WeaponCard("Dagger"));
        weapons.add(new WeaponCard("Lead Pipe"));
        weapons.add(new WeaponCard("Revolver"));
        weapons.add(new WeaponCard("Rope"));
        weapons.add(new WeaponCard("Spanner"));
        //roomCards:
        rooms = new ArrayList<>();
        rooms.add(new RoomCard("Kitchen"));
        rooms.add(new RoomCard("Ballroom"));
        rooms.add(new RoomCard("Conservatory"));
        rooms.add(new RoomCard("Dining room"));
        rooms.add(new RoomCard("Lounge"));
        rooms.add(new RoomCard("Billiard room"));
        rooms.add(new RoomCard("Library"));
        rooms.add(new RoomCard("Study"));
        rooms.add(new RoomCard("Hall"));

        cardStack = new CardStack(this);

        //init envelope:
        envelope = new Call(cardStack.getWeaponCard(), cardStack.getRoomCard(), cardStack.getCharacterCard());

        //select players:
        System.out.println("Welcome to Cluedo!");
        int numOfPlayers = 0;
        board.initPlayerStart();
        while(3 > numOfPlayers || numOfPlayers > 6){
            System.out.println("how many players do you have? (3-6)");

            String input = scan.nextLine();
            numOfPlayers = Integer.parseInt(input);
        }
        for (int i = 1; i <= numOfPlayers; i++) {
            System.out.println("Player "+(i)+" - Please select a name:");
            for (int j = 0; j < characters.size(); j++) {
                System.out.println(j+": "+characters.get(j).getName());
            }
            int characterNumber = -1;
            while(0 > characterNumber || characterNumber > characters.size()){
                String input = scan.nextLine();
                characterNumber = Integer.parseInt(input);
            }
            String name = characters.get(characterNumber).getName();
            characters.remove(characterNumber);
            Player player = new Player(name, i, board.getStartPosition(name));
            players.add(player);
        }
        board.initBoardPlayerStart();
        board.initRooms();
        board.initWeapons(weapons);

        //deal hand to players:
        int i = 0;
        while(cardStack.hasCard()){
            if(i>=players.size()){i=0;}
            players.get(i++).addCard(cardStack.getRandCard());
        }
        this.board.print();
        int t = 0;
        while(!isWon()) {
            if(t>=players.size()){t=0;}
            doTurn(players.get(t++));
        }
    }

    public void initTurn(){
    }

    public void doTurn(Player player) {
        Random rand = new Random();
        int dice1 = rand.nextInt(7)+1;
        int dice2 = rand.nextInt(7)+1;
        int roll = dice1+dice2;
        int moves = roll;
        System.out.println("Player " + player.getNum() + ": It is your turn and you are currently in the corridors!");
        System.out.println("You rolled a " + roll + "!");
        for (int i = 0; i < roll ; i++) {
            //if a player is in a room then the only way they can move is exit
            if(player.getRoom() != null){
                System.out.println("You are in the "+player.getRoom().getName());
                System.out.println("0: Make a suggestion");
                System.out.println("1: Leave the room");
                int answer = -1;
                while(0 > answer || answer > 1){
                    String input = scan.next();
                    answer = Integer.parseInt(input);
                }
                if(answer == 1){
                    player.leaveRoom();
                    board.print();
                }
                if(answer == 0){
                    Call suggestion = player.suggest(this, envelope);
                    System.out.println("A suggestion has been made!");
                    System.out.println("Player " + player.getNum() + " is suggesting that " + suggestion.getCharacter().getName()
                    + " did the murder with a " + suggestion.getWeaponCard().getName() + " in the " + suggestion.getRoom().getName() + "!");
                    System.out.println("It is now each players turn to refute this suggestion!");
                    break;
                }
            }
            //TODO: ASK WHAT DIRECTION THEY SHOULD MOVE IN AND MOVE IN THAT DIR
            System.out.println("You have " + moves + " moves!");
            System.out.println("Which direction do you want to move?");
            System.out.println("0: North");
            System.out.println("1: East");
            System.out.println("2: South");
            System.out.println("3: West");
            int answer = -1;
            while (0 > answer || answer > 3) {
                String input = scan.next();
                answer = Integer.parseInt(input);

            }
            switch (answer) {
                case 0:
                    board.move(player, Board.Direction.NORTH);
                    moves--;
                    break;
                case 1:
                    board.move(player, Board.Direction.EAST);
                    moves--;
                    break;
                case 2:
                    board.move(player, Board.Direction.SOUTH);
                    moves--;
                    break;
                case 3:
                    board.move(player, Board.Direction.WEST);
                    moves--;
                    break;
            }
        }
        //TODO: AT THE END OF TURN ASK IF THEY WANT TO MAKE AN ACCUSATION
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
        return rooms;
    }

    public List<WeaponCard> getWeapons() {
        return weapons;
    }

    public static void main(String[] args) {
        Game game = new Game();
        while (!game.isWon()) {
            //TODO: determine which players turn it is and doTurn(Player)
        }
        game.scan.close();
    }
}