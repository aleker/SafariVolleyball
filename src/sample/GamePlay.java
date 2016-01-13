package sample;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.image.Image;

import java.awt.*;
import java.lang.Exception;
import javafx.stage.*;
import javafx.scene.layout.*;

public class GamePlay extends SceneWrapper {

    Player listOfPlayers[];
    int points[];
    private static boolean playing;
    private DynamicEntity ball;
    private StaticEntity leftwall;
    private StaticEntity rightwall;
    private StaticEntity ceiling;
    private StaticEntity ground;
    private StaticEntity net;
    private GraphicsContext gc;
    public int Left_index;

    public int getRight_index() {
        return Right_index;
    }

    public void setRight_index(int right_index) {
        Right_index = right_index;
    }

    public int getLeft_index() {
        return Left_index;
    }

    public void setLeft_index(int left_index) {
        Left_index = left_index;
    }

    public int Right_index;

    public GamePlay(Group root, Game game, int windowWidth, int windowHeight) {
        super(root, game, windowWidth, windowHeight);
    }

    @Override
    public void initialize() {

        points = new int[2];
        this.background = new Image("Pictures/background.png");
        Canvas canvas = new Canvas(800,600);
        gc = canvas.getGraphicsContext2D();
        group.getChildren().add(canvas);
        createEntities();

        // temporary
        Button b_result = new Button("Go to Result_scene");
        b_result.setOnAction(e -> this.exit(new Result(new Group(), this.game, 800, 600)));
        b_result.setLayoutX(200);
        b_result.setLayoutY(200);
        this.addEntity(b_result);
        //end temporary

    }

    @Override
    public void handleEvents() {

    }

    @Override
    public void update(double deltaTime) {
        ball.detectStaticCollison();
        ball.calculateNewPosition();
        gc.clearRect(0, 0, 800,600);
        ball.setCenterPoint();
        ball.detectStaticCollison();
        ball.calculateNewPosition();
        gc.drawImage( background, 0, 0 );
        gc.drawImage(net.image,net.point.pos_x,net.point.pos_y);
        gc.drawImage(ball.image,ball.point.pos_x,ball.point.pos_y);

    }

    public static boolean stop() {
        return !playing;
    }
    public void createEntities() {
        leftwall = new StaticEntity(0,0);
        rightwall = new StaticEntity(800,0);
        ceiling = new StaticEntity(0,0);
        ground = new StaticEntity(0,600);
        net = new StaticEntity("Pictures/net.png",400,231);
        ball = new DynamicEntity("Pictures/ball.png");
    }

    public void createPlayer() {
        //listOfPlayers[0] =  PlayerList.newPlayer(Left_index, Player.LEFT_SIDE);
        //listOfPlayers[1] = PlayerList.playerList.newPlayer(Left_index, Player.RIGHT_SIDE);
    }

    public void setNewServe(int player_number) {

    }

}
