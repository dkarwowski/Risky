package risky.common;

public interface Statelike {
    public Player getPlayer();

    public final static int SETUP_BOARD = 1;
    public final static int PLAY_GAIN = 2;
    public final static int PLAY_PUTS = 3;
    public final static int PLAY_ATTK = 4;

    public void removeResources(int resources);
    
    public boolean isThisState(int testState);

    public int getPlayerState();

    public void finishSetup();
    public void cycleGame();
}
