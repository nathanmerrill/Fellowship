package fellowship;

import com.nmerrill.kothcomm.game.AbstractPlayer;
import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.actions.ReadonlyAction;
import fellowship.characters.CharacterTemplate;
import fellowship.characters.EnemyCharacter;
import fellowship.characters.ReadonlyCharacter;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;

import java.util.List;
import java.util.Set;

public abstract class Player extends AbstractPlayer<Player>{

    protected MutableSet<ReadonlyCharacter> team;
    protected MutableSet<EnemyCharacter> enemies;
    protected MutableMap<Point2D, EnemyCharacter> visibleEnemies;

    public void setTeam(MutableSet<ReadonlyCharacter> team) {
        this.team = team;
    }

    public void setEnemies(MutableSet<EnemyCharacter> enemies) {
        this.enemies = enemies;
    }

    public void setVisibleEnemies(MutableMap<Point2D, EnemyCharacter> enemies){
        this.visibleEnemies = enemies;
    }

    public boolean isEnemy(EnemyCharacter character){
        return enemies.contains(character);
    }

    public boolean isEnemy(ReadonlyCharacter character){
        return !team.contains(character);
    }

    public abstract List<CharacterTemplate> createCharacters();
    public abstract ReadonlyAction choose(Set<ReadonlyAction> actions, ReadonlyCharacter character);
}
