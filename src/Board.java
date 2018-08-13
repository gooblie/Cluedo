import java.io.File;
import java.io.FileNotFoundException;
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

    //Board Associations
    private List<Room> rooms;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Board(String fileName)
    {
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
    }

    //------------------------
    // INTERFACE
    //------------------------


    public List<Room> getRooms()
    {
        List<Room> newRooms = Collections.unmodifiableList(rooms);
        return newRooms;
    }



    public boolean isMoveValid(Player player, Direction dir){
        return false;
    }

}