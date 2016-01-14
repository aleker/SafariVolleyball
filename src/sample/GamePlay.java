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
    public int Left_index = 1;
    public int Right_index = 1;


    public GamePlay(Group root, Game game, int windowWidth, int windowHeight, int Left_index, int Right_index) {
        super(root, game, windowWidth, windowHeight);
        this.Left_index = Left_index;
        this.Right_index = Right_index;
        initialize();
        handleEvents();
    }

    @Override
    public void initialize() {

        points = new int[2];
        this.addBackground(new Image("file:src/Pictures/background.png"));
        Canvas canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        group.getChildren().add(canvas);
        createPlayers();
        createEntities();

        // temporary button
        Button b_result = new Button("Go to Result_scene");
        b_result.setOnAction(e -> this.exit(new Result(new Group(), this.game, 800, 600)));
        this.addEntity(b_result);
        //end temporary button

    }

    @Override
    public void handleEvents() {

    }

    @Override
    public void update(double deltaTime) {
        ball.detectStaticCollison();
        ball.calculateNewPosition();
        gc.clearRect(0, 0, 800, 600);
        ball.setCenterPoint();
        ball.detectStaticCollison();
        ball.calculateNewPosition();
        gc.drawImage(background, 0, 0);
        gc.drawImage(net.image, net.point.pos_x, net.point.pos_y);
        gc.drawImage(ball.image, ball.point.pos_x, ball.point.pos_y);

    }

    public static boolean stop() {
        return !playing;
    }

    public void createEntities() {
        leftwall = new StaticEntity(0, 0);
        rightwall = new StaticEntity(800, 0);
        ceiling = new StaticEntity(0, 0);
        ground = new StaticEntity(0, 600);
        net = new StaticEntity("Pictures/net.png", 400, 231);
        ball = new DynamicEntity("Pictures/ball.png");
    }

    public void createPlayers() {
        Player listOfPlayers[] = new Player[2];
        listOfPlayers[0] = PlayerList.newPlayer(Left_index, Player.LEFT_SIDE);
        listOfPlayers[1] = PlayerList.newPlayer(Right_index, Player.RIGHT_SIDE);

        // colour = 0 -> it will be changed so the value will return appropriate colour of animal
        // createAnimal not working!
        //listOfPlayers[0].createAnimal(0);
        //listOfPlayers[1].createAnimal(1);

        //listOfPlayers[0].animal.startPos();
        //listOfPlayers[1].animal.startPos();
    }

    public void setNewServe(int player_number) {
        listOfPlayers[0].animal.startPos();
        listOfPlayers[1].animal.startPos();
        ball.setNewSetPosition(player_number);
    }

}