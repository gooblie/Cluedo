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

        startPositions.put("Mrs. White", new Position(9, 0));
        startPositions.put("Mr. Green", new Position(14, 0));
        startPositions.put("Mrs. Peacock", new Position(23, 6));
        startPositions.put("Prof. Plum", new Position(23, 19));
        startPositions.put("Miss Scarlett", new Position(7, 24));
        startPositions.put("Col. Mustard", new Position(0, 17));

        rooms = new ArrayList<>();

        //Construct Kitchen
        ArrayList<Position> kitchenEntrances = new ArrayList<>();
        kitchenEntrances.add(new Position(4, 6));
        Position kitchenExit = new Position(4, 7);
        rooms.add(new Room("Kitchen", kitchenEntrances, kitchenExit));

        //Construct Ball Room
        ArrayList<Position> ballRoomEntrances = new ArrayList<>();
        ballRoomEntrances.add(new Position(8, 5));
        ballRoomEntrances.add(new Position(15, 5));
        ballRoomEntrances.add(new Position(9, 7));
        ballRoomEntrances.add(new Position(14, 7));
        Position ballRoomExit = new Position(14, 8);
        rooms.add(new Room("Ball Room", ballRoomEntrances, ballRoomExit));

        //Construct Conservatory
        ArrayList<Position> conservatoryEntrances = new ArrayList<>();
        conservatoryEntrances.add(new Position(18, 4));
        Position conservatoryExit = new Position(17, 4);
        rooms.add(new Room("Conservatory", conservatoryEntrances, conservatoryExit));

        //Construct Dining Room
        ArrayList<Position> diningRoomEntrances = new ArrayList<>();
        diningRoomEntrances.add(new Position(7, 12));
        diningRoomEntrances.add(new Position(6, 15));
        Position diningRoomExit = new Position(6, 16);
        rooms.add(new Room("Dining Room", diningRoomEntrances, diningRoomExit));

        //Construct Billiard Room
        ArrayList<Position> billiardRoomEntrances = new ArrayList<>();
        billiardRoomEntrances.add(new Position(18, 9));
        billiardRoomEntrances.add(new Position(22, 12));
        Position billiardRoomExit = new Position(17, 9);
        rooms.add(new Room("Billiard Room", billiardRoomEntrances, billiardRoomExit));

        //Construct Library
        ArrayList<Position> libraryEntrances = new ArrayList<>();
        libraryEntrances.add(new Position(20, 14));
        libraryEntrances.add(new Position(17, 16));
        Position libraryExit = new Position(16, 16);
        rooms.add(new Room("Library", libraryEntrances, libraryExit));

        //Construct Lounge
        ArrayList<Position> loungeEntrances = new ArrayList<>();
        loungeEntrances.add(new Position(6, 19));
        Position loungeExit = new Position(6, 18);
        rooms.add(new Room("Lounge", loungeEntrances, loungeExit));

        //Construct Hall
        ArrayList<Position> hallEntrances = new ArrayList<>();
        hallEntrances.add(new Position(11, 18));
        hallEntrances.add(new Position(12, 18));
        hallEntrances.add(new Position(14, 20));
        Position hallExit = new Position(15, 20);
        rooms.add(new Room("Hall", hallEntrances, hallExit));

        //Construct Study
        ArrayList<Position> studyEntrances = new ArrayList<>();
        studyEntrances.add(new Position(17, 21));
        Position studyExit = new Position(17, 20);
        rooms.add(new Room("Study", studyEntrances, studyExit));
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