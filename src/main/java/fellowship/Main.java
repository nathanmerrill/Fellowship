package fellowship;

import com.nmerrill.kothcomm.communication.Arguments;
import com.nmerrill.kothcomm.communication.Downloader;
import com.nmerrill.kothcomm.communication.LanguageLoader;
import com.nmerrill.kothcomm.communication.languages.java.JavaLoader;
import com.nmerrill.kothcomm.communication.languages.local.LocalJavaLoader;
import com.nmerrill.kothcomm.game.GameManager;
import com.nmerrill.kothcomm.game.PlayerType;
import com.nmerrill.kothcomm.game.runners.FixedCountRunner;
import com.nmerrill.kothcomm.game.runners.TournamentRunner;
import com.nmerrill.kothcomm.game.scoring.STVAggregator;
import com.nmerrill.kothcomm.game.scoring.Scoreboard;
import com.nmerrill.kothcomm.game.tournaments.RoundRobin;
import com.nmerrill.kothcomm.ui.gui.GamePane;
import com.nmerrill.kothcomm.ui.gui.GraphMap2DView;
import com.nmerrill.kothcomm.ui.gui.TournamentPane;
import com.nmerrill.kothcomm.ui.text.TableBuilder;
import fellowship.characters.BaseCharacter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

public class Main extends Application {
    private static TournamentRunner<Player> runner;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        TournamentPane<Player, Fellowship> runnerPane = new TournamentPane<>(runner, game -> {
            GamePane pane = new GamePane(game);
            GraphMap2DView<MapObject> mapView = new GraphMap2DView<>(game.getMap());
            mapView.addListener(mapObject -> {
                if (mapObject == null){
                    pane.setRight(null);
                } else if (mapObject instanceof BaseCharacter) {
                    BaseCharacter object = (BaseCharacter) mapObject;
                    pane.setRight(new CharacterView(object));
                }
            });
            pane.setCenter(mapView);
            return pane;
        });
        root.getChildren().add(runnerPane);
        root.setVisible(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Fellowship");
        primaryStage.setHeight(500);
        primaryStage.setWidth(800);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Arguments arguments = Arguments.parse(args);
        LanguageLoader<Player> loader = new LanguageLoader<>(arguments);
        loader.addLoader(new JavaLoader<>(Player.class));

        if (arguments.validQuestionID()) {
            new Downloader(loader, arguments.questionID).downloadQuestions();
        }
        LocalJavaLoader<Player> localLoader = new LocalJavaLoader<>();
//         Add your player here if you want to test it locally, and uncomment the next line
//        localLoader.register("Sample", TemplatePlayer::new);
//        localLoader.register("Your player", YourPlayer::new);
//        loader.addLoader(localLoader);
        GameManager<Player> manager = new GameManager<>(Fellowship::new, arguments.getRandom())
                .playerCount(2);
        manager.register(loader.load());
        runner = new TournamentRunner<>(new RoundRobin<>(manager), new STVAggregator<>());
        if (arguments.useGui) {
            launch(Main.class);
        } else {
            new FixedCountRunner<>(runner).run(arguments.iterations);

            MutableList<Scoreboard<PlayerType<Player>>> scoreboards = runner.getScoreList();
            TableBuilder builder = new TableBuilder();
            builder.hasHeader(true);
            builder.setBorderType(TableBuilder.BorderType.ASCII);
            builder.rightAlign();
            MutableList<String> header = Lists.mutable.of("Name");
            header.addAll(manager.allPlayers().collect(PlayerType::getName));
            System.out.println(builder.display(manager.allPlayers(), p1 -> {
                MutableList<String> row = Lists.mutable.of(p1.getName());
                manager.allPlayers().forEach(p2 -> {
                    if (p1.equals(p2)){
                        row.add("");
                        return;
                    }
                    int wins=0, ties=0, losses=0;
                    for (Scoreboard<PlayerType<Player>> scoreboard: scoreboards.select(s -> s.contains(p1) && s.contains(p2))){
                        int compare = scoreboard.compare(p1, p2);
                        if (compare < 0){
                            wins++;
                        } else if (compare > 0){
                            losses++;
                        } else {
                            ties++;
                        }
                    }
                    if (wins != 0 || ties != 0 || losses != 0) {
                        row.add(wins + "-" + ties + "-" + losses);
                    } else {
                        row.add("");
                    }
                });
                return row;
            }, header));

            System.exit(0);
        }
    }
}
