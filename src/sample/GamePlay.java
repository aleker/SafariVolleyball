package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.layout.*;

public class GamePlay extends SceneWrapper {

    // Player playerList[];
    int points[];
    private static boolean playing;

    public GamePlay(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void initialize() {

        points = new int[2];
        this.addBackground(new Image("file:src/Pictures/background.png"));
        // temporary
        Button b_result = new Button("Go to Result_scene");
        b_result.setOnAction(e -> this.exit(new Result(new Group(), this.game, 800, 600)));
        b_result.setLayoutX(200);
        b_result.setLayoutY(200);
        this.addEntity(b_result);
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
    public void createEntities() {}
    public void createPlayer() {}
    public void setNewServe() {}

}
