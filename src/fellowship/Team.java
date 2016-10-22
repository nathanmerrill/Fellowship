package fellowship;


import java.util.*;

public class Team implements Iterable<Character> {
    private final List<Character> characters;
    private final Player player;
    public Team(Player player){
        characters = new ArrayList<>();
        this.player = player;
    }

    public void addCharacter(Character character){
        if (characters.add(character)){
            character.setTeam(this);
        }
    }

    public Player getPlayer(){
        return player;
    }

    public Team getEnemyTeam(){

    }

    public boolean contains(Character player){
        return characters.contains(player);
    }

    @Override
    public Iterator<Character> iterator() {
        return characters.iterator();
    }

    public List<Character> getPlayers() {
        return new ArrayList<>(characters);
    }
}
