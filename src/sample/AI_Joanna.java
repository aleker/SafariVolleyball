package sample;

public class AI_Joanna extends Player {

    private int side;
    private double time;
    private int LEFT = 0;
    private int RIGHT = 1;
    private int UP = 2;
    private int DOWN = 3;
    private int ballDIR_X, ballDIR_Y;
    private int middle;
    private Point ballCoordinates;
    private Point pastBallCoordinates = new Point(0,0);

    public AI_Joanna(int side) {
        super(side);
        this.side = side;
        middle = GameConstant.WINDOW_WIDTH/2;
    }

    @Override
    public void moveDecision(int direction, double deltaTime, Point ball_point) {

        time = deltaTime;
        ballCoordinates = ball_point;
        boolean ballOnMySide = isBallOnMySide();

        detectBallMovement();

        /** MOVEMENT DECISION **/
        if(ballOnMySide) {
            int delta = 10;
            int noJumpZone = 100;
            boolean doMove = ballCoordinates.pos_y > 200;

            /** PRIORITY IS FALLING IF IN THE AIR **/
            if(animal.point.pos_y + animal.image.getHeight() < 600)
                animal.move(0, time);
            /** MOVEMENT DECISION FOR WHEN LEFT PLAYER **/
            if(side == LEFT) {
                if(ballCoordinates.pos_y < 400 && ballCoordinates.pos_y > 200 && ballDIR_Y == DOWN && ballDIR_X == LEFT) {
                    if(animal.center.pos_x < ballCoordinates.pos_x
                            && animal.center.pos_x + delta > ballCoordinates.pos_x
                            && animal.center.pos_x < middle - noJumpZone
                            && doMove)
                        animal.move(animal.DIR_UP, time);
                }
                else if(animal.center.pos_x + delta < ballCoordinates.pos_x && doMove) {
                    animal.move(animal.DIR_RIGHT, time);
                }
                else if(animal.center.pos_x > ballCoordinates.pos_x && doMove) {
                    animal.move(animal.DIR_LEFT, time);
                }
                else animal.move(0, time);
            }
            /** MOVEMENT DECISION FOR WHEN RIGHT PLAYER **/
            else {
                if(ballCoordinates.pos_y < 400 && ballCoordinates.pos_y > 200 && ballDIR_Y == DOWN && ballDIR_X == RIGHT) {
                    if(animal.center.pos_x - delta < ballCoordinates.pos_x
                            && animal.center.pos_x > ballCoordinates.pos_x
                            && animal.center.pos_x > middle + noJumpZone
                            && doMove)
                        animal.move(animal.DIR_UP, time);
                }
                else if(animal.center.pos_x < ballCoordinates.pos_x && doMove) {
                    animal.move(animal.DIR_RIGHT, time);
                }
                else if(animal.center.pos_x - delta > ballCoordinates.pos_x && doMove) {
                    animal.move(animal.DIR_LEFT, time);
                }
                else animal.move(0, time);
            }
        }
        else {
            prepare();
        }

        pastBallCoordinates.pos_x = ballCoordinates.pos_x;
        pastBallCoordinates.pos_y = ballCoordinates.pos_y;
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

    private void detectBallMovement() {
        if(pastBallCoordinates.pos_x - ballCoordinates.pos_x > 0) ballDIR_X = LEFT;
        else ballDIR_X = RIGHT;
        if(pastBallCoordinates.pos_y - ballCoordinates.pos_y > 0) ballDIR_Y = UP;
        else ballDIR_Y = DOWN;
    }

    private void prepare() {
        int delta = 10;
        if(side == LEFT) {
            if(animal.center.pos_x + delta < middle/2)
                animal.move(animal.DIR_RIGHT, time);
            else if(animal.center.pos_x > middle/2)
                animal.move(animal.DIR_LEFT, time);
            else animal.move(0, time);
        }
        else {
            if(animal.center.pos_x < middle + middle/2)
                animal.move(animal.DIR_RIGHT, time);
            else if(animal.center.pos_x - delta > middle + middle/2)
                animal.move(animal.DIR_LEFT, time);
            else animal.move(0, time);
        }
    }
}