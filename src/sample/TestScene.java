package sample;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TestScene extends SceneWrapper {

    public TestScene(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void initialize() {
        Button button = new Button("Go to test scene 2");
        button.setOnAction(e -> this.exit(new TestScene2(new Pane(), this.game, 600, 400)));
        this.addEntity(button);
    }

    @Override
    public void handleEvents() {
        this.setOnMousePressed(mouseEvent -> System.out.println("Mouse click in TestScene"));
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.RIGHT)
                    System.out.println("Right arrow");
                else if(keyEvent.getCode() == KeyCode.LEFT)
                    System.out.println("Left arrow");
            }
        });
    }

    @Override
    public void update() {

    }
}
