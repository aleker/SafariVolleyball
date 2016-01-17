package sample;

public class AI_Joanna extends Player {

    private int side;
    private double time;
    private int LEFT = 0;
    private int RIGHT = 1;
    private int UP = 2;
    private int DOWN = 3;
    private int middle;
    private Point ballCoordinates;

    public AI_Joanna(int side) {
        super(side);
        this.side = side;
        middle = GameConstant.RIGHT_WALL_X/2;
    }

    @Override
    public void moveDecision(int direction, double deltaTime, Point ball_point) {

        time = deltaTime;

        ballCoordinates = ball_point;
        boolean ballOnMySide = isBallOnMySide();
    }

    private boolean isBallOnMySide() {
        if(ballCoordinates.pos_x > middle) {
            if(side == LEFT) return false;
            else return true;
        }
        else {
            if(side == LEFT) return true;
            else return false;
        }
    }

    private void prepare() {
        if(side == LEFT) {
            if(animal.center_point.pos_x < middle/2)
                animal.move(animal.DIR_RIGHT, time);
            else if(animal.center_point.pos_x > middle/2)
                animal.move(animal.DIR_LEFT, time);
        }
        else {
            if(animal.center_point.pos_x < middle + middle/2)
                animal.move(animal.DIR_RIGHT, time);
            else if(animal.center_point.pos_x > middle + middle/2)
                animal.move(animal.DIR_LEFT, time);
        }
    }
}
