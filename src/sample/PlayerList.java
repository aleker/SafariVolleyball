package sample;

import java.lang.reflect.Constructor;

/**
 * Created by aleksander-zn on 2016-01-10.
 */
public class PlayerList {
    public static final Class<?>[] playerList = {Player.class, AI_Ola.class, AI_Joanna.class, AI.class, AIJump.class, AILeftRight.class};
    public static Player newPlayer(int index, int side) {
        try {
            @SuppressWarnings("unchecked")
            final Class<? extends Player> playerClass = (Class<? extends Player>) playerList[index];
            final Constructor<? extends Player> playerConstructor = playerClass.getConstructor(int.class);
            return playerConstructor.newInstance(side);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

class AI extends Player {
    public AI(int side) {
        super(side);
        System.out.println("AI has been created");
    }
}
