package fellowship;

import com.nmerrill.kothcomm.communication.Downloader;
import com.nmerrill.kothcomm.communication.LanguageLoader;
import com.nmerrill.kothcomm.communication.languages.java.JavaLoader;
import com.nmerrill.kothcomm.communication.languages.local.LocalJavaLoader;
import com.nmerrill.kothcomm.game.GameManager;
import com.nmerrill.kothcomm.game.runners.FixedCountRunner;
import com.nmerrill.kothcomm.game.runners.TournamentRunner;
import com.nmerrill.kothcomm.game.scoring.MamAggregator;
import com.nmerrill.kothcomm.game.tournaments.RoundRobin;
import com.nmerrill.kothcomm.gui.GamePane;
import com.nmerrill.kothcomm.gui.GraphMap2DView;
import com.nmerrill.kothcomm.gui.TournamentPane;
import fellowship.characters.BaseCharacter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        LanguageLoader<Player> loader = new LanguageLoader<>(new JavaLoader<>(Player.class), arguments);

        if (arguments.shouldDownload) {
            new Downloader(loader, arguments.questionID).downloadQuestions();
        }
        LocalJavaLoader<Player> localLoader = new LocalJavaLoader<>();
        localLoader.register("Sample", TemplatePlayer::new);
        // Add your player here if you want to test it locally.

        GameManager<Player> manager = new GameManager<>(Fellowship::new, arguments.getRandom())
                .playerCount(2).allowDuplicates();
        manager.register(loader.load());
        runner = new TournamentRunner<>(new RoundRobin<>(manager), new MamAggregator<>());
        if (arguments.useGui) {
            launch();
        } else {
            new FixedCountRunner<>(runner).run(arguments.iterations, System.out);
        }
    }
}
