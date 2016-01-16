package sample;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

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

    // temporary
    private double counter = 0;
    private int rightDir = Animal.DIR_RIGHT;
    // temporary

    double time;

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
        this.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT:
                    listOfPlayers[Player.RIGHT_SIDE].moveDecision(Animal.LEFT, time);
                    break;
                case RIGHT:
                    listOfPlayers[Player.RIGHT_SIDE].moveDecision(Animal.RIGHT, time);
                    break;
                case UP:
                    listOfPlayers[Player.RIGHT_SIDE].moveDecision(Animal.UP, time);
                    break;
                case A:
                    listOfPlayers[Player.LEFT_SIDE].moveDecision(Animal.LEFT, time);
                    break;
                case D:
                    listOfPlayers[Player.LEFT_SIDE].moveDecision(Animal.RIGHT, time);
                    break;
                case W:
                    listOfPlayers[Player.LEFT_SIDE].moveDecision(Animal.UP, time);
                    break;
                case ESCAPE:
                    this.exit(new Menu(new Group(), this.game, 600, 400));
                    break;
            }
        });
    }

    @Override
    public void update(double deltaTime) {
        this.time = deltaTime;
        ball.detectStaticCollison();
        ball.calculateNewPosition();
        gc.clearRect(0, 0, 800, 600);
        ball.setCenterPoint();
        ball.detectStaticCollison();
        ball.calculateNewPosition();
        //listOfPlayers[0].animal.calculateNewPosition();
        //listOfPlayers[1].animal.calculateNewPosition();
        //listOfPlayers[0].animal.detectStaticCollison();
        //listOfPlayers[1].animal.detectStaticCollison();
        listOfPlayers[0].animal.countCenter();
        listOfPlayers[1].animal.countCenter();
        ball.detectDynamicCollision(listOfPlayers[0].animal);
        ball.detectDynamicCollision(listOfPlayers[1].animal);
        gc.drawImage(background, 0, 0, this.width, this.height);
        gc.drawImage(net.image, net.point.pos_x, net.point.pos_y);
        gc.drawImage(ball.image, ball.point.pos_x, ball.point.pos_y);
        listOfPlayers[0].moveDecision(0, deltaTime);
        listOfPlayers[1].moveDecision(0, deltaTime);
        gc.drawImage(listOfPlayers[0].animal.image, listOfPlayers[0].animal.point.pos_x, listOfPlayers[0].animal.point.pos_y);
        gc.drawImage(listOfPlayers[1].animal.image, listOfPlayers[1].animal.point.pos_x, listOfPlayers[1].animal.point.pos_y);
    }

    public static boolean stop() {
        return !playing;
    }

    public void createEntities() {
        leftwall = new StaticEntity(0, 0);
        rightwall = new StaticEntity(800, 0);
        ceiling = new StaticEntity(0, 0);
        ground = new StaticEntity(0, 600);
        net = new StaticEntity("Pictures/net.png", 400 - 29 / 2, 231);
        ball = new DynamicEntity("Pictures/ball.png");
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
