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
    private Point pastBallCoordinates = new Point(0,0);

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
        System.out.println(ballOnMySide);

        /** DETECTING BALL MOVEMENT **/
        int ballDIR_X, ballDIR_Y;
        if(pastBallCoordinates.pos_x - ball_point.pos_x > 0) ballDIR_X = LEFT;
        else ballDIR_X = RIGHT;
        if(pastBallCoordinates.pos_y - ball_point.pos_y > 0) ballDIR_Y = UP;
        else ballDIR_Y = DOWN;

        /** MOVEMENT DECISION **/
        if(ballOnMySide) {
            if(animal.center.pos_x + 5 > ball_point.pos_x
                    && animal.center.pos_x - 5 < ball_point.pos_x
                    && ball_point.pos_y < 150
                    && ballDIR_Y == DOWN) {
                animal.move(animal.DIR_UP, deltaTime);
            }
            if(animal.center.pos_x < ball_point.pos_x) {
                animal.move(animal.DIR_RIGHT, deltaTime);
            }
            else if(animal.center.pos_x > ball_point.pos_x) {
                animal.move(animal.DIR_LEFT, deltaTime);
            }
            else animal.move(0, deltaTime);
        }
        else {
            prepare();
        }

        pastBallCoordinates.pos_x = ball_point.pos_x;
        pastBallCoordinates.pos_y = ball_point.pos_y;
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
            if(animal.center.pos_x < middle/2)
                animal.move(animal.DIR_RIGHT, time);
            else if(animal.center.pos_x > middle/2)
                animal.move(animal.DIR_LEFT, time);
        }
        else {
            if(animal.center.pos_x < middle + middle/2)
                animal.move(animal.DIR_RIGHT, time);
            else if(animal.center.pos_x > middle + middle/2)
                animal.move(animal.DIR_LEFT, time);
        }
    }
}
