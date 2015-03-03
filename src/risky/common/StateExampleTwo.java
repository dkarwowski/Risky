package risky.common;

public class StateExampleTwo implements Statelike{
	private int counter = 0;

	@Override
	public void writeName(final StateContext context, final String name) {
		System.out.println(name.toUpperCase());
		if(++counter > 1){
			context.setState(new StateExample());;
		}
		
	}
	
}
