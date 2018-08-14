import java.util.*;

public class Room {

    private String name;
    private List<Player> players;
    private List<Position> entrances;

    private Position Exit;

    public Room(String name, List<Position> positions){
        this.name = name;
        this.entrances = positions;
        this.Exit = entrances.get(0);
    }

    public String getName() {
        return name;
    }

    public List<Position> getEntrances() {
        return entrances;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Position getExit() {
        return Exit;
    }
}
