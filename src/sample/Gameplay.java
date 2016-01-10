package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;

public class Gameplay extends SceneWrapper {

    // Player playerList[];
    int points[];

    public Gameplay(Pane root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void initialize() {
        points = new int[2];
    }

    @Override
    public void handleEvents() {

    }

    @Override
    public void update() {

    }

    public void createEntities() {}
    public void createPlayer() {}
    public void setNewServe() {}

}
