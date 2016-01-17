package sample;

/**
 * Created by Izabela on 17.01.2016.
 */
public class AI_Izabela extends Player{
    private int side;

    public AI_Izabela(int side) {
        super(side);
        this.side = side;
        ball_point = new Point(0, 0);
    }

    Point ball_point;


    private boolean ballOnTheOtherSide() {
        switch (side) {
            case LEFT_SIDE:
                if (ball_point.pos_x > 400) {
                    return true;
                } //ball on the right side
                else return false;

            case RIGHT_SIDE:
                if (ball_point.pos_x < 400) {
                    return true;
                } // ball on the left side
                else return false;

        }
        return false;
    }

    private void stayInCenter(double deltaTime) {
        switch (side) {
            case LEFT_SIDE:
                if (animal.point.pos_x > 150) {
                    animal.move(Animal.DIR_LEFT, deltaTime); // go left
                    return;
                } else if (animal.point.pos_x < 100) {
                    animal.move(Animal.DIR_RIGHT, deltaTime); // go right
                    return;
                } else {
                    animal.move(0, deltaTime);
                    return;
                }

            case RIGHT_SIDE:
                if (animal.point.pos_x > 600) {
                    animal.move(Animal.DIR_LEFT, deltaTime); // go left
                    return;
                } else if (animal.point.pos_x < 500) {
                    animal.move(Animal.DIR_RIGHT, deltaTime); // go right
                    return;
                } else {
                    animal.move(0, deltaTime);
                    return;
                }
        }
    }

    private boolean ballOverTheNet() {
        if (ball_point.pos_y < 230) {
            return true;
        } else return false;
    }

    private void jump(double deltaTime) {
        animal.move(Animal.DIR_UP, deltaTime);
        return;
    }

    private void getBallPosition(double deltaTime) {
        switch (side) {
            case LEFT_SIDE:
                if (ball_point.pos_x < animal.point.pos_x && animal.point.pos_x > 100) {
                    animal.move(Animal.DIR_LEFT, deltaTime); // go left
                    return;
                } else if (ball_point.pos_x > animal.point.pos_x && animal.point.pos_x < 200) {
                    animal.move(Animal.DIR_RIGHT, deltaTime); // go right
                    return;
                }

            case RIGHT_SIDE:
                if (ball_point.pos_x < animal.point.pos_x && animal.point.pos_x > 500) {
                    animal.move(Animal.DIR_LEFT, deltaTime); // go left
                    return;
                } else if (ball_point.pos_x > animal.point.pos_x && animal.point.pos_x < 550) {
                    animal.move(Animal.DIR_RIGHT, deltaTime); // go right
                    return;
                }
        }
    }

    @Override
    public void moveDecision(int direction, double deltaTime, Point ball_point) {
        if (ballOnTheOtherSide()) {
            stayInCenter(deltaTime);
        } else if (ballOverTheNet()) {
            jump(deltaTime);
        } else {
            getBallPosition(deltaTime);
        }

    }
}