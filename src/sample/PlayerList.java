package sample;

import java.lang.reflect.Constructor;

/**
 * Created by aleksander-zn on 2016-01-10.
 */
public class PlayerList {
    public static final Class<?>[] playerList = {Player.class, AI.class};
    public static Player newPlayer(int index, int side) throws Exception {
        final Class<? extends Player> playerClass = (Class<? extends Player>) playerList[index];
        final Constructor<? extends Player> playerConstructor = playerClass.getConstructor(int.class);
        final Player player = playerConstructor.newInstance(side);
        return player;
    }
}

class AI extends Player {
    public AI(int side) {
        super(side);
        System.out.println("AI has been created");
    }
}
