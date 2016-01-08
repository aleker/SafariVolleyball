package sample;

import java.awt.*;

/**
 * Created by Mateusz on 2016-01-08.
 */
public class Ball extends DynamicEntity {
    private double radius;
    private Point center;
    private Point startPos;
    private double startVel; // velocity after bounce the ball

    Ball(double posX, double posY, double rad, double Vel) { // px, py - coordinates of ball center, rad - radius, Vel - velocity after bounce
        center.pos_x = posX;
        center.pos_y = posY;
        radius = rad;
        startPos.pos_x = posX - rad;
        startPos.pos_y = posY - rad;
        startVel = -Vel;
        setStartPos();
    }

    public void setStartPos() {
        point = startPos;
        vel_x = vel_y = 0;
    }

    public void countCenter() {
        center.pos_x = point.pos_x + radius;
        center.pos_y = point.pos_y + radius;
    }

    public int detectDynamicCollision(Animal animal) {    // returns 1 if collision, else returns 0
        countCenter();
        // solve distance
        double dx = center.pos_x - animal.center.pos_x;
        double dy = center.pos_y - animal.center.pos_y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <= radius + animal.radius) { // if collision then count new vel_x and vel_y
            // assuming that mass of animal is much greater than mass of ball
            vel_x = startVel * (dx / distance);
            vel_y = startVel * (dy / distance);
            return 1;
        }
        return 0;
    }
}
