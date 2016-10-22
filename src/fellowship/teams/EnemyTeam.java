package fellowship.teams;

import fellowship.characters.CharacterInterface;
import fellowship.characters.EnemyCharacter;
import fellowship.characters.ReadonlyCharacter;
import org.eclipse.collections.api.list.MutableList;

import java.util.Iterator;

public class EnemyTeam implements Iterable<ReadonlyCharacter>, TeamInterface {
    private final Team team;

    public EnemyTeam(Team team){
        this.team = team;
    }

    public ReadonlyTeam getEnemyTeam() {
        return new ReadonlyTeam(team.getEnemyTeam());
    }

    public boolean contains(CharacterInterface player){
        return team.contains(player);
    }

    @Override
    public MutableList<EnemyCharacter> getCharacters() {
        return team.getCharacters().collect(EnemyCharacter::new);
    }

    @Override
    public Iterator<ReadonlyCharacter> iterator() {
        return team.getCharacters().collect(ReadonlyCharacter::new).iterator();
    }

}
