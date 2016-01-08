package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public abstract class SceneWrapper extends Scene {

    Game game;
    int width;
    int height;
    Group root;
    BackgroundImage background;

    public SceneWrapper(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, windowWidth, windowHeight);
        this.game = game;
        this.width = windowWidth;
        this.height = windowHeight;
        this.root = root;
        initialize();
    }

    public abstract void initialize();

    public void run(Stage stage) {
        final long startTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                double time = (currentTime - startTime) / 1000000000.0;

                System.out.println(time);
                handleEvents();
                update();
                stage.show();
            }
        }.start();
    }

    public abstract void handleEvents();

    public abstract void update();

    public void exit(SceneWrapper newScene) {
        this.game.launchScene(newScene);
    }

    public void addEntity(Control entity) {
        root.getChildren().add(entity);
    }

}

