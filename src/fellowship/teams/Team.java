package fellowship.teams;


import fellowship.Player;
import fellowship.characters.BaseCharacter;
import fellowship.characters.CharacterInterface;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;

import java.util.*;

public class Team implements Iterable<BaseCharacter>, TeamInterface {
    private final MutableSet<BaseCharacter> characters;
    private final Player player;
    private Team enemyTeam;

    public Team(Player player){
        characters = Sets.mutable.empty();
        this.player = player;
    }

    public void addCharacter(BaseCharacter character){
        characters.add(character);
    }

    public void removeCharacter(BaseCharacter character){
        characters.add(character);
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

    public boolean contains(CharacterInterface player){
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
