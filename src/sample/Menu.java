package sample;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Menu extends SceneWrapper {

    public Menu(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void initialize() {
        this.addBackground(new Image("file:src/Pictures/background.png"));
        final GridPane grid = (GridPane) getRoot();

        // CHANGE PLAYER
        // labels
        final Label leftPlayerLabel = new Label("Left Player:");
        leftPlayerLabel.setStyle("-fx-font: 16 verdana; -fx-text-fill: snow");
        leftPlayerLabel.setLayoutX(270);
        leftPlayerLabel.setLayoutY(200);
        //this.addEntity(leftPlayerLabel);
        final Label rightPlayerLabel = new Label("Right Player:");
        rightPlayerLabel.setStyle("-fx-font: 16 verdana; -fx-text-fill: snow");
        rightPlayerLabel.setLayoutX(270);
        rightPlayerLabel.setLayoutY(250);
        //this.addEntity(rightPlayerLabel);
        grid.add(leftPlayerLabel, 0, 1);
        grid.add(rightPlayerLabel, 0, 2);

        // ComboBoxes
        final ComboBox<String> leftPlayerComboBox = new ComboBox<String>();
        final ComboBox<String> rightPlayerComboBox = new ComboBox<String>();
        for (Class<?> type: PlayerList.playerList) {
            leftPlayerComboBox.getItems().add(type.getName());
            rightPlayerComboBox.getItems().add(type.getName());
        }
        leftPlayerComboBox.setValue(leftPlayerComboBox.getItems().get(0));
        rightPlayerComboBox.setValue(rightPlayerComboBox.getItems().get(0));
        leftPlayerComboBox.setLayoutX(400);
        leftPlayerComboBox.setLayoutY(200);
        rightPlayerComboBox.setLayoutX(400);
        rightPlayerComboBox.setLayoutY(250);
        //this.addEntity(leftPlayerComboBox);
        //this.addEntity(rightPlayerComboBox);
        grid.add(leftPlayerComboBox, 1, 1);
        grid.add(rightPlayerComboBox, 1, 2);


        // NEW GAME
        Button b_newGame = new Button("Start");
        b_newGame.setOnAction(e -> {
            System.out.print("left_index ");
            System.out.println(leftPlayerComboBox.getSelectionModel().getSelectedIndex());
            System.out.print("right_index ");
            System.out.println(rightPlayerComboBox.getSelectionModel().getSelectedIndex());
            GamePlay game = new GamePlay(new Group(), this.game, 800, 600);
            game.setLeft_index(leftPlayerComboBox.getSelectionModel().getSelectedIndex());
            game.setRight_index(rightPlayerComboBox.getSelectionModel().getSelectedIndex());
            this.exit(game);
        });
        b_newGame.setStyle("-fx-focus-color: #FF9933; -fx-font: 18 verdana; -fx-base: #FFFF66;");
        b_newGame.setLayoutX(400);
        b_newGame.setLayoutY(300);
        //this.addEntity(b_newGame);
        grid.add(b_newGame, 0, 3, 2, 1);

        // EXIT
        Button b_close = new Button("Exit");
        b_close.setOnAction(e -> this.quit());
        b_close.setStyle("-fx-focus-color: #FF9933; -fx-font: 18 verdana; -fx-base: #FFFF66;");
        b_close.setLayoutX(270);
        b_close.setLayoutY(300);
        //this.addEntity(b_close);
        grid.add(b_close, 0, 3, 2, 2);
    }

    @Override
    public void handleEvents() {

    }

    @Override
    public void update() {

    }

    public void changePlayer() {}
    public void quit() { this.game.stage.close(); }

}

/*      super(new GridPane(), 640, 480);
        final GridPane grid = (GridPane) getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(5);
        grid.setVgap(5);

        final Text title = new Text("Safari Volleyball");
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(title, 0, 0, 2, 1);

        final Label leftPlayerLabel = new Label("Left Player:");
        final Label rightPlayerLabel = new Label("Right Player:");
        grid.add(leftPlayerLabel, 0, 1);
        grid.add(rightPlayerLabel, 0, 2);

        final ComboBox<String> leftPlayerComboBox = new ComboBox<String>();
        final ComboBox<String> rightPlayerComboBox = new ComboBox<String>();
        leftPlayerComboBox.getItems().addAll("Human (WSAD)");
        leftPlayerComboBox.setValue(leftPlayerComboBox.getItems().get(0));
        rightPlayerComboBox.getItems().addAll("Human (Arrows)", "AI1", "AI2");
        rightPlayerComboBox.setValue(rightPlayerComboBox.getItems().get(0));
        grid.add(leftPlayerComboBox, 1, 1);
        grid.add(rightPlayerComboBox, 1, 2);

        final Button playButton = new Button("Play!");
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                System.out.println("Left Player: " + leftPlayerComboBox.getValue());
                System.out.println("Right Player: " + rightPlayerComboBox.getValue());
            }
        });
        grid.add(playButton, 0, 3, 2, 1);

        final BackgroundImage backgroundImage = loadBackgroundImage("sample/background.png");
        grid.setBackground(new Background(backgroundImage));
        // no fills? */
