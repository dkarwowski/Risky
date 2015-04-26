package risky.common;

public class StateContext {
    private Statelike myState;
    private int gameState;

    public final static int SETUP_BOARD = 1;
    public final static int PLAY_GAIN = 2;
    public final static int PLAY_PUTS = 3;
    public final static int PLAY_ATTK = 4;

    public StateContext() {
        this(null);
    }

    public StateContext(Statelike state) {
        setState(state);
        gameState = StateContext.SETUP_BOARD;
    }

    /**
     * Setter method for state
     * Normally only called by classes implementing the State interface.
     * @param newState the new state of this context
     */
    public void setState(Statelike newState) {
        myState = newState;
    }

    /**
     * Get the Player who is currently active
     * @return Player who is active
     */
    public Player getPlayer() {
        return (this.myState.getPlayer());
    }

    /**
     * Remove resources from the current player
     * @param resources Integer of resources to remove
     */
    public void removeResources(int resources) {
        this.myState.removeResources(resources);
    }

    /**
     * Check if the game state matches a test
     * @param compare integer that should fit in the statecontext parameters
     * @return boolean value of matching or not
     */
    public boolean gameStateEquals(int compare) {
        return (this.gameState == compare);
    }

    /**
     * Force the game to move on to its next stage
     */
    public void progressGame() {
        if (this.gameStateEquals(StateContext.SETUP_BOARD))
            this.gameState = StateContext.PLAY_GAIN;
        else if (this.gameState > StateContext.SETUP_BOARD)
            this.cycleGame();
    }

    /**
     * Get the current state from the context
     * @return StateContext variable
     */
    public int getPlayerState() {
        return (this.gameState);
    }

    /**
     * Cycles the game state
     */
    private void cycleGame() {
        if (++this.gameState > StateContext.PLAY_ATTK)
            this.gameState = StateContext.PLAY_GAIN;
    }
}
