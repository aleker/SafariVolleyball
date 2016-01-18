package sample;

/**
 * Created by AiS on 2016-01-17.
 */
public class AI_Aleksander extends Player {
    private final double b;
    private int counter;
    private int jmpCounter;
    private boolean jmpLock;
    private static final double e = 10.0;
    private static final double f = 51.0;
    private static final double g = 200.0;
    private static final double h = 150.0;
    private static final double i = 100;
    private double vx, vy;
    private double bx, by;

    public AI_Aleksander(int side) {
        super(side);
        b = (1 - 2 * side);
        counter = 0;
        jmpCounter = 0;
        jmpLock = false;
        vx = 0;
        vy = 0;
        bx = 0;
        by = 0;
    }

    @Override
    public void moveDecision(int dir, double deltaTime, Point ball_point) {
        vx = (ball_point.pos_x - bx) / deltaTime;
        vy = (ball_point.pos_y - by) / deltaTime;
        bx = ball_point.pos_x;
        by = ball_point.pos_y;
        counter++;
        final double a = GameConstant.ANIMAL_RADIUS_PER_PIXEL * animal.width * 2;
        int direction = 0;
        final double dx = ball_point.pos_x - animal.center.pos_x;
        if (dx * b > a / 2) {
            if (b < 0)  direction = Animal.DIR_LEFT;
            else        direction = Animal.DIR_RIGHT;
        } else {
            if (b < 0)  direction = Animal.DIR_RIGHT;
            else        direction = Animal.DIR_LEFT;
        }
        if (dx * b > a / 2) {
            long speed = Math.round(dx * dx / e);
            if (speed > 10) speed = 10;
            if (counter > speed) direction = 0;
        }
        final double dy = ball_point.pos_y - animal.center.pos_y;
        final double dist = Math.sqrt(dx * dx + dy * dy);
        if (dist <= a + f + g && dist > a + f + h && (dx * b) > a / 2 && (dx * b) < a && Math.abs(vx) < i && vy > 0) {
            if (!jmpLock) {
                jmpCounter++;
                if (jmpCounter == 1)
                    direction = Animal.DIR_UP;
                jmpCounter %= 1;
                jmpLock = true;
            }
        } else {
            jmpLock = false;
        }
        animal.move(direction, deltaTime);
        counter %= 10;
    }
}
