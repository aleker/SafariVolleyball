package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game extends Application {

    Stage stage;
    SceneWrapper currentScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        primaryStage.setTitle("Safari Volleyball");

        Group root = new Group();
        Menu scene = new Menu(root, this, 600, 400);
        launchScene(scene);
    }

    public void launchScene(SceneWrapper scene) {
        this.currentScene = scene;
        this.stage.setScene(scene);
        scene.run(this.stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
