package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.image.Image;
import java.lang.Exception;
import javafx.stage.*;
import javafx.scene.layout.*;

public class GamePlay extends SceneWrapper {

    int left_index;
    int right_index;
    Player list_of_players[];
    int points[];
    private static boolean playing;

    public GamePlay(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);

    }

    public int getLeft_index() {
        return left_index;
    }

    public void setLeft_index(int left_index) {
        this.left_index = left_index;
    }

    public int getRight_index() {
        return right_index;
    }

    public void setRight_index(int right_index) {
        this.right_index = right_index;
    }

    @Override
    public void initialize() {
        System.out.print("left_index ");
        System.out.println(left_index);
        System.out.print("right_index ");
        System.out.println(right_index);
        this.addBackground(new Image("file:src/Pictures/background.png"));
        points = new int[2];
        PlayerList playerList = new PlayerList();
        //playerList.newPlayer(left_index, Player.LEFT_SIDE);   // newPlayer(left_index, Player.LEFT_SIDE);

    }

    @Override
    public void handleEvents() {

    }

    @Override
    public void update() {

    }

    public static boolean stop() {
        return !playing;
    }
    public void createEntities() {
        Ball ball = new Ball(200, 200, 20, 20);
        StaticEntity net = new StaticEntity("file:src/Pictures/net.png", 400, 200);

    }
    public void createPlayer() {}
    public void setNewServe() {}

}
