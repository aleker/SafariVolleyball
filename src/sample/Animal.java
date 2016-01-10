package sample;

/**
 * Created by Mateusz on 2016-01-08.
 */
public class Animal extends DynamicEntity {
    private Point center;
    private Point startPos;
    private double radius;
    private double startVel;
    private double deltaX;
    private double leftLimit;
    private double rightLimit;

    Animal(double px, double py, double dx, double rad, double vel, double limitLeft, double limitRight) {
        super("animal1.jpg");
        center.pos_x = px;
        center.pos_y = py;
        deltaX = dx;
        radius = rad;
        startVel = vel;
        vel_x = vel_y = 0;
        startPos.pos_x = px - rad;
        startPos.pos_y = py - rad;
        leftLimit = limitLeft;
        rightLimit = limitRight;
    }

    public void startPos() {    // set starting position of animal
        vel_x = vel_y = 0;
        point = startPos;
    }

    public void move(int num) {
        switch(num) {
            case 4: // MOVE_LEFT
                point.pos_x -= deltaX;
                if(point.pos_x < leftLimit) point.pos_x = leftLimit;
            case 6: // MOVE_RIGHT
                point.pos_x += deltaX;
                if(point.pos_x > rightLimit) point.pos_x = rightLimit;
            case 8: // MOVE_UP
                double floorLevel = list_of_staticEntity.get(3).point.pos_y;
                if(point.pos_y + height == floorLevel) jump();
        }
    }

    private void jump() {
        vel_y = -startVel;
    }

    public double getRadius() { return radius; }
    public Point getCenter() { return center; }
}
