package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class Game extends Application {

    Stage stage;
    SceneWrapper currentScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        primaryStage.setResizable(false);
        primaryStage.setTitle("Safari Volleyball");

        Group root = new Group();
        Menu scene = new Menu(root, this, 600, 400);
        launchScene(scene);
        stage.show();
    }

    public void launchScene(SceneWrapper scene) {
        if(currentScene != null) currentScene.stopScene();
        currentScene = scene;
        stage.setScene(scene);
        scene.runScene();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
