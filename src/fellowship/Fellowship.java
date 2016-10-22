package fellowship;


import com.ppcg.kothcomm.game.AbstractGame;
import com.ppcg.kothcomm.game.scoring.Scoreboard;


public class Fellowship extends AbstractGame<Player> {

    @Override
    public void setup() {

    }

    @Override
    public Scoreboard<Player> getScores() {
       return null;
    }

    @Override
    protected boolean step() {
        return false;
    }
}
