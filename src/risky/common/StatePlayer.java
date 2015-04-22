package risky.common;

public class StatePlayer implements Statelike{
    private Player player;
    private int gameState;

    public StatePlayer(Player plyr){
        this.gameState = Statelike.SETUP_BOARD;
        this.player = plyr;
    }

    public Player getPlayer() {
        return (this.player);
    }

    @Override
    public void removeResources(int resources) {
        this.player.addResources(-resources);
    }

    @Override
    public boolean isThisState(int testState) {
        return (this.gameState == testState);
    }

    @Override
    public int getPlayerState() {
        return (this.gameState);
    }

    @Override
    public void finishSetup() {
        this.gameState = Statelike.PLAY_GAIN;
    }

    @Override
    public void cycleGame() {
        if (++this.gameState > Statelike.PLAY_ATTK)
            this.gameState = Statelike.PLAY_GAIN;
    }
}
