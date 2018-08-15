import java.util.*;

public class Room {

    private String name;
    private List<Player> players;
    private List<Position> entrances;
    private List<WeaponCard> weapons;
    private List<CharacterCard> characters;
    private Position Exit;

    public Room(String name, List<Position> entrances, Position exit){
        this.name = name;
        this.entrances = entrances;
        this.Exit = exit;
        this.players = new ArrayList<>();
        this.weapons = new ArrayList<>();
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
        player.setRoom(this);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public void addWeapon(WeaponCard weapon){
        weapons.add(weapon);
        weapon.setRoom(this);
    }

    public List<CharacterCard> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterCard> characters) {
        this.characters = characters;
        for(CharacterCard character : characters){
            character.setRoom(this);
        }
    }

    public void addCharacter(CharacterCard character){
        this.characters.add(character);
        character.setRoom(this);
    }

    public Position getExit() {
        return Exit;
    }
}
