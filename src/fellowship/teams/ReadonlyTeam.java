package fellowship.teams;

import fellowship.characters.CharacterInterface;
import fellowship.characters.ReadonlyCharacter;
import org.eclipse.collections.api.list.MutableList;

import java.util.Iterator;

public class ReadonlyTeam implements Iterable<ReadonlyCharacter>, TeamInterface {
    private final Team team;

    public ReadonlyTeam(Team team){
        this.team = team;
    }

    public EnemyTeam getEnemyTeam() {
        return new EnemyTeam(team.getEnemyTeam());
    }

    public boolean contains(CharacterInterface player){
        return team.contains(player);
    }

    public MutableList<ReadonlyCharacter> getCharacters() {
        return team.getCharacters().collect(ReadonlyCharacter::new);
    }

    @Override
    public Iterator<ReadonlyCharacter> iterator() {
        return team.getCharacters().collect(ReadonlyCharacter::new).iterator();
    }

}
