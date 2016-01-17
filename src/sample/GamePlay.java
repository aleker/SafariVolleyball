package sample;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

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
    public int Right_index;
    private int noOfConsecutiveContacts = 0;
    private int sideOfLastContact = Player.LEFT_SIDE;

    double time;

    private int move[][];

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
        move = new int[2][1];

        this.addBackground(new Image("file:src/Pictures/background.png"));
        Canvas canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        group.getChildren().add(canvas);
        createEntities();
        // entities should be created before players
        createPlayers();
        playing = true;
    }

    @Override
    public void handleEvents() {
        this.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT:
                    move[Player.RIGHT_SIDE][0] = Animal.DIR_LEFT;
                    break;
                case RIGHT:
                    move[Player.RIGHT_SIDE][0] = Animal.DIR_RIGHT;
                    break;
                case UP:
                    move[Player.RIGHT_SIDE][0] = Animal.DIR_UP;
                    break;
                case A:
                    move[Player.LEFT_SIDE][0] = Animal.DIR_LEFT;
                    break;
                case D:
                    move[Player.LEFT_SIDE][0] = Animal.DIR_RIGHT;
                    break;
                case W:
                    move[Player.LEFT_SIDE][0] = Animal.DIR_UP;
                    break;
                case ESCAPE:
                    this.exit(new Menu(new Group(), this.game, 600, 400));
                    break;
            }
        });
        this.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT:
                    move[Player.RIGHT_SIDE][0] = -1;
                    break;
                case RIGHT:
                    move[Player.RIGHT_SIDE][0] = -1;
                    break;
                case UP:
                    move[Player.RIGHT_SIDE][0] = -1;
                    break;
                case A:
                    move[Player.LEFT_SIDE][0] = -1;
                    break;
                case D:
                    move[Player.LEFT_SIDE][0] = -1;
                    break;
                case W:
                    move[Player.LEFT_SIDE][0] = -1;
                    break;
            }
        });
    }

    @Override
    public void update(double deltaTime) {
        int sideOfContact = Player.LEFT_SIDE;
        boolean contact = false;
        this.time = deltaTime;

        // COLLISION WITH FLOOR:
        int floor_collision = ball.detectStaticCollison();
        if (floor_collision < 2) {
            int scoredPlayer = Player.LEFT_SIDE;
            if (floor_collision == 0) scoredPlayer = Player.RIGHT_SIDE;
            points[scoredPlayer]++;
            if (points[scoredPlayer] >= GameConstant.MAX_POINTS ) {
                // GAME OVER
                this.exit(new Result(new Group(), this.game, 800, 600, scoredPlayer));
                playing = false;
            }
            System.out.println(points[0] + " " + points[1]);
            setNewServe(scoredPlayer);
        }

        // BOUNCES COUNTER:
        for (int i = Player.LEFT_SIDE; i <= Player.RIGHT_SIDE; i++) {
            if (ball.detectDynamicCollision(listOfPlayers[i].animal) != 0) {
                contact = true;
                sideOfContact = i;
            }
        }
        if (contact) {
            if (sideOfContact == sideOfLastContact) {
                noOfConsecutiveContacts++;
                if (noOfConsecutiveContacts > GameConstant.MAX_AMOUNT_OF_BOUNCES) {
                    noOfConsecutiveContacts = 0;
                    // FOR MAX_AMOUNT_OF_BOUNCES RESTRICTION:
                    // sideOfContact -> Player who scored
                    //if (sideOfContact == Player.LEFT_SIDE) { sideOfContact = Player.RIGHT_SIDE; }
                    //else { sideOfContact = Player.LEFT_SIDE; }
                    //points[sideOfContact]++;
                    //setNewServe(sideOfContact);
                    //
                }
            } else {
                noOfConsecutiveContacts = 1;
            }
            sideOfLastContact = sideOfContact;
        }

        // DISPLAYING:
        ball.calculateNewPosition(deltaTime);
        gc.clearRect(0, 0, 800, 600);
        gc.drawImage(background, 0, 0, this.width, this.height);
        gc.drawImage(net.image, net.point.pos_x, net.point.pos_y);
        gc.drawImage(ball.image, ball.point.pos_x, ball.point.pos_y);

        whichMoveDecision();

        gc.drawImage(listOfPlayers[0].animal.image, listOfPlayers[0].animal.point.pos_x, listOfPlayers[0].animal.point.pos_y);
        gc.drawImage(listOfPlayers[1].animal.image, listOfPlayers[1].animal.point.pos_x, listOfPlayers[1].animal.point.pos_y);

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        gc.setFont(Font.font("Verdana", FontWeight.NORMAL, 18));
        gc.setFill(Color.BLACK);
        gc.fillText(new Integer(noOfConsecutiveContacts).toString(), 200, 20);

        // displaying points:
        gc.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 80));
        gc.setEffect(ds);
        gc.setFill(Color.ORANGERED);
        gc.fillText(new Integer(points[0]).toString(), this.getWidth()/2 - 80, 70);
        gc.fillText(new Integer(points[1]).toString(), this.getWidth()/2 + 30, 70);
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
        System.out.println("setNewServe for " + player_number);
        double leftLimit, rightLimit;
        // LEFT PLAYER
        leftLimit = leftwall.point.pos_x + leftwall.width;
        rightLimit = net.point.pos_x;
        listOfPlayers[0].animal.getOnPosition((leftLimit + rightLimit) / 2 - listOfPlayers[0].animal.width/2,
                ground.point.pos_y);

        // RIGHT PLAYER
        leftLimit = net.point.pos_x + net.width;
        rightLimit = rightwall.point.pos_x;
        listOfPlayers[1].animal.getOnPosition((leftLimit + rightLimit) / 2- listOfPlayers[0].animal.width/2,
                ground.point.pos_y);

        // BALL
        ball.setNewSetPosition(player_number);
    }

    private void whichMoveDecision() {
        if(move[Player.RIGHT_SIDE][0] == Animal.DIR_LEFT)
            listOfPlayers[Player.RIGHT_SIDE].moveDecision(Animal.DIR_LEFT, this.time);

        else if(move[Player.RIGHT_SIDE][0] == Animal.DIR_RIGHT)
            listOfPlayers[Player.RIGHT_SIDE].moveDecision(Animal.DIR_RIGHT, this.time);

        else if(move[Player.RIGHT_SIDE][0] == Animal.DIR_UP)
            listOfPlayers[Player.RIGHT_SIDE].moveDecision(Animal.DIR_UP, this.time);

        else listOfPlayers[Player.RIGHT_SIDE].moveDecision(0, this.time);

        if(move[Player.LEFT_SIDE][0] == Animal.DIR_LEFT)
            listOfPlayers[Player.LEFT_SIDE].moveDecision(Animal.DIR_LEFT, this.time);

        else if(move[Player.LEFT_SIDE][0] == Animal.DIR_RIGHT)
            listOfPlayers[Player.LEFT_SIDE].moveDecision(Animal.DIR_RIGHT, this.time);

        else if(move[Player.LEFT_SIDE][0] == Animal.DIR_UP)
            listOfPlayers[Player.LEFT_SIDE].moveDecision(Animal.DIR_UP, this.time);

        else listOfPlayers[Player.LEFT_SIDE].moveDecision(0, this.time);
    }

}