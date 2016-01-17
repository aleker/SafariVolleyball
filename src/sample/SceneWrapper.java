package sample;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Control;


public abstract class SceneWrapper extends Scene {

    protected int width;
    protected int height;

    protected Parent root = null;
    protected Pane pane = null;
    protected Game game;
    protected Group group = null;

    protected Image background;

    private AnimationTimer animationTimer;

    public SceneWrapper(Pane root, Game game, int windowWidth, int windowHeight) {
        super(root, windowWidth, windowHeight);
        this.game = game;
        this.width = windowWidth;
        this.height = windowHeight;
        this.root = root;
        this.pane = root;
        initialize();
        handleEvents();
    }

    public SceneWrapper(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, windowWidth, windowHeight);
        this.game = game;
        this.width = windowWidth;
        this.height = windowHeight;
        this.root = root;
        this.group = root;
    }

    public abstract void initialize();

    public abstract void handleEvents();

    public void runScene() {
        animationTimer = new AnimationTimer() {
            private long previousCallTime;

            @Override
            public void start() {
                previousCallTime = System.nanoTime();
                super.start();
            }

            @Override
            public void handle(long currentTime) {
                double deltaTime = (currentTime - previousCallTime) / 1000000000.0;

                update(deltaTime);

                previousCallTime = currentTime;
            }
        };
        animationTimer.start();
    }

    public void stopScene() {
        animationTimer.stop();
    }

    public abstract void update(double deltaTime);

    public void exit(SceneWrapper newScene) {
        this.game.launchScene(newScene);
    }

    public void addEntity(Control entity) {
        if(group != null) group.getChildren().add(entity);
        else if(pane != null) pane.getChildren().add(entity);
    }

    public void addEntity(StaticEntity entity) {
        ImageView imageView = new ImageView();
        imageView.setImage(entity.image);
        if(group != null) group.getChildren().add(imageView);
        else if(pane != null) pane.getChildren().add(imageView);
    }

    public void addBackground(Image background) {
        this.background = background;
        ImageView imageView = new ImageView();
        imageView.setImage(background);
        imageView.setFitWidth(this.width);
        imageView.setFitHeight(this.height);
        this.group.getChildren().add(0,imageView);
    }

}
