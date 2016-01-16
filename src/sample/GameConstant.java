package sample;

/**
 * Created by Izabela on 08.01.2016.
 */
public class GameConstant {
    public static final double C_SPEED = -180.0;
    private static final double C_ANGLE = 180.0 * Math.PI / 180.0;
    public static final double C_VEL_X = C_SPEED * Math.cos(C_ANGLE);
    public static final double C_VEL_Y = C_SPEED * Math.sin(C_ANGLE);
    public static final double C_GRAVITY = 200;
    public static final double C_START_POS_X = 400;
    public static final double C_START_POS_Y = 0;

    public static final int LEFT_WALL_X = 0;
    public static final int RIGHT_WALL_X = 800;
    public static final double ANIMAL_SPEED = 10.0;
    public static final double ANIMAL_RADIUS_PER_PIXEL = 50.0/166.0;
    public static final double ANIMAL_HEAD_CENTER_Y_PER_PIXEL = 85.0/171.0;
    public static final double ANIMAL_JUMP_SPEED = 10.0;

    public static final int ANIMAL_JUMP_HEIGHT = 200;
    public static final double ANIMAL_GRAVITY = C_GRAVITY; //200;
    public static final double ANIMAL_SCALE = 0.85;

    public static final double BALL_SCALE = 0.85;

    public static final int MAX_POINTS = 15;
}
