import java.util.*;

public class Game {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Game Attributes
    private int turn;
    private Call envelope;
    private List<Call> suggestions;
    private boolean won;
    private List<CharacterCard> characters;
    private List<RoomCard> rooms;
    private List<WeaponCard> weapons;
    private Scanner scan;
    private ArrayList<Player> allPlayers;
    private int roll;
    private int moves;

    //Game Associations
    private Board board;
    private CardStack cardStack;
    private List<Player> playersInGame;
    private GUI GUI;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Game(GUI GUI) {
        this.GUI = GUI;
        initFields();
        //initPlayers();
        board.initRooms();
        board.initWeapons(weapons);
        //dealHand();
        board.print();
    }

    //------------------------
    // METHODS
    //------------------------

    public void initFields() {
        turn = 1;
        playersInGame = new ArrayList<>();
        allPlayers = new ArrayList<>();
        suggestions = new ArrayList<>();
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

        //cardStack
        cardStack = new CardStack(this);

        //init envelope:
        envelope = new Call(cardStack.getWeaponCard(), cardStack.getRoomCard(), cardStack.getCharacterCard());
    }

//    public void initPlayers() {
//        //select playersInGame:
//        System.out.println("Welcome to Cluedo!");
//        int numOfPlayers = 0;
//        board.initPlayerStart();
//        while(3 > numOfPlayers || numOfPlayers > 6){
//            System.out.println("How many players do you have? (3-6)");
//
//            String input = scan.nextLine();
//            numOfPlayers = Integer.parseInt(input);
//        }
//        List<CharacterCard> charactersLeft = new ArrayList<>();
//        charactersLeft.addAll(characters);
//        for (int i = 1; i <= numOfPlayers; i++) {
//            System.out.println("Player "+(i)+" - Please select a name:");
//            for (int j = 0; j < charactersLeft.size(); j++) {
//                System.out.println(j+": "+charactersLeft.get(j).getName());
//            }
//            int characterNumber = -1;
//            while(0 > characterNumber || characterNumber > charactersLeft.size()){
//                String input = scan.nextLine();
//                characterNumber = Integer.parseInt(input);
//            }
//            String name = charactersLeft.get(characterNumber).getName();
//            charactersLeft.remove(characterNumber);
//            Player player = new Player(name, i, board.getStartPosition(name));
//            playersInGame.add(player);
//        }
//        allPlayers.addAll(playersInGame);
//        board.initBoardPlayerStart();
//    }

    public void dealHand(){
        //deal hand to playersInGame:
        int i = 0;
        while(cardStack.hasCard()){
            if(i>= playersInGame.size()){i=0;}
            playersInGame.get(i++).addCard(cardStack.getRandCard());
        }
    }

    public void gameLoop(){
        int t = 0;
        while(!isWon()) {
            if(t>= playersInGame.size()){t=0;}
            if(!playersInGame.isEmpty()){
                doTurn(playersInGame.get(t++));
            }
            else{
                System.out.println("All players have been eliminated from making accusations!");
                System.out.println("Who dunnit? " + envelope.getCharacter().getName() + " used a " +
                        envelope.getWeaponCard().getName() + " in the " + envelope.getRoom().getName() + "!");

                System.out.println("Thanks for playing!");
            }
        }
        scan.close();
    }

    public void doTurn(Player player) {
        Random rand = new Random();
        int dice1 = rand.nextInt(7)+1;
        int dice2 = rand.nextInt(7)+1;
        roll = dice1+dice2;
        moves = roll;
        if(checkSuggestions(player)) return;
        if(player.getRoom() == null) {
            System.out.println("Player " + player.getNum() + ": Your current cards are:");
            for (Card card : player.getCards()) {
                System.out.println(card.getName());
            }
            System.out.println();
            System.out.println("It is your turn and you are currently in the corridors!");
            System.out.println("You rolled a " + roll + "!");
        }
        System.out.println();
        for (int i = 0; i < roll ; i++) {
            //if a player is in a room then the only way they can move is exit
            if (player.getRoom() != null){
                System.out.println("Player " + player.getNum() + ": You are in the " + player.getRoom().getName());
                System.out.println();
                System.out.println("Your current cards are: ");
                for (Card card : player.getCards()) {
                    System.out.println(card.getName());
                }
                System.out.println();
                System.out.println("0: Make a suggestion");
                System.out.println("1: Leave the room");
                int answer = -1;
                while (0 > answer || answer > 1) {
                    String input = scan.next();
                    answer = Integer.parseInt(input);
                }
                if (answer == 1) {
                    player.leaveRoom();
                    board.setPlayerPosition(player);
                    moves--;
                    board.print();
                    continue;
                }
                Call suggestion = makeSuggestion(player);
                refuteLoop(player, suggestion);
                return;
            }
            getMoves(player);
        }
    }

    public boolean checkSuggestions(Player player){
        if (!suggestions.isEmpty()) {
            System.out.println("One or more unrefuted suggestions have been made!");
            for(int s = 1; s <= suggestions.size(); s++) {
                System.out.println("Suggestion " + s + ": " + suggestions.get(s-1).getCharacter().getName()
                        + " did the murder with a " + suggestions.get(s-1).getWeaponCard().getName() + " in the " + suggestions.get(s-1).getRoom().getName() + ".");
            }
            System.out.println();
            System.out.println("Player " + player.getNum() + ": Would you like to make an accusation?");
            System.out.println("0: Yes");
            System.out.println("1: No");
            int accusation = -1;
            while (0 > accusation || accusation > 1) {
                String input = scan.next();
                accusation = Integer.parseInt(input);
            }
            if (accusation == 0) {
                Call accuse = player.accuse(this);
                if (accuse.equals(envelope)) {
                    doWin(player);
                } else {
                    playersInGame.remove(player);
                    System.out.println("Incorrect accusation! You lose!");
                    return true;
                }
            }
        }
        return false;
    }

    public Call makeSuggestion(Player player){
        Call suggestion = player.suggest(this);
        System.out.println("A suggestion has been made!");
        System.out.println("Player " + player.getNum() + " is suggesting that " + suggestion.getCharacter().getName()
                + " did the murder with a " + suggestion.getWeaponCard().getName() + " in the " + suggestion.getRoom().getName() + "!");
        return suggestion;
    }

    public void refuteLoop(Player player, Call suggestion){
        System.out.println("It is now each players turn to refute this suggestion!");
        System.out.println();
        Map<Player, Card> refuters = new HashMap<>();
        for (Player other : allPlayers) {
            if (other != player) {
                System.out.println("Player " + other.getNum() + ": Do you want to choose a card from your hand to refute the suggestion?");
                System.out.println("0: No refute");
                int j = 1;
                for (Card card : other.getCards()) {
                    System.out.println(j++ + ": " + card.getName());
                }
                int answer = -1;
                while (0 > answer || answer > other.getCards().size()) {
                    String input = scan.next();
                    answer = Integer.parseInt(input);
                }
                if (answer == 0) {
                    continue;
                }
                refuters.put(other, other.getCards().get(answer - 1));
            }
        }
        if (refuters.isEmpty()) {
            System.out.println("No one refuted this suggestion");
            System.out.println();
            suggestions.add(suggestion);
        } else {
            for (Player p : refuters.keySet()) {
                if (suggestion.contains(refuters.get(p))) {
                    System.out.println("(For player " + player.getNum() + "s eyes only!): Player " + p.getNum() + " refuted the suggestion with the card: " + refuters.get(p).getName());
                    System.out.println();
                }
            }
        }
    }

    public void getMoves(Player player){
        System.out.println("You have " + moves + " moves!");
        System.out.println("Player " + player.getNum() + ": Which direction do you want to move?");
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

    public void doWin(Player player) {
        won = true;
        System.out.println(player.getName() + " has won!");
        System.out.println("Who dunnit? " + envelope.getCharacter().getName() + " used a " +
                envelope.getWeaponCard().getName() + " in the " + envelope.getRoom().getName() + "!");
        System.out.println("Thanks for playing!");
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

    public List<Player> getPlayersInGame() {
        return playersInGame;
    }

    public List<Player> getAllPlayers(){
        return allPlayers;
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

}