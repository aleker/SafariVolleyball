package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.layout.*;

public class Result extends SceneWrapper {

    int time;
    int winner;
    int points[];

    public Result(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void initialize() {
        this.addBackground(new Image("file:src/Pictures/background.png"));

        // button New Game
        Button b_newGame = new Button("New Game (go to Menu)");
        b_newGame.setOnAction(e -> this.exit(new Menu(new Group(), this.game, 600, 400)));
        this.addEntity(b_newGame);

        // button Exit
        Button b_close = new Button("Exit");
        b_close.setOnAction(e -> this.quit());
        b_close.setLayoutX(200);
        b_close.setLayoutY(400);
        this.addEntity(b_close);
    }

    @Override
    public void handleEvents() {

    }
    @Override
    public void update() {

    }
    public void quit() { this.game.stage.close(); }

}
