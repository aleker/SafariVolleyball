package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import java.util.Random;

public class TestScene extends SceneWrapper {

    public TestScene(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void initialize() {
        Button button = new Button("Go to test scene 2");
        button.setOnAction(e -> this.exit(new TestScene2(new Group(), this.game, 600, 400)));
        this.addEntity(button);
    }

    @Override
    public void handleEvents() {
        Random random = new Random();
        this.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        this.setOnMousePressed(mouseEvent -> System.out.println("Mouse click in TestScene"));
    }

    @Override
    public void update() {

    }
}
