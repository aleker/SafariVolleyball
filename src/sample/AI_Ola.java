package sample;

/**
 * Created by ola on 17.01.16.
 */
public class AI_Ola extends Player {

    private double leftLimit;
    private double rightLimit;
    private Point old_ball_point;
    private int attack_side;

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;
    private static final int ATTACK_LEFT = 0;
    private static final int ATTACK_RIGHT = 1;

    public AI_Ola(int side) {
        super(side);
        // set limits
        this.old_ball_point = new Point(GameConstant.C_START_POS_X,GameConstant.C_START_POS_Y);
        final StaticEntity leftWall = StaticEntity.list_of_staticEntity.get(0);
        final StaticEntity rightWall = StaticEntity.list_of_staticEntity.get(1);
        final StaticEntity net = StaticEntity.list_of_staticEntity.get(4);
        final StaticEntity ground = StaticEntity.list_of_staticEntity.get(3);
        if (side == LEFT_SIDE) {
            leftLimit = leftWall.point.pos_x + leftWall.width;
            rightLimit = net.point.pos_x;
            attack_side = ATTACK_RIGHT;
        } else {
            leftLimit = net.point.pos_x + net.width;
            rightLimit = rightWall.point.pos_x;
            attack_side = ATTACK_LEFT;
        }
    }

    @Override
    public void moveDecision(int direction, double deltaTime, Point ball_point) {
        // if ball is on my side
        if (attack_side == ATTACK_LEFT) {
            // if ball is on the LEFT side of the animal
            if (ball_point.pos_x < animal.point.pos_x && ball_point.pos_x > leftLimit) {
                attack(deltaTime, ball_point);
                animal.move(Animal.DIR_LEFT, deltaTime);
            }
            // if ball is on the RIGHT side of the animal
            else if (ball_point.pos_x > animal.point.pos_x) {
                attack(deltaTime, ball_point);
                animal.move(Animal.DIR_RIGHT, deltaTime);
            }
        }
        else if (attack_side == ATTACK_RIGHT) {
            // if ball is on the LEFT side of the animal
            if (ball_point.pos_x < animal.point.pos_x) {
                attack(deltaTime, ball_point);
                animal.move(Animal.DIR_LEFT, deltaTime);
            }
            // if ball is on the RIGHT side of the animal
            else if (ball_point.pos_x > animal.point.pos_x && ball_point.pos_x < rightLimit) {
                attack(deltaTime, ball_point);
                animal.move(Animal.DIR_RIGHT, deltaTime);
            }
        }
    }

    private void attack(double deltaTime, Point ball_point) {
        if ( ball_point.pos_y < GAME_HEIGHT/5) {
            animal.move(Animal.DIR_UP, deltaTime);
        }
    }
}

