import java.util.*;

public class Game
{

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

  public Game()
  {
    initGame();
  }

  //------------------------
  // METHODS
  //------------------------

  public void initGame(){
    turn = 1;
    players = new ArrayList<>();
  }

  public void doTurn(){

  }

  public void doWin(Player player){
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

  public static void main(String[] args){
    Game game = new Game();
    while(!game.isWon()){
      game.doTurn();
    }
  }

}