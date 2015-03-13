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
	void setState(final Statelike newState) {
		myState = newState;
	}
}
