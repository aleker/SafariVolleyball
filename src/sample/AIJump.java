package sample;

/**
 * Created by AiS on 2016-01-16.
 */
public class AIJump extends Player {
    public AIJump(int side) {
        super(side);
    }

    @Override
    public void moveDecision(int direction, double deltaTime, Point ball_point) {
        animal.move(Animal.DIR_UP, deltaTime);
    }
}
