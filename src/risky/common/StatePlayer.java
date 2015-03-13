package risky.common;

public class StatePlayer implements Statelike{

    private Player player;

    void StatePlayer(Player plyr){
        player = plyr;
    }

    @Override
    public void writeName(StateContext context, String name) {

    }
}
