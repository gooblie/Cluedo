import java.util.*;

public class Room {

    private String name;
    private List<Player> players;
    private List<Position> positions;
    private Position defaultExit;

    public Room(String name, List<Position> positions, Position defaultExit){
        this.name = name;
        this.positions = positions;
        this.defaultExit = defaultExit;
    }

    public String getName() {
        return name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
}
