package sample;

import javafx.scene.image.Image;

/**
 * Created by Mateusz on 2016-01-08.
 */
public class Animal extends DynamicEntity {
    static double floorLevel = list_of_staticEntity.get(3).point.pos_y;
    public Point center;
    private double radius;
    private double startVel = GameConstant.ANIMAL_JUMP_SPEED;
    private double deltaX = GameConstant.ANIMAL_SPEED;
//    private double leftLimit;
//    private double rightLimit;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 8;
    private double animalHeadCenter;

    /**************************************************************************/
    private static final String[] colour2imagePath = {"Pictures/lion_brown.png", "Pictures/lion_white.png"};
    private double px;
    private double py;
    private double leftLimit;
    private double rightLimit;
    // is our character still in the air?
    public boolean inTheAir;

    public static final int DIR_LEFT = 4;
    public static final int DIR_RIGHT = 6;
    public static final int DIR_UP = 8;

    public Animal(int colour, double px, double py, double leftLimit, double rightLimit) {
        super(colour2imagePath[colour], GameConstant.ANIMAL_SCALE);
        this.px = px - width / 2;
        this.py = py - height;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit - width;
        center = new Point(0, 0);
        animalHeadCenter = GameConstant.ANIMAL_HEAD_CENTER_Y_PER_PIXEL * height;
        radius = GameConstant.ANIMAL_RADIUS_PER_PIXEL * width;
        getOnPosition();
    }

    public void getOnPosition() {
        point.pos_x = px;
        point.pos_y = py;
        vel_x = 0;
        vel_y = 0;
        inTheAir = false;
        countCenter();
    }

    public void move(int direction, double deltaTime) {
        double acc_x = 0;
        switch (direction) {
            // assuming we *can* move our character while it is in the air
            case DIR_LEFT:
                if (vel_x > -GameConstant.ANIMAL_MAX_HORIZONTAL_SPEED)
                    acc_x = -GameConstant.ANIMAL_HORIZONTAL_ACCELERATION;
                break;
            case DIR_RIGHT:
                if (vel_x < +GameConstant.ANIMAL_MAX_HORIZONTAL_SPEED)
                    acc_x = +GameConstant.ANIMAL_HORIZONTAL_ACCELERATION;
                break;
            default:
                if (!inTheAir) {
                    if (GameConstant.ANIMAL_HORIZONTAL_DECELERATION * deltaTime > Math.abs(vel_x)) {
                        vel_x = 0;
                    } else {
                        acc_x = GameConstant.ANIMAL_HORIZONTAL_DECELERATION * -Math.signum(vel_x);
                    }
                }
                break;
        }

        double acc_y = 0;
        if (inTheAir) {
            acc_y = GameConstant.ANIMAL_GRAVITY;
        } else if (direction == DIR_UP) {
            vel_y = -GameConstant.ANIMAL_JUMP_SPEED;
            inTheAir = true;
        }

        vel_x += acc_x * deltaTime;
        vel_y += acc_y * deltaTime;

        // check the limits
        if ((vel_x < 0 && point.pos_x < leftLimit) || (vel_x > 0 && point.pos_x > rightLimit)) {
            vel_x = 0;
        }

        // update the position
        point.pos_x += vel_x * deltaTime;
        point.pos_y += vel_y * deltaTime;
        if (point.pos_y > py) {
            point.pos_y = py;
            inTheAir = false;
        }

        countCenter();
    }

    private void updateVelX(double deltaTime, double acc_x) {
        vel_x += acc_x * deltaTime;
    }

    /**************************************************************************/

    Animal(int side, String imagePath) {
        super(imagePath);
        center = new Point(0, 0);
        vel_y = 0;
        vel_x = 0;
        switch(side) {
            case LEFT:
                leftLimit = GameConstant.LEFT_WALL_X;
                rightLimit = (GameConstant.RIGHT_WALL_X + GameConstant.LEFT_WALL_X)/2;
                break;
            case RIGHT:
                leftLimit = (GameConstant.RIGHT_WALL_X + GameConstant.LEFT_WALL_X)/2;
                rightLimit = GameConstant.RIGHT_WALL_X;
                break;
        }
        center.pos_x = leftLimit + (rightLimit - leftLimit) / 2;
        point.pos_x = center.pos_x - image.getWidth()/2;
        point.pos_y = floorLevel - image.getHeight();
        center.pos_y = point.pos_y + GameConstant.ANIMAL_HEAD_CENTER_Y_PER_PIXEL * height;
    }

//    public void startPos() {    // set starting position of animal
//        vel_x = vel_y = 0;
//        point = startPos;
//    }

//    public void move(int num) {
//        switch(num) {
//            case LEFT: // MOVE_LEFT
//                point.pos_x -= deltaX;
//                if(point.pos_x < leftLimit) point.pos_x = leftLimit;
//
//            case RIGHT: // MOVE_RIGHT
//                point.pos_x += deltaX;
//                if(point.pos_x > rightLimit) point.pos_x = rightLimit;
//            case UP: // MOVE_UP
//                if(point.pos_y + height == floorLevel) jump();
//                else if(point.pos_y + height < floorLevel + 20) vel_y--;
//        }
//    }

    public void calculateNewPosition() {
        point.pos_y += vel_y;
        detectStaticCollison();
        countCenter();
    }

    public double getRadius() { return radius; }
    public Point getCenter() { return center; }
    private void countCenter() {
        center.pos_x = point.pos_x + image.getWidth()/2;
        center.pos_y = point.pos_y + animalHeadCenter;
    }
    private void detectStaticCollision() {
        if(point.pos_y + image.getHeight() > floorLevel) {
            point.pos_y = image.getHeight() + floorLevel;
            vel_y = 0.0;
        }
    }
}