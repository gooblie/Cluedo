import java.util.*;

public class Room {

    private String name;
    private List<Player> players;
    private List<Position> positions;

    public Room(String name, List<Position> positions){
        this.name = name;
        this.positions = positions;
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
