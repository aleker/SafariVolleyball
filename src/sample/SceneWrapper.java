package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public abstract class SceneWrapper extends Scene {

    Game game;
    int width;
    int height;
    Background background;

    public SceneWrapper(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, windowWidth, windowHeight);
        this.game = game;
        this.width = windowWidth;
        this.height = windowHeight;
    }

    public void run(Stage stage, SceneWrapper scene) {
        final long startTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                double time = (currentTime - startTime) / 1000000000.0;

                if( time >= 4.0 ) stop();

                System.out.println(time);
                scene.handleEvents();
                stage.show();
            }
        }.start();
    }

    public void initialize() {}

    public abstract void handleEvents();

    public abstract void draw();

    public void exit(SceneWrapper newScene) {
        this.game.launchScene(newScene);
    }

}

