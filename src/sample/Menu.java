package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Menu extends SceneWrapper {

    public Menu(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
        initialize();
        handleEvents();
    }

    @Override
    public void initialize() {
        this.addBackground(new Image("file:src/Pictures/background.png"));
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setLayoutX(250);
        grid.setLayoutY(150);

        // CHANGE PLAYER
        // labels
        final Label leftPlayerLabel = new Label("Left Player:");
        leftPlayerLabel.setStyle("-fx-font: 16 verdana; -fx-text-fill: snow");
        final Label rightPlayerLabel = new Label("Right Player:");
        rightPlayerLabel.setStyle("-fx-font: 16 verdana; -fx-text-fill: snow");
        grid.add(leftPlayerLabel, 0, 1);
        grid.add(rightPlayerLabel, 0, 2);

        // ComboBoxes
        final ComboBox<String> leftPlayerComboBox = new ComboBox<String>();
        final ComboBox<String> rightPlayerComboBox = new ComboBox<String>();
        for (Class<?> type: PlayerList.playerList) {
            leftPlayerComboBox.getItems().add(type.getSimpleName());
            rightPlayerComboBox.getItems().add(type.getSimpleName());
        }
        leftPlayerComboBox.setValue(leftPlayerComboBox.getItems().get(0));
        rightPlayerComboBox.setValue(rightPlayerComboBox.getItems().get(0));
        grid.add(leftPlayerComboBox, 1, 1);
        grid.add(rightPlayerComboBox, 1, 2);

        // NEW GAME
        Button b_newGame = new Button("Start");
        b_newGame.setOnAction(e -> {
            GamePlay game = new GamePlay(new Group(), this.game, 800, 600,
                    leftPlayerComboBox.getSelectionModel().getSelectedIndex(),
                    rightPlayerComboBox.getSelectionModel().getSelectedIndex());
            this.exit(game);
        });
        b_newGame.setStyle("-fx-focus-color: #FF9933; -fx-font: 18 verdana; -fx-base: #FFFF66;");
        grid.add(b_newGame, 1, 3, 2, 1);

        // EXIT
        Button b_close = new Button("Exit");
        b_close.setOnAction(e -> this.quit());
        b_close.setStyle("-fx-focus-color: #FF9933; -fx-font: 18 verdana; -fx-base: #FFFF66;");
        grid.add(b_close, 0, 3, 2, 1);

        this.group.getChildren().add(grid);
    }
    @Override
    public void handleEvents() {

    }

    @Override
    public void update(double deltaTime) {

    }

    public void quit() { this.game.stage.close(); }

    private String improveName(String name) {
        // delete "sample."
        name = name.substring(7, name.length());
        return name;
    }
}
