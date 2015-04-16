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
}
