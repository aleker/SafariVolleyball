package sample;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by aleksander-zn on 2016-01-09.
 */
public class Game extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Safari Volleyball");
        primaryStage.setScene(new Menu());
        primaryStage.show();
    }
}
