package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Game extends Application {

    Stage stage;
    SceneWrapper currentScene;

//    String image = JavaFXApplication9.class.getResource("splash.jpg").toExternalForm();
//    root.setStyle("-fx-background-image: url('" + image + "'); " +
//            "-fx-background-position: center center; " +
//            "-fx-background-repeat: stretch;");

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        primaryStage.setTitle("Safari Volleyball");

        Group root = new Group();
        Menu scene = new Menu(root, this, 800, 600);
        //scene.setFill(Color.PAPAYAWHIP);
        BackgroundImage background = new BackgroundImage(new Image("background.png", 0, 0 , false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //String backgorund = Game.class.getResource("background.png").toExternalForm();
        //root.setStyle("-fx-background-image: url('" + backgorund + "'); " + "-fx-background-position: center ;" + "-fx-background-repeat: stretch");

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
