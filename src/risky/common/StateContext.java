package risky.common;

public class StateContext {
    private Statelike myState;

    public StateContext() {
        this(null);
    }

    public StateContext(Statelike state) {
        setState(state);
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
     * Force the game to move on to its next stage
     */
    public void progressGame() {
        if (this.myState.isThisState(Statelike.SETUP_BOARD))
            this.myState.finishSetup();
        else if (this.myState.getPlayerState() > Statelike.SETUP_BOARD)
            this.myState.cycleGame();
    }

    /**
     * Check whether the game is in a specific state
     * @param testState Statelike state to check against
     * @return boolean if the state matches
     */
    public boolean isGameInState(int testState) {
        return (this.myState.isThisState(testState));
    }

    /**
     * Get the current state from the context
     * @return Statelike variable
     */
    public int getPlayerState() {
        return (this.myState.getPlayerState());
    }
}
