package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.layout.*;

public class Result extends SceneWrapper {

    int time;
    int winner;
    int points[];

    public Result(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
        initialize();
        handleEvents();
    }

    @Override
    public void initialize() {
        this.addBackground(new Image("file:src/Pictures/background.png"));
        // INFO ABOUT WINNER
        final Label label = new Label("The winner is:");
        label.setStyle("-fx-font: 16 verdana; -fx-text-fill: snow");
        label.setLayoutX(270);
        label.setLayoutY(250);
        this.addEntity(label);

        final Label l_winner = new Label();
        l_winner.setStyle("-fx-font: 18 verdana; -fx-text-fill: snow");
        l_winner.setLayoutX(400);
        l_winner.setLayoutY(250);
        this.addEntity(l_winner);

        // NEW GAME
        Button b_newGame = new Button("New Game");
        b_newGame.setOnAction(e -> this.exit(new Menu(new Group(), this.game, 600, 400)));
        b_newGame.setStyle("-fx-focus-color: #FF9933; -fx-font: 18 verdana; -fx-base: #FFFF66;");
        b_newGame.setLayoutX(400);
        b_newGame.setLayoutY(300);
        this.addEntity(b_newGame);

        // EXIT
        Button b_close = new Button("Exit");
        b_close.setOnAction(e -> this.quit());
        b_close.setStyle("-fx-focus-color: #FF9933; -fx-font: 18 verdana; -fx-base: #FFFF66;");
        b_close.setLayoutX(270);
        b_close.setLayoutY(300);
        this.addEntity(b_close);
    }

    @Override
    public void handleEvents() {

    }
    @Override
    public void update(double deltaTime) {

    }
    public void quit() { this.game.stage.close(); }

}
