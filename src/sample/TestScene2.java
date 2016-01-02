package sample;

import javafx.scene.Group;

public class TestScene2 extends SceneWrapper {
    public TestScene2(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void handleEvents() {
        this.setOnMousePressed(mouseEvent -> System.out.println("Mouse click in TestScene2"));
    }

    @Override
    public void draw() {

    }
}
