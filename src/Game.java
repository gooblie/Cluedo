import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.lang.Character;
import java.util.*;

public class Game {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Game Attributes
    private int turn;
    private Call Envelope;
    private boolean won;


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
        new Board("board.txt");

        System.out.println("Welcome to Cluedo!");
        int numOfPlayers = 0;
        while(3 > numOfPlayers || numOfPlayers > 6){
            System.out.println("how many players do you have(3-6)?");
            Scanner read = new Scanner(System.in);
            String input = read.nextLine();
            numOfPlayers = Integer.parseInt(input);
        }
        List<GameCharacter> characters = new ArrayList<>();
        characters.add(new GameCharacter("Miss Scarlett"));
        characters.add(new GameCharacter("Colonel Mustard"));
        characters.add(new GameCharacter("Mrs White"));
        characters.add(new GameCharacter("Mr Green"));
        characters.add(new GameCharacter("Mrs Peacock"));
        characters.add(new GameCharacter("Professor Plum"));
        for (int i = 0; i < numOfPlayers; i++) {

        }

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
        return Envelope;
    }

    public boolean isWon() {
        return won;
    }

    public CardStack getCardStack() {
        return cardStack;
    }

    public static void main(String[] args) {
        Game game = new Game();
        while (!game.isWon()) {
            game.doTurn();
        }
    }

}