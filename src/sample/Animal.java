package sample;

import javafx.scene.image.Image;

/**
 * Created by Mateusz on 2016-01-08.
 */
public class Animal extends DynamicEntity {
    static double floorLevel = list_of_staticEntity.get(3).point.pos_y;
    public Point center;
    private double radius = GameConstant.ANIMAL_RADIUS;
    private double startVel = GameConstant.ANIMAL_JUMP_SPEED;
    private double deltaX = GameConstant.ANIMAL_SPEED;
    private double leftLimit;
    private double rightLimit;
    final int LEFT = 0;
    final int RIGHT = 1;
    final int UP = 8;

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
        center.pos_y = point.pos_y + GameConstant.ANIMAL_HEAD_CENTER_Y;
    }

//    public void startPos() {    // set starting position of animal
//        vel_x = vel_y = 0;
//        point = startPos;
//    }

    public void move(int num) {
        switch(num) {
            case LEFT: // MOVE_LEFT
                point.pos_x -= deltaX;
                if(point.pos_x < leftLimit) point.pos_x = leftLimit;

            case RIGHT: // MOVE_RIGHT
                point.pos_x += deltaX;
                if(point.pos_x > rightLimit) point.pos_x = rightLimit;
            case UP: // MOVE_UP
                if(point.pos_y + height == floorLevel) jump();
                else if(point.pos_y + height < floorLevel + 20) vel_y--;
        }
    }

    private void jump() {
        vel_y = -startVel;
    }

    public void calculateNewPosition() {
        point.pos_y += vel_y;
        detectStaticCollison();
        countCenter();
    }

    public double getRadius() { return radius; }
    public Point getCenter() { return center; }
    private void countCenter() {
        center.pos_x = point.pos_x + image.getWidth()/2;
        center.pos_y = point.pos_y + GameConstant.ANIMAL_HEAD_CENTER_Y;
    }
    private void detectStaticCollision() {
        if(point.pos_y + image.getHeight() > floorLevel) {
            point.pos_y = image.getHeight() + floorLevel;
            vel_y = 0.0;
        }
    }
}