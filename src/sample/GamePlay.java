package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.image.Image;
import java.lang.Exception;
import javafx.stage.*;
import javafx.scene.layout.*;

public class GamePlay extends SceneWrapper {

    int points[];
    private static boolean playing;

    public GamePlay(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);

    }

    @Override
    public void initialize() {

    }

    @Override
    public void handleEvents() {

    }

    @Override
    public void update() {

    }

    public static boolean stop() {
        return !playing;
    }
    public void createEntities() {

    }
    public void createPlayer() {}
    public void setNewServe() {}

}
