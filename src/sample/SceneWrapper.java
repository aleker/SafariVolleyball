package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public abstract class SceneWrapper extends Scene {

    Game game;
    int width;
    int height;
    Parent root = null;
    Pane pane = null;
    Group group = null;
    Image background;

    public SceneWrapper(Pane root, Game game, int windowWidth, int windowHeight) {
        super(root, windowWidth, windowHeight);
        this.game = game;
        this.width = windowWidth;
        this.height = windowHeight;
        this.root = root;
        this.pane = root;
        initialize();
    }

    public SceneWrapper(Group root, Game game, int windowWidth, int windowHeight/*, Background background*/) {
        super(root, windowWidth, windowHeight);
        this.game = game;
        this.width = windowWidth;
        this.height = windowHeight;
        this.root = root;
        this. group = root;
        initialize();
    }

    public abstract void initialize();

    public void run(Stage stage) {
        final long startTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                double time = (currentTime - startTime) / 1000000000.0;

                handleEvents();
                update();
                stage.show();
            }
        }.start();
    }

    public abstract void handleEvents();

    public abstract void update();

    public void exit(SceneWrapper newScene) {
        this.game.launchScene(newScene);
    }

    public void addEntity(Control entity) {
        if(group != null) group.getChildren().add(entity);
        else if(pane != null) pane.getChildren().add(entity);
    }

    public void addBackground(Image background) {
        this.background = background;
        ImageView imageView = new ImageView();
        imageView.setImage(background);
        this.group.getChildren().add(0,imageView);
    }

}

