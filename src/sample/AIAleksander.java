package sample;

/**
 * Created by AiS on 2016-01-17.
 */
public class AIAleksander extends Player {
    private final double b;

    public AIAleksander(int side) {
        super(side);
        b = (1 - 2 * side);
    }

    @Override
    public void moveDecision(int dir, double deltaTime, Point ball_point) {
        double a = GameConstant.ANIMAL_RADIUS_PER_PIXEL * animal.width / 2;
        int direction;
        final double d = ball_point.pos_x - (animal.point.pos_x + animal.width / 2);
        if (d * b > a) {
            if (b < 0)  direction = Animal.DIR_LEFT;
            else        direction = Animal.DIR_RIGHT;
        } else {
            if (b < 0)  direction = Animal.DIR_RIGHT;
            else        direction = Animal.DIR_LEFT;
        }
        animal.move(direction, deltaTime);
    }
}
