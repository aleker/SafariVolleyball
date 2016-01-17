package sample;

/**
 * Created by AiS on 2016-01-16.
 */
public class AILeftRight extends Player {
    private double counter = 0;
    private int direction = Animal.DIR_LEFT;

    public AILeftRight(int side) {
        super(side);
    }

    @Override
    public void moveDecision(int dir, double deltaTime, Point ball_point) {
        counter += deltaTime;
        if (counter > 1.5) {
            direction = 6 - (direction - 4);
            counter -= 3;
        }
        animal.move(direction, deltaTime);
    }
}
