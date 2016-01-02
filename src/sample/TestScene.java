package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import java.util.Random;

public class TestScene extends SceneWrapper {

    public TestScene(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void handleEvents() {
        Random random = new Random();
        this.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        this.setOnMousePressed(mouseEvent -> System.out.println("Mouse click in TestScene"));
        this.setOnMouseReleased(mouseEvent -> this.exit(new TestScene2(new Group(), this.game, 600, 400)));
    }

    @Override
    public void draw() {

    }
}
