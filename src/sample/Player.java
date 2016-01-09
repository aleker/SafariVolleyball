package sample;

/**
 * Created by aleksander-zn on 2016-01-09.
 */
public class Player {
    // all instance members of reference type are initialized to null
    private Animal animal;

    // British English spelling just to annoy programmers
    private int colour;
    private int side;

    // placeholders for real constants; they may be migrated to another class, don't get used to 'em
    private static final double dx = 0;
    private static final double rad = 0;
    private static final double vel = 0;

    // on which side of the net out character is supposed to be?
    // this constants are public to make life easier; they enable other classes to create a player this way:
    // Player p = new Player(Player.LEFT_SIDE);
    public static final int LEFT_SIDE = -1;
    public static final int RIGHT_SIDE = 1;

    public Player(int side) {
        this.side = side;
    }

    public void createAnimal(int colour) {
        StaticEntity leftWall = StaticEntity.list_of_staticEntity[0];
        StaticEntity rightWall = StaticEntity.list_of_staticEntity[1];
        StaticEntity net = StaticEntity.list_of_staticEntity[3];
        StaticEntity ground = StaticEntity.list_of_staticEntity[4];

        double leftLimit;
        double rightLimit;
        switch (side) {
            case LEFT_SIDE:
                leftLimit = leftWall.point.pos_x + leftWall.width;
                rightLimit = net.point.pos_x;
                break;
            case RIGHT_SIDE:
                leftLimit = net.point.pos_x + net.width;
                rightLimit = rightWall.point.pos_x;
                break;
        }

        // starting position set to the center of our half of the volleyball court
        double px = (leftLimit + rightLimit) / 2;
        double py = ground.point.y;

        animal = new Animal(px, py, dx, rad, vel, leftLimit, rightLimit);

        // and what do do with the colour?
    }

    public void moveDecision(int direction) {
        // implementation goes here
    }
}
