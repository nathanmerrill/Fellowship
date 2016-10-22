package fellowship;

import com.ppcg.kothcomm.game.AbstractPlayer;
import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.actions.CharacterAction;

import java.util.Set;

public abstract class Player extends AbstractPlayer<Player>{

    public abstract CharacterAction choose(Set<CharacterAction> actions, ReadonlyCharacter character);
    public abstract Point2D step(Set<Point2D> options, ReadonlyCharacter character);
}
