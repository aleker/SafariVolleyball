package sample;

/**
 * Created by ola on 17.01.16.
 */
public class AI_Ola extends Player {

    private double leftLimit;
    private double rightLimit;
    private int attack_side;
    private boolean attack = false;

    private static final int threshold = 7;
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;
    private static final int ATTACK_LEFT = 0;
    private static final int ATTACK_RIGHT = 1;

    public AI_Ola(int side) {
        super(side);
        // set limits
        if (side == LEFT_SIDE) {
            leftLimit = 0;
            rightLimit = GAME_WIDTH/2;
            attack_side = ATTACK_RIGHT;
        } else {
            leftLimit = GAME_WIDTH/2;
            rightLimit = GAME_WIDTH;
            attack_side = ATTACK_LEFT;
        }
    }

    @Override
    public void moveDecision(int direction, double deltaTime, Point ball_point) {
        // if ball is on my side
        if (attack_side == ATTACK_LEFT) {
            // if ball is on the LEFT side of the animal
            if (ball_point.pos_x + threshold < animal.center.pos_x && ball_point.pos_x > leftLimit) {
                if (attack(deltaTime, ball_point)) return;
                else {
                    animal.move(Animal.DIR_LEFT, deltaTime);
                    attack = true;
                    return;
                }
            }
            // if ball is on the RIGHT side of the animal
            else if (ball_point.pos_x > animal.center.pos_x) {
                if (attack(deltaTime, ball_point)) return;
                else animal.move(Animal.DIR_RIGHT, deltaTime);
                return;
            }
            else animal.move(0, deltaTime);
        }
        else if (attack_side == ATTACK_RIGHT) {
            // if ball is on the LEFT side of the animal
            if (ball_point.pos_x < animal.center.pos_x) {
                if (attack(deltaTime, ball_point)) return;
                else animal.move(Animal.DIR_LEFT, deltaTime);
                return;
            }
            // if ball is on the RIGHT side of the animal
            else if (ball_point.pos_x - threshold > animal.center.pos_x && ball_point.pos_x < rightLimit) {
                if (attack(deltaTime, ball_point)) return;
                else {
                    animal.move(Animal.DIR_RIGHT, deltaTime);
                    attack = true;
                    return;
                }
            }
            else animal.move(0, deltaTime);
        }
        else animal.move(0,deltaTime);
        return;
    }

    private boolean attack(double deltaTime, Point ball_point) {
        if ( ball_point.pos_y < GAME_HEIGHT/6 && attack) {
            animal.move(Animal.DIR_UP, deltaTime);
            attack = false;
            return true;
        }
        return false;
    }
}

