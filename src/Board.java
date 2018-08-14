import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Character;
import java.util.*;

public class Board
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum Direction { NORTH, EAST, SOUTH, WEST }

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Board Attributes
    private char board[][];
    private Map<String, Position> startPositions;

    //Board Associations
    private List<Room> rooms;
    private Game game;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Board(String fileName, Game game)
    {
        this.game = game;
        board = new char[25][24];
        File boardFile = new File(fileName);
        try {
            Scanner scanner = new Scanner(boardFile);
            for (int i = 0; i < 25; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < 24; j++) {
                    board[i][j] = line.charAt(j);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //TODO: initialise startPositions map

        startPositions.put("Mrs. White", new Position(9, 0));
        startPositions.put("Mr. Green", new Position(14, 0));
        startPositions.put("Mrs. Peacock", new Position(23, 6));
        startPositions.put("Prof. Plum", new Position(23, 19));
        startPositions.put("Miss Scarlett", new Position(7, 24));
        startPositions.put("Col. Mustard", new Position(0, 17));

        //TODO: initialise room objects with names and positions of entrances
    }

    //------------------------
    // INTERFACE
    //------------------------


    public List<Room> getRooms()
    {
        List<Room> newRooms = Collections.unmodifiableList(rooms);
        return newRooms;
    }

    public void removeUnecessaryPlayers(int players){
        for (int i = players+1; i <= 6; i++) {
            for (int j = 0; j < 25; j++) {
                for (int k = 0; k < 24; k++) {
                    if(board[j][k] == Character.forDigit(i, 10)){
                        board[j][k] = 'x';
                    }
                }
            }
        }
    }

    public boolean isMoveValid(Player player, Direction dir){
        //TODO:
        return false;
    }

    public Position getStartPosition(String characterName){
        return startPositions.get(characterName);
    }

    public void move(Player player, Direction dir){
        //TODO: change board state to reflect moved player positions
        //TODO: put players in rooms if they're at room positions
    }

    public void print(){
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 24; j++) {
                //TODO print board
            }
        }
    }

}