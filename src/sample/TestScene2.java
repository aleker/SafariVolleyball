package sample;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

public class TestScene2 extends SceneWrapper {

    public TestScene2(Pane root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void initialize() {
        Button button = new Button("Close button");
        button.setOnAction(e -> this.game.stage.close());
        this.addEntity(button);
    }

    @Override
    public void handleEvents() {
        this.setOnMousePressed(mouseEvent -> System.out.println("Mouse click in TestScene2"));
    }

    @Override
    public void update() {

    }
}
