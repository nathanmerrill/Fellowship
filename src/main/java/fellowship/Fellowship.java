package fellowship;


import com.nmerrill.kothcomm.game.games.MaxActionQueueGame;
import com.nmerrill.kothcomm.game.maps.Point2D;
import com.nmerrill.kothcomm.game.maps.graphmaps.AdjacencyGraphMap;
import com.nmerrill.kothcomm.game.maps.graphmaps.GraphMap;
import com.nmerrill.kothcomm.game.maps.graphmaps.bounds.point2D.SquareBounds;
import com.nmerrill.kothcomm.game.maps.graphmaps.neighborhoods.MooreNeighborhood;
import com.nmerrill.kothcomm.game.scoring.Scoreboard;
import fellowship.characters.BaseCharacter;
import fellowship.characters.CharacterTemplate;
import fellowship.characters.Team;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Lists;

import java.util.List;


public class Fellowship extends MaxActionQueueGame<Player> {
    public static final int CHARACTERS_PER_TEAM = 3;
    public static final int MAP_SIZE = 10;
    public static final int MAX_STEPS = 10000;

    private MutableMap<Player, Team> teams;
    private GraphMap<Point2D, MapObject> map;

    public Fellowship() {
        super(MAX_STEPS);
    }

    public GraphMap<Point2D, MapObject> getMap() {
        return map;
    }

    @Override
    public void setup() {
        teams = players.toMap(i -> i, Team::new);
        map = new AdjacencyGraphMap<>(new SquareBounds(MAP_SIZE), new MooreNeighborhood());

        MutableList<Team> teamList = teams.valuesView().toList();
        teamList.zip(teamList.asReversed()).forEach(pair -> pair.getOne().setEnemyTeam(pair.getTwo()));
        MutableMap<Player, MutableList<CharacterTemplate>> templates =
                players.toMap(i -> i, i -> Lists.mutable.ofAll(i.createCharacters()));
        templates.forEachKeyValue((player, templateList) -> {
            if (!templateList.allSatisfy(CharacterTemplate::isValid)) {
                throw new RuntimeException(player.getName() + " created an invalid character");
            }
            if (templateList.size() != CHARACTERS_PER_TEAM) {
                throw new RuntimeException(player.getName() + " created a team of " + templateList.size() + " characters");
            }
            templateList.forEach(template -> {
                BaseCharacter character = new BaseCharacter(queue,
                        map,
                        teams.get(player),
                        random
                );
                template.currentAttributes().forEach(character::addStat);
                template.currentAbilities().forEach(character::addAbility);
                character.start();
            });
        });
        teamList.zipWithIndex().forEach(t -> {
            MutableList<BaseCharacter> characters = t.getOne().getCharacters();
            int y = t.getTwo() * (MAP_SIZE - 1);
            int spacing = MAP_SIZE / characters.size();
            characters.zipWithIndex().forEach(p -> {
                BaseCharacter character = p.getOne();
                int x = spacing * p.getTwo();
                character.setLocation(new Point2D(x, y));
            });
        });

    }

    @Override
    public Scoreboard<Player> getScores() {
        Scoreboard<Player> scores = new Scoreboard<>();
        teams.toMap(Team::getPlayer, t -> Math.min(t.getCharacters().size(), CHARACTERS_PER_TEAM)).forEach(scores::addScore);
        scores.setDescending();
        return scores;
    }

    @Override
    public boolean finished() {
        return super.finished() || teams.collect(Team::getCharacters).anySatisfy(List::isEmpty);
    }
}
