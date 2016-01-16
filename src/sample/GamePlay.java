package sample;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.application.*;
import javafx.scene.image.Image;

import java.lang.Exception;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private int noOfConsecutiveContacts = 0;
    private int sideOfLastContact = Player.LEFT_SIDE;

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
        createEntities();
        // entities should be created before players
        createPlayers();

        // temporary button
        Button b_result = new Button("Go to Result_scene");
        b_result.setOnAction(e -> this.exit(new Result(new Group(), this.game, 800, 600)));
        this.addEntity(b_result);
        //end temporary button
        playing = true;
    }

    @Override
    public void handleEvents() {

    }

    @Override
    public void update(double deltaTime) {
        int sideOfContact = Player.LEFT_SIDE;
        boolean contact = false;
        ball.detectStaticCollison();

        for (int i = Player.LEFT_SIDE; i <= Player.RIGHT_SIDE; i++) {
            if (ball.detectDynamicCollision(listOfPlayers[i].animal) != 0) {
                contact = true;
                sideOfContact = i;
            }
        }
        if (contact) {
            if (sideOfContact == sideOfLastContact) {
                noOfConsecutiveContacts++;
            } else {
                noOfConsecutiveContacts = 1;
            }
            sideOfLastContact = sideOfContact;
        }

        ball.calculateNewPosition(deltaTime);
        gc.clearRect(0, 0, 800, 600);
        gc.drawImage(background, 0, 0, this.width, this.height);
        gc.drawImage(net.image, net.point.pos_x, net.point.pos_y);
        gc.drawImage(ball.image, ball.point.pos_x, ball.point.pos_y);
        listOfPlayers[0].moveDecision(0, deltaTime);
        listOfPlayers[1].moveDecision(0, deltaTime);
        gc.drawImage(listOfPlayers[0].animal.image, listOfPlayers[0].animal.point.pos_x, listOfPlayers[0].animal.point.pos_y);
        gc.drawImage(listOfPlayers[1].animal.image, listOfPlayers[1].animal.point.pos_x, listOfPlayers[1].animal.point.pos_y);

        gc.setFont(Font.font("Verdana", FontWeight.NORMAL, 18));
        gc.setFill(Color.BLACK);
        gc.fillText(new Integer(noOfConsecutiveContacts).toString(), 200, 20);
    }

    public static boolean stop() {
        return !playing;
    }

    public void createEntities() {
        leftwall = new StaticEntity(0, 0);
        rightwall = new StaticEntity(800, 0);
        ceiling = new StaticEntity(0, 0);
        ground = new StaticEntity(0, 600);
        net = new StaticEntity("Pictures/net.png", 400 - (29 / 2), 231);
        ball = new DynamicEntity("Pictures/ball.png", GameConstant.BALL_SCALE);
    }

    public void createPlayers() {
        listOfPlayers = new Player[2];
        listOfPlayers[0] = PlayerList.newPlayer(Left_index, Player.LEFT_SIDE);
        listOfPlayers[1] = PlayerList.newPlayer(Right_index, Player.RIGHT_SIDE);

        listOfPlayers[0].createAnimal(0);
        listOfPlayers[1].createAnimal(1);
    }

    public void setNewServe(int player_number) {
//        listOfPlayers[0].animal.startPos();
//        listOfPlayers[1].animal.startPos();
        ball.setNewSetPosition(player_number);
    }

}