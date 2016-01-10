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
        b_newGame.setStyle("-fx-focus-color: #FF9933; -fx-font: 22 verdana; -fx-base: #FFFF66;");
        b_newGame.setLayoutX(300);
        b_newGame.setLayoutY(200);
        this.addEntity(b_newGame);

        // button Exit
        Button b_close = new Button("Exit");
        b_close.setOnAction(e -> this.quit());
        b_close.setStyle("-fx-focus-color: #FF9933; -fx-font: 18 verdana; -fx-base: #FFFF66;");
        b_close.setLayoutX(300);
        b_close.setLayoutY(250);
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
