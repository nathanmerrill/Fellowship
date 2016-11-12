package fellowship.characters;


import fellowship.Player;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;

import java.util.*;

public class Team implements Iterable<BaseCharacter> {
    private final MutableSet<BaseCharacter> characters;
    private final Player player;
    private Team enemyTeam;

    public Team(Player player){
        characters = Sets.mutable.empty();
        this.player = player;
    }

    public void addCharacter(BaseCharacter character){
        characters.add(character);
        updatePlayers();
    }

    public void removeCharacter(BaseCharacter character){
        characters.remove(character);
        updatePlayers();
    }

    private void updatePlayers(){
        player.setTeam(characters.collect(BaseCharacter::readonly));
        enemyTeam.getPlayer().setEnemies(characters.collect(BaseCharacter::enemy));
    }

    public Player getPlayer(){
        return player;
    }

    public Team getEnemyTeam() {
        return enemyTeam;
    }

    public void setEnemyTeam(Team enemyTeam) {
        this.enemyTeam = enemyTeam;
    }

    public boolean contains(BaseCharacter player){
        return characters.contains(player);
    }

    @Override
    public Iterator<BaseCharacter> iterator() {
        return characters.iterator();
    }

    public MutableList<BaseCharacter> getCharacters() {
        return Lists.mutable.ofAll(characters);
    }
}
