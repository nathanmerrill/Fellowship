package fellowship;

import com.ppcg.kothcomm.game.AbstractPlayer;
import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.actions.ReadonlyAction;
import fellowship.characters.ReadonlyCharacter;
import fellowship.teams.ReadonlyTeam;

import java.util.Set;

public abstract class Player extends AbstractPlayer<Player>{

    private ReadonlyCharacter currentCharacter;
    private ReadonlyAction currentAction;
    private ReadonlyTeam team;

    public final ReadonlyCharacter getCurrentCharacter(){
        return currentCharacter;
    }
    public final void setCurrentCharacter(ReadonlyCharacter currentCharacter){
        this.currentCharacter = currentCharacter;
    }

    public final ReadonlyAction getCurrentAction() {
        return currentAction;
    }
    public final void setCurrentAction(ReadonlyAction currentAction) {
        this.currentAction = currentAction;
    }

    public final ReadonlyTeam getTeam() {
        return team;
    }
    public final void setTeam(ReadonlyTeam team) {
        this.team = team;
    }

    public abstract ReadonlyAction choose(Set<ReadonlyAction> actions);
    public abstract Point2D locate(Set<Point2D> options);
    public abstract ReadonlyCharacter target(Set<ReadonlyCharacter> options);
}
