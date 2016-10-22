package fellowship;

import com.ppcg.kothcomm.game.AbstractPlayer;
import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.actions.CharacterAction;
import fellowship.actions.ReadonlyCharacterAction;
import fellowship.characters.ReadonlyCharacter;
import fellowship.teams.ReadonlyTeam;

import java.util.Set;

public abstract class Player extends AbstractPlayer<Player>{

    private ReadonlyCharacter currentCharacter;
    private ReadonlyCharacterAction currentAction;
    private ReadonlyTeam team;

    public final ReadonlyCharacter getCurrentCharacter(){
        return currentCharacter;
    }
    public final void setCurrentCharacter(ReadonlyCharacter currentCharacter){
        this.currentCharacter = currentCharacter;
    }

    public final ReadonlyCharacterAction getCurrentAction() {
        return currentAction;
    }
    public final void setCurrentAction(ReadonlyCharacterAction currentAction) {
        this.currentAction = currentAction;
    }

    public final ReadonlyTeam getTeam() {
        return team;
    }
    public final void setTeam(ReadonlyTeam team) {
        this.team = team;
    }

    public abstract CharacterAction choose(Set<ReadonlyCharacterAction> actions);
    public abstract Point2D locate(Set<Point2D> options);
    public abstract ReadonlyCharacter target(Set<ReadonlyCharacter> options);
}
