package sample;

/**
 * Created by Mateusz on 2016-01-18.
 */
public class AI_Mateusz extends Player{
    private double x, y;
    private double oldBallX, oldBallY;
    private double dx, dy;
    private double px;
    private double leftLimit;
    private double rightLimit;
    private int mySide;

    public AI_Mateusz(int side) {
        super(side);
        mySide = LEFT_SIDE;
        leftLimit = 0.0;
        rightLimit = 400.0;
        if(side == RIGHT_SIDE) {
            leftLimit += 400.0;
            rightLimit += 400.0;
            mySide = RIGHT_SIDE;
        }
    }

    private double abs(double a) {
        if(a < 0.0) return -a;
        return a;
    }

    @Override
    public void moveDecision(int dir, double deltaTime, Point ball_point) {


        x = animal.point.pos_x + animal.width/2;
        y = animal.point.pos_y;
        if(ball_point.pos_x > rightLimit || ball_point.pos_x < leftLimit) {
            double center = (leftLimit + rightLimit) /2 + 3 * px;
            if(center > x) {
                if(animal.vel_x < 100) animal.move(Animal.DIR_RIGHT, deltaTime);
                else animal.move(Animal.DIR_LEFT, deltaTime);
                return;
            }
            if(center < x) {
                if(animal.vel_x < 100) animal.move(Animal.DIR_LEFT, deltaTime);
                else animal.move(Animal.DIR_RIGHT, deltaTime);
                return;
            }
            return;
        }
        dx = ball_point.pos_x - x + px/2;
        dy = abs(ball_point.pos_y - y);
        if(abs(dx) < 20.0 && dy < 200.0) {
            animal.move(Animal.DIR_UP, deltaTime);
            return;
        }

        if(mySide == LEFT_SIDE) px = -20;
        else px = 20;

        if(ball_point.pos_x + px > x) {
            if(animal.vel_x < 150) animal.move(Animal.DIR_RIGHT, deltaTime);
            else animal.move(Animal.DIR_LEFT, deltaTime);
            return;
        }

        if(ball_point.pos_x - px < x) {
            if(animal.vel_x > -150) animal.move(Animal.DIR_LEFT, deltaTime);
            else animal.move(Animal.DIR_RIGHT, deltaTime);
            return;
        }

        animal.move(Animal.DIR_LEFT, deltaTime);
    }
}
