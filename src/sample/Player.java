package sample;

/**
 * Created by aleksander-zn on 2016-01-09.
 */
public class Player {
    // all instance members of reference type are initialized to null
    public Animal animal;

    // British English spelling just to annoy programmers
    private int colour;

    // we can't change side during the game
    private final int side;

    // on which side of the net out character is supposed to be?
    // public to enable other classes to create a player this way:
    // Player p = new Player(Player.LEFT_SIDE);
    // as these constants are numbered from zero, you can use them as array indices, for example:
    // Player[] p = new Player[2];
    // for (int i = Player.LEFT_SIDE; i <= Player.RIGHT_SIDE; i++) {
    //     p[i] = new Player(i);
    // }
    public static final int LEFT_SIDE = 0;
    public static final int RIGHT_SIDE = 1;

    public Player(int side) {
        this.side = side;
    }

    public void createAnimal(int colour) {
        final StaticEntity leftWall = StaticEntity.list_of_staticEntity.get(0);
        final StaticEntity rightWall = StaticEntity.list_of_staticEntity.get(1);
        final StaticEntity net = StaticEntity.list_of_staticEntity.get(4);
        final StaticEntity ground = StaticEntity.list_of_staticEntity.get(3);

        double leftLimit;
        double rightLimit;
        if (side == LEFT_SIDE) {
            leftLimit = leftWall.point.pos_x + leftWall.width;
            rightLimit = net.point.pos_x;
        } else {
            leftLimit = net.point.pos_x + net.width;
            rightLimit = rightWall.point.pos_x;
        }

        // starting position set to the exact center of our half of the volleyball court
        double px = (leftLimit + rightLimit) / 2;
        double py = ground.point.pos_y;

        // colour
        this.colour = colour;
        animal = new Animal(colour, px, py, leftLimit, rightLimit);
    }

    public void moveDecision(int direction, double deltaTime, Point ball_point) {
        // ball_point used in AI
        animal.move(direction, deltaTime);
    }

}
