package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuScene extends SceneWrapper {
    public MenuScene(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void initialize() {
        final GridPane grid = new GridPane();
        this.group.getChildren().add(grid);
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
    }

    @Override
    public void handleEvents() {

    }

    @Override
    public void update() {

    }

    private static BackgroundImage loadBackgroundImage(String url) {
        final Image image = new Image(url);

        final BackgroundRepeat repeatX = BackgroundRepeat.NO_REPEAT;
        final BackgroundRepeat repeatY = BackgroundRepeat.NO_REPEAT;

        final BackgroundPosition position = BackgroundPosition.CENTER;

        final BackgroundSize size = new BackgroundSize(1.00, 1.00, true, true, true, false);
        return new BackgroundImage(image, repeatX, repeatY, position, size);
    }
}
