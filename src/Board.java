import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Character;
import java.util.*;
import java.util.List;

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
    private static final Color KITCHEN_COLOR = Color.orange;
    private static final Color BALLROOM_COLOR = Color.yellow;
    private static final Color CONSERVATORY_COLOR = Color.red;
    private static final Color DININGROOM_COLOR = Color.green;
    private static final Color BILLIARDROOM_COLOR = Color.pink;
    private static final Color LIBRARY_COLOR = Color.cyan;
    private static final Color LOUNGE_COLOR = new Color(0, 100, 250);
    private static final Color HALL_COLOR = new Color(200, 100, 0);
    private static final Color STUDY_COLOR = Color.magenta;
    private static final Color CORRIDOR_COLOR = Color.lightGray;

    //Board Associations
    private List<Room> rooms;
    private List<WeaponCard> weapons;
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
    }

    public void initPlayerStart(){
        startPositions = new HashMap<>();
        startPositions.put("Mrs. White", new Position(9, 0));
        startPositions.put("Mr. Green", new Position(14, 0));
        startPositions.put("Mrs. Peacock", new Position(23, 6));
        startPositions.put("Prof. Plum", new Position(23, 19));
        startPositions.put("Miss Scarlett", new Position(7, 24));
        startPositions.put("Col. Mustard", new Position(0, 17));
    }

    public void initRooms(){
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

    public void initBoardPlayerStart(){
        for(Player p : game.getPlayersInGame()){
           board[getStartPosition(p.getCharacter()).getY()][getStartPosition(p.getCharacter()).getX()] = Character.forDigit(p.getNum(), 10);
        }
    }

    public void initWeapons(List<WeaponCard> weapons){
        this.weapons = weapons;
        List<Room> tempRooms = new ArrayList<>();
        tempRooms.addAll(rooms);
        List<WeaponCard> tempWeapons = new ArrayList<>();
        tempWeapons.addAll(weapons);
        Random rand = new Random();
        while(tempWeapons.size() > 0){
            int randRoomIndex = rand.nextInt(tempRooms.size());
            int randWeaponIndex = rand.nextInt(tempWeapons.size());
            WeaponCard weapon = tempWeapons.get(randWeaponIndex);
            Room room = tempRooms.get(randRoomIndex);
            room.addWeapon(weapon);
            weapon.setRoom(room);
            tempWeapons.remove(weapon);
            tempRooms.remove(room);
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

    public char getCharFromPosition(Position pos){
        return board[pos.getY()][pos.getX()];
    }

    public boolean isMoveValid(Player player, Direction dir){
        Position newPosition = player.getPosition().move(dir);
        if(newPosition.getY() > 24 || newPosition.getX() > 23 || newPosition.getY() < 0 || newPosition.getX() < 0){
            System.out.println("Cannot move out of bounds!");
            System.out.println();
            return false;
        }
        if(getCharFromPosition(newPosition) != ' ' && getCharFromPosition(newPosition) != '*'){
            System.out.println("Can only move to blank spaces or asterisks!");
            System.out.println();
            return false;
        }
        return true;
    }

    public Position getStartPosition(String characterName){
        return startPositions.get(characterName);
    }

    public void setPlayerPosition(Player player){
        board[player.getPosition().getY()][player.getPosition().getX()] = Character.forDigit(player.getNum(), 10);
    }

    public void removePlayer(Player player){
        board[player.getPosition().getY()][player.getPosition().getX()] = ' ';
    }

    public void move(Player player, Direction dir){
        try {
            if (!isMoveValid(player, dir)) {
                return;
            }
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        Position newPosition = player.getPosition().move(dir);
        if(getCharFromPosition(newPosition) == ' ') {
            board[player.getPosition().getY()][player.getPosition().getX()] = ' ';
            board[newPosition.getY()][newPosition.getX()] = Character.forDigit(player.getNum(), 10);
            player.setPosition(newPosition);
            print();
        }
        else{
            board[player.getPosition().getY()][player.getPosition().getX()] = ' ';
            player.setPosition(null);
            for(Room room : rooms){
                for(Position pos : room.getEntrances()) {
                    if (pos.getX() == newPosition.getX() && pos.getY() == newPosition.getY()) {
                        player.setRoom(room);
                        room.addPlayer(player);
                        print();
                    }
                }
            }
        }
    }

    public void print(){
        for (int j = 0; j < 25; j++) {
            System.out.println();
            for (int i = 0; i < 24; i++) {
                System.out.print(board[j][i]);
            }
        }
        System.out.println();
        System.out.println();
    }

    public void draw(GUI gui, Graphics g){
        int rowLength = board[0].length;
        int colLength = board.length;
        int width = (gui.getWidth() / rowLength);
        int height = (gui.getHeight() / colLength);
        //drawing room colors
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 25; j++) {
                switch (board[j][i]) {
                    case 'x':
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(i * width, j * height, width, height);
                        break;
                    case 'K':
                        g.setColor(KITCHEN_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        break;
                    case 'B':
                        g.setColor(BALLROOM_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        break;
                    case 'C':
                        g.setColor(CONSERVATORY_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        break;
                    case 'N':
                        g.setColor(DININGROOM_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        break;
                    case 'I':
                        g.setColor(BILLIARDROOM_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        break;
                    case 'L':
                        g.setColor(LIBRARY_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        break;
                    case 'O':
                        g.setColor(LOUNGE_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        break;
                    case 'H':
                        g.setColor(HALL_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        break;
                    case 'S':
                        g.setColor(STUDY_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        break;
                    case ' ':
                        g.setColor(CORRIDOR_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.drawRect(i * width, j * height, width, height);
                        break;
                    case '*':
                        g.setColor(Color.black);
                        g.drawString("E", i * width + (width / 2), j * height + (height / 2));
                        g.drawRect(i * width, j * height, width, height);
                        break;
                }
            }
        }
        Font roomFont = g.getFont();
        //drawing room names
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 25; j++) {
                if(i == 11 & j == 13) {
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 17));
                    g.setColor(Color.white);
                    g.drawString("CLUEDO", i * width + (width / 2), j * height + (height / 2));
                }
                if(i == 2 & j == 3) {
                    g.setFont(roomFont);
                    g.setColor(Color.black);
                    g.drawString("Kitchen", i * width + (width / 2), j * height + (height / 2));
                }
                if(i == 11 & j == 3) {
                    g.setFont(roomFont);
                    g.setColor(Color.black);
                    g.drawString("Ball Room", i * width + (width / 2), j * height + (height / 2));
                }
                if(i == 20 & j == 3) {
                    g.setFont(roomFont);
                    g.setColor(Color.black);
                    g.drawString("Conservatory", i * width + (width / 2), j * height + (height / 2));
                }
                if(i == 2 & j == 12) {
                    g.setFont(roomFont);
                    g.setColor(Color.black);
                    g.drawString("Dining Room", i * width + (width / 2), j * height + (height / 2));
                }
                if(i == 20 & j == 10) {
                    g.setFont(roomFont);
                    g.setColor(Color.black);
                    g.drawString("Billiard Room", i * width + (width / 2), j * height + (height / 2));
                }
                if(i == 20 & j == 16) {
                    g.setFont(roomFont);
                    g.setColor(Color.black);
                    g.drawString("Library", i * width + (width / 2), j * height + (height / 2));
                }
                if(i == 2 & j == 22) {
                    g.setFont(roomFont);
                    g.setColor(Color.black);
                    g.drawString("Lounge", i * width + (width / 2), j * height + (height / 2));
                }
                if(i == 11 & j == 22) {
                    g.setFont(roomFont);
                    g.setColor(Color.black);
                    g.drawString("Hall", i * width + (width / 2), j * height + (height / 2));
                }
                if(i == 20 & j == 22) {
                    g.setFont(roomFont);
                    g.setColor(Color.black);
                    g.drawString("Study", i * width + (width / 2), j * height + (height / 2));
                }
            }
        }
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 25; j++) {
                switch (board[j][i]) {
                    case '1':
                        g.setColor(CORRIDOR_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.drawRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.fillOval(i * width, j * height, width, height);
                        g.setColor(Color.white);
                        g.drawString("1", i * width + ((width - metrics.stringWidth("1")) / 2) + 1, j * height + ((height - metrics.getHeight()) / 2) + metrics.getAscent());
                        break;
                    case '2':
                        g.setColor(CORRIDOR_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.drawRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.fillOval(i * width, j * height, width, height);
                        g.setColor(Color.white);
                        g.drawString("2", i * width + ((width - metrics.stringWidth("2")) / 2) + 1, j * height + ((height - metrics.getHeight()) / 2) + metrics.getAscent());
                        break;
                    case '3':
                        g.setColor(CORRIDOR_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.drawRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.fillOval(i * width, j * height, width, height);
                        g.setColor(Color.white);
                        g.drawString("3", i * width + ((width - metrics.stringWidth("3")) / 2) + 1, j * height + ((height - metrics.getHeight()) / 2) + metrics.getAscent());
                        break;
                    case '4':
                        g.setColor(CORRIDOR_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.drawRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.fillOval(i * width, j * height, width, height);
                        g.setColor(Color.white);
                        g.drawString("4", i * width + ((width - metrics.stringWidth("4")) / 2) + 1, j * height + ((height - metrics.getHeight()) / 2) + metrics.getAscent());
                        break;
                    case '5':
                        g.setColor(CORRIDOR_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.drawRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.fillOval(i * width, j * height, width, height);
                        g.setColor(Color.white);
                        g.drawString("5", i * width + ((width - metrics.stringWidth("5")) / 2) + 1, j * height + ((height - metrics.getHeight()) / 2) + metrics.getAscent());
                        break;
                    case '6':
                        g.setColor(CORRIDOR_COLOR);
                        g.fillRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.drawRect(i * width, j * height, width, height);
                        g.setColor(Color.black);
                        g.fillOval(i * width, j * height, width, height);
                        g.setColor(Color.white);
                        g.drawString("6", i * width + ((width - metrics.stringWidth("6")) / 2) + 1, j * height + ((height - metrics.getHeight()) / 2) + metrics.getAscent());
                        break;
                }
            }
        }
    }
}