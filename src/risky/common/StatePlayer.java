package risky.common;

public class StatePlayer implements Statelike{
    private Player player;

    public StatePlayer(Player plyr){
        this.player = plyr;
    }

    public Player getPlayer() {
        return (this.player);
    }
}