package fellowship;

import game.Game;
import game.Scoreboard;

import java.util.stream.StreamSupport;

public class Fellowship extends Game<Team> {

    @Override
    public void setup() {

        for (Team team: players){
            for (Character character: team){

            }
        }
    }

    @Override
    public Scoreboard<Team> getScores() {
        Scoreboard<Team> s = new Scoreboard<>();
        return s;
    }

    @Override
    protected boolean step() {
        return false;
    }
}
