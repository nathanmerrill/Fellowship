package fellowship.teams;

import fellowship.characters.CharacterInterface;
import org.eclipse.collections.api.list.MutableList;

public interface TeamInterface {
    TeamInterface getEnemyTeam();
    boolean contains(CharacterInterface player);
    MutableList<? extends CharacterInterface> getCharacters();
}
