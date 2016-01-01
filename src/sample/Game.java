package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {

    Stage stage;
    SceneWrapper currentScene;
    int FPS;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage = primaryStage;

        Group root = new Group();
        primaryStage.setTitle("Safari Volleyball");

        TestScene scene = new TestScene(root, 800, 600);
        scene.setFill(Color.PAPAYAWHIP);

        launchScene(scene);
    }

    public void launchScene(SceneWrapper scene) {
        this.currentScene = scene;
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
